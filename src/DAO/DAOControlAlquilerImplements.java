package DAO;

import DAOInterfaces.*;
import Informacion.Control_Tiempo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import util.MySQLConexion;
import java.util.Timer;
import java.util.TimerTask;

public class DAOControlAlquilerImplements implements DAOControlAlquiler {

    public int[] timer;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void ListarControlAlquiler(Control_Tiempo ControlTiempo, JTable JT) {
        Connection con = (Connection) MySQLConexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        DefaultTableModel jTableControl = (DefaultTableModel) JT.getModel();
        jTableControl.setRowCount(0);

        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT ca.alquiler_id, e.nombre AS nombre_equipo, ca.fecha_alquiler_inicio, ca.hora_inicio, ca.fecha_alquiler_final, ca.hora_final_estimada, TIMESTAMPDIFF(SECOND, NOW(), TIMESTAMP(ca.fecha_alquiler_final, ca.hora_final_estimada)) AS segundos_restantes, c.nombres AS nombre_cliente, precio_final FROM control_alquiler ca JOIN equipo e ON ca.equipo_id = e.equipo_id JOIN cliente c ON ca.cliente_id = c.cliente_id WHERE ca.id_estado_control IN (1, 2) AND ca.usuario_id = ?");
            ps.setInt(1, ControlTiempo.getUsuario().getId_usuario());
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            // Mueve el cursor a la última fila para obtener el tamaño del ResultSet
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst(); // Mueve el cursor de vuelta a la primera fila
            timer = new int[rowCount];
            Object[] fila = new Object[rsmd.getColumnCount()];
            int t = 0;
            while (rs.next()) {
                // System.out.println("A");
                for (int indice = 0; indice < rsmd.getColumnCount(); indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }

                // Obtén minutos_transcurridos y conviértelo a segundos para inicializar timer
                String tiempoTranscurrido = rs.getString("segundos_restantes");
                int segundosTranscurridos = convertirTiempoASegundos(formatTimer(Integer.parseInt(tiempoTranscurrido)));

                // Inicializa el arreglo timer si es la primera vez que se asigna un valor
                // Asigna el valor de segundosTranscurridos al arreglo timer
                timer[t] = segundosTranscurridos;
                // System.out.println("aas: " + timer[t]);
                if (fila[7].equals("NOCLIENTE")) {
                    fila[7] = "Sin cuenta";

                }
                if (timer[t] <= 0) {
                    timer[t] = 0; // Si el timer es 0 o menor, establecerlo a 0
                }
                jTableControl.addRow(new Object[]{fila[0], fila[1], fila[2], fila[3], fila[4], fila[5], formatTimer(timer[t]), fila[7],fila[8]});
                t++;
            }
            /*   for (int i = 0; i < timer.length; i++) {

                System.out.println("timer antes del for: " + timer[i]);
            }*/
            con.close();

            // Programa una tarea para actualizar la tabla con el timer cada segundo
            TimerTask task = new TimerTask() {
                public void run() {
                    // Incrementa el timer cada segundo
                    for (int i = 0; i < timer.length; i++) {
                        timer[i]--; // Decrementar el valor del timer
                        if (timer[i] <= 0) {
                            timer[i] = 0; // Si el timer es 0 o menor, establecerlo a 0
                        }
                    }

                    // Actualiza la tabla con el nuevo valor de timer
                    int rowCount = jTableControl.getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        jTableControl.setValueAt(formatTimer(timer[i]), i, 6); // Actualiza la columna del timer
                    }
                }
            };

