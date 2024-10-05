package DAO;

import DAOInterfaces.DAORecepcionista;
import Informacion.Usuario;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import util.MySQLConexion;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DAORecepcionistaImplements implements DAORecepcionista{
    
  int columnas;
    
  public boolean agregarRecepcionista(Usuario usuario) {
    Connection con ;
    PreparedStatement ps;
    ResultSet rs ;

    try {
        con = (Connection) MySQLConexion.getConexion();

        // Verificar si el nombre de usuario existe
        String sqlUsuario = "SELECT COUNT(*) FROM usuario WHERE nombre_usuario = ?";
        ps = (PreparedStatement) con.prepareStatement(sqlUsuario);
        ps.setString(1, usuario.getNombre_usuario());
        rs = ps.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe.");
            
            return false;
        }

        // Verificar si el correo existe
        String sqlCorreo = "SELECT COUNT(*) FROM informacion_usuario WHERE correo = ?";
        ps = (PreparedStatement) con.prepareStatement(sqlCorreo);
        ps.setString(1, usuario.getCorreo_usu());
        rs = ps.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            JOptionPane.showMessageDialog(null, "El correo ya existe.");
            return false;
        }

        // Insertar el nuevo usuario en la tabla usuario
        String sqlInsertUsuario = "INSERT INTO usuario (nombre_usuario, clave_usuario, estado, rol_id) VALUES (?, ?, ?, ?)";
       ps = (PreparedStatement) con.prepareStatement(sqlInsertUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, usuario.getNombre_usuario());
        ps.setString(2, usuario.getClave_usuario());
        ps.setInt(3, 1); // Estado activo
        ps.setInt(4, 2); // ID de rol para recepcionista
        int rowsAffected = ps.executeUpdate();

        // Verificar si se insertó correctamente el usuario
        if (rowsAffected > 0) {
            // Obtener el ID generado para el usuario insertado
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int usuarioId = generatedKeys.getInt(1);

                // Insertar información adicional del usuario en la tabla informacion_usuario
                String sqlInsertInfoUsuario = "INSERT INTO informacion_usuario (usuario_id, Nombres, ApePaterno, ApeMaterno, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)";
               ps = (PreparedStatement) con.prepareStatement(sqlInsertInfoUsuario);
                ps.setInt(1, usuarioId);
                ps.setString(2, usuario.getNombres_usu());
                ps.setString(3, usuario.getApellidoPaterno_usu());
                ps.setString(4, usuario.getApellidoMaterno_usu());
                ps.setString(5, usuario.getDireccion_usu());
                ps.setString(6, usuario.getTelefono_usu());
                ps.setString(7, usuario.getCorreo_usu());
                ps.executeUpdate();

                return true;
            }
        }
        return false;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } 
  }

    @Override
    public void CargarTablaRecepcionista(JTable tabla) {
        Connection con = (Connection) MySQLConexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd; 
        DefaultTableModel jTableAlumnosA = (DefaultTableModel) tabla.getModel(); 
        jTableAlumnosA.setRowCount(0);

        try {
            ps = (PreparedStatement) con.prepareStatement("SELECT usuario_id, nombre_usuario, clave_usuario FROM usuario WHERE rol_id = 2"); 
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

    public Usuario obtenerInformacionRecepcionista(Usuario usu) {
  
         Connection con ;
         PreparedStatement ps;
       

        try {
            
            con = (Connection) MySQLConexion.getConexion();
            ps = (PreparedStatement) con.prepareStatement("SELECT u.nombre_usuario, u.clave_usuario, iu.Nombres, iu.ApePaterno, iu.ApeMaterno, " +
                     "iu.direccion, iu.telefono, iu.correo " +
                     "FROM usuario u " +
                     "JOIN informacion_usuario iu ON u.usuario_id = iu.usuario_id " +
                     "WHERE u.usuario_id = ?");
            
            {

            ps.setInt(1, usu.getId_usuario());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usu.setNombre_usuario(rs.getString("nombre_usuario"));
                usu.setClave_usuario(rs.getString("clave_usuario"));
                usu.setNombres_usu(rs.getString("Nombres"));
                usu.setApellidoPaterno_usu(rs.getString("ApePaterno"));
                usu.setApellidoMaterno_usu(rs.getString("ApeMaterno"));
                usu.setDireccion_usu(rs.getString("direccion"));
                usu.setTelefono_usu(rs.getString("telefono"));
                usu.setCorreo_usu(rs.getString("correo"));
        }
             
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usu;
    }
    
    public boolean actualizarRecepcionista(Usuario usu) {
        Connection con ;
        PreparedStatement ps;
      

        try {
            
            
            con = (Connection) MySQLConexion.getConexion();
            ps = (PreparedStatement) con.prepareStatement("UPDATE usuario u " +
                     "JOIN informacion_usuario i ON u.usuario_id = i.usuario_id " +
                     "SET u.nombre_usuario = ?, " +
                     "    u.clave_usuario = ?, " +
                     "    i.Nombres = ?, " +
                     "    i.ApePaterno = ?, " +
                     "    i.ApeMaterno = ?, " +
                     "    i.direccion = ?, " +
                     "    i.telefono = ?, " +
                     "    i.correo = ? " +
                     "WHERE u.usuario_id = ?");
            
             ps.setString(1, usu.getNombre_usuario());
            ps.setString(2, usu.getClave_usuario());
            ps.setString(3, usu.getNombres_usu());
            ps.setString(4, usu.getApellidoPaterno_usu());
            ps.setString(5, usu.getApellidoMaterno_usu());
            ps.setString(6, usu.getDireccion_usu());
            ps.setString(7, usu.getTelefono_usu());
            ps.setString(8, usu.getCorreo_usu());
            ps.setInt(9, usu.getId_usuario());
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        }
            catch (SQLException e) {
                
            e.printStackTrace();
            return false;
            
        }
    }


 
       
}
