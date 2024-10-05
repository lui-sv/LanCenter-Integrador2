package DAO;

import Informacion.Equipo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import util.MySQLConexion;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import DAOInterfaces.DAOEquipo;

/**
 *
 * @author MARLON
 */
public class DAOEquipoImplements implements DAOEquipo{
    
    int columnas;
    @Override
    public void CargarTablaEquipos(JTable tabla) {
        Connection con = (Connection) MySQLConexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd; 
        DefaultTableModel jTableAlumnosA = (DefaultTableModel) tabla.getModel(); 
        jTableAlumnosA.setRowCount(0);

        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT e.equipo_id, e.nombre, es.descripcion " +
                         "FROM equipo e " +
                         "JOIN estado_equipo es ON e.estado_id = es.estado_id " +
                         "WHERE es.descripcion IN ('LIBRE', 'FUERA DE SERVICIO')"); 
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            Object[][] data = new Object[100][columnas]; // Ajusta el tamaño del arreglo según tus necesidades

            int row = 0;

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }

                data[row] = fila;
                row++;
            }

            
            QuickSort.ordenar(data, 0, row - 1);

            for (int i = 0; i < row; i++) {
                jTableAlumnosA.addRow(data[i]);
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("ERRORSQL: " + e);
        }
        
    }

    @Override
    public boolean actualizarEstado(Equipo equipo) {
    Connection con = (Connection) MySQLConexion.getConexion();
    PreparedStatement ps;
    
    try {
        // Actualizar el estado en la base de datos
            ps = (PreparedStatement) con.prepareStatement("UPDATE equipo SET estado_id = ? WHERE equipo_id = ?");
            ps.setInt(1, equipo.getEstado_id_equipo());
            ps.setInt(2, equipo.getId_equipo());
            ps.executeUpdate();
            return true; // La actualización fue exitosa
    } catch (SQLException e) {
        System.out.println("ERRORSQL: " + e);
        return false; // Hubo un error en la actualización
    }
}

    @Override
    public Map<String, Integer> ListarComboBox(JComboBox<String> cbEstado) {
        Map<String, Integer> estadoMap = new HashMap<>();
        
        Connection con = (Connection) MySQLConexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
          
            ps = (PreparedStatement) con.prepareStatement("SELECT estado_id, descripcion FROM estado_equipo WHERE descripcion IN ('LIBRE', 'FUERA DE SERVICIO')");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String estado = rs.getString("descripcion");
                int id = rs.getInt("estado_id");
                estadoMap.put(estado, id);
                cbEstado.addItem(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estadoMap;
    }
    
        @Override
    public boolean agregarEquipo(String nombre) {
        java.sql.Connection con = MySQLConexion.getConexion();
        java.sql.PreparedStatement ps;
        ResultSet rs;
        String nuevoNombreEquipo;

        try {
            ps = con.prepareStatement("SELECT nombre FROM equipo WHERE nombre LIKE ? ORDER BY equipo_id DESC LIMIT 1");
            ps.setString(1, nombre + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                String ultimoNombreEquipo = rs.getString("nombre");

                String numeroStr = ultimoNombreEquipo.replace(nombre, "").replaceAll("\\D+", "");
                int numero = 1; 

                if (!numeroStr.isEmpty()) {
                    try {
                        numero = Integer.parseInt(numeroStr) + 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir el número: " + e.getMessage());
                    }
                }

                nuevoNombreEquipo = nombre + numero;
            } else {
                nuevoNombreEquipo = nombre + "1";
            }

            ps = con.prepareStatement("INSERT INTO equipo (nombre, estado_id) VALUES (?, ?)");
            ps.setString(1, nuevoNombreEquipo);
            ps.setInt(2, 1);  

            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar equipo: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    }
    