            Timer timer = new Timer();
            // Programa la tarea para que se ejecute cada segundo (1000 ms)
            timer.scheduleAtFixedRate(task, 1000, 1000);

        } catch (SQLException e) {
            System.out.println("ERRORSQL: " + e);
        }
    }

    private int convertirTiempoASegundos(String tiempo) {
        //System.out.println(tiempo);
        String[] partes = tiempo.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        int segundos = Integer.parseInt(partes[2]);
        return horas * 3600 + minutos * 60 + segundos;
    }

    private String formatTimer(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    @Override
    public boolean InsertarAlquiler(Control_Tiempo CT) {
        LocalDate fechaActual = LocalDate.now();
        String sql = "INSERT INTO control_alquiler (usuario_id, equipo_id, fecha_alquiler_inicio, hora_inicio, tiempo_alquilado, hora_final_estimada, fecha_alquiler_final, precio_final,cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection con = (Connection) MySQLConexion.getConexion(); PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setInt(1, CT.getUsuario().getId_usuario()); // Reemplaza con el método correcto para obtener usuario_id
            ps.setInt(2, CT.getEquipo().getId_equipo());
            ps.setDate(3, java.sql.Date.valueOf(fechaActual));
            ps.setTime(4, java.sql.Time.valueOf(CT.getHoraInicio()));
            ps.setInt(5, CT.getTiempoAgregador()); // Reemplaza con el método correcto para obtener tiempo_alquilado
            ps.setTime(6, java.sql.Time.valueOf(CT.getHoraInicio().plusMinutes(CT.getTiempoAgregador())));
            ps.setDate(7, java.sql.Date.valueOf(fechaActual));
            ps.setDouble(8, CT.getPrecio());
            ps.setString(9, CT.getCliente().getDNICliente());

            int rows = ps.executeUpdate();
            ActualizarEstadoEquipo(CT);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Map<String, Integer> ComboEquipos(JComboBox JC) {
        Map<String, Integer> equipoMap = new HashMap<>();

        Connection con = (Connection) MySQLConexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {

            ps = (PreparedStatement) con.prepareStatement("SELECT equipo_id, nombre FROM equipo WHERE estado_id = 1");
            rs = ps.executeQuery();

            while (rs.next()) {
                String equipo = rs.getString("nombre");
                int id = rs.getInt("equipo_id");
                equipoMap.put(equipo, id);
                JC.addItem(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipoMap;

    }

    @Override
    public Map<String, Integer> ComboClientes(JComboBox JC) {
        Map<String, Integer> clientemap = new HashMap<>();

        Connection con = (Connection) MySQLConexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {

            ps = (PreparedStatement) con.prepareStatement("SELECT cliente_id, nombres FROM cliente;");
            rs = ps.executeQuery();

            while (rs.next()) {
                String equipo = rs.getString("nombres");
                int id = rs.getInt("cliente_id");

                if (equipo.equals("NOCLIENTE")) {
                    equipo = "Sin cuenta";

                }
                clientemap.put(equipo, id);
                JC.addItem(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientemap;
    }

    @Override
    public boolean ActualizarAlquiler(Control_Tiempo ControlTiempo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ActualizarEstadosAutomatica(Control_Tiempo ControlTiempo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean CobrarAlquiler(Control_Tiempo CT) {
        // Definir el SQL para insertar un nuevo comprobante de pago, sin la columna fecha_pago
        String sql = "INSERT INTO comprobante_pago (alquiler_id, monto, tipo_comprobante) VALUES (?, ?, ?);";

        try (Connection con = (Connection) MySQLConexion.getConexion(); PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {

            // Establecer los parámetros en la consulta
            ps.setInt(1, CT.getId_Alquiler()); // Asumiendo que Control_Tiempo tiene un método getAlquiler y Alquiler tiene un método getId_alquiler
            ps.setDouble(2, CT.getPrecio()); // Asumiendo que Control_Tiempo tiene un método getMonto que devuelve un BigDecimal
            ps.setString(3, "Recibo"); // Asumiendo que Control_Tiempo tiene un método getTipoComprobante que devuelve un String

            int rows = ps.executeUpdate();
          
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void ActualizarEstadoEquipo(Control_Tiempo CT) {
        //  
        String sql = " UPDATE `equipo` SET `estado_id` = '2' WHERE `equipo`.`equipo_id` = ?;";
        try (Connection con = (Connection) MySQLConexion.getConexion(); PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {

            ps.setInt(1, CT.getEquipo().getId_equipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void ActualizarEstadoEquipoLibre(Control_Tiempo CT) {
        //  
        String sql = " UPDATE `equipo` SET `estado_id` = '1' WHERE `equipo`.`equipo_id` = ?;";
        try (Connection con = (Connection) MySQLConexion.getConexion(); PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {

            ps.setInt(1, CT.getEquipo().getId_equipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void ActualizarMinutos(Control_Tiempo ControlTiempo) {
        // Definir el SQL para actualizar los minutos guardados
        String sql = "UPDATE `cliente` SET `minutos_guardados` = `minutos_guardados` + ? WHERE `cliente_id` = ?;";

        try (Connection con = (Connection) MySQLConexion.getConexion(); PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {

            // Establecer los parámetros en la consulta
            ps.setInt(1, ControlTiempo.getTiempoRestante()); // Asumiendo que Control_Tiempo tiene un método getMinutosASumar
            ps.setString(2, ControlTiempo.getCliente().getDNICliente()); // Asumiendo que Control_Tiempo tiene un método getCliente y Cliente tiene un método getId_cliente

            // Ejecutar la actualización
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void ActualizarEstadoEquipoControl(Control_Tiempo CT) {
        //  
        String sql = " UPDATE `control_alquiler` SET `id_estado_control` = '3' WHERE `alquiler_id` = ?;";
        try (Connection con = (Connection) MySQLConexion.getConexion(); PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {

            ps.setInt(1, CT.getId_Alquiler());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public Map<String, Integer> ListarIDEq(Control_Tiempo CT) {
        Map<String, Integer> equipoMap = new HashMap<>();

        Connection con = (Connection) MySQLConexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {

            ps = (PreparedStatement) con.prepareStatement("SELECT equipo_id, nombre FROM equipo");
            rs = ps.executeQuery();

            while (rs.next()) {
                String equipo = rs.getString("nombre");
                int id = rs.getInt("equipo_id");
                equipoMap.put(equipo, id);
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipoMap;

    }

}
