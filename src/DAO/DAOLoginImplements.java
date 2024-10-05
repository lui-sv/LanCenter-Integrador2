package DAO;
import Informacion.Usuario;
import util.MySQLConexion;
import DAOInterfaces.DAOLogin;
import Informacion.Usuario;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class DAOLoginImplements implements DAOLogin{
    private static Map<String, Integer> intentosFallidosMap = new HashMap<>();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int intentarlogin(Usuario usuario) {
        int resultado = 0;
        String sql = "SELECT usuario_id, estado FROM usuario WHERE BINARY nombre_usuario = ? AND clave_usuario = ?";
        String sqlInfoUsuario = "SELECT Nombres, ApePaterno, ApeMaterno, direccion, telefono, correo " +
                                "FROM informacion_usuario WHERE usuario_id = ?";

        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
             PreparedStatement psInfo = (PreparedStatement) con.prepareStatement(sqlInfoUsuario)) {

            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getClave_usuario());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                int estado = rs.getInt("estado");

                if (estado == 1) {
                    resultado = 1;
                    
                    psInfo.setInt(1, rs.getInt("usuario_id"));
                    ResultSet rsInfo = psInfo.executeQuery();

                    if (rsInfo.next()) {
                        usuario.setId_usuario(rs.getInt("usuario_id"));
                        usuario.setNombres_usu(rsInfo.getString("Nombres"));
                        usuario.setApellidoPaterno_usu(rsInfo.getString("ApePaterno"));
                        usuario.setApellidoMaterno_usu(rsInfo.getString("ApeMaterno"));
                        usuario.setDireccion_usu(rsInfo.getString("direccion"));
                        usuario.setTelefono_usu(rsInfo.getString("telefono"));
                        usuario.setCorreo_usu(rsInfo.getString("correo"));
                    }
                    intentosFallidosMap.put(usuario.getNombre_usuario(), 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Esta cuenta se encuentra bloqueada");
                    resultado = -1;
                }
            } else {
                sumarintentos(usuario);
                int intentos = intentosFallidosMap.getOrDefault(usuario.getNombre_usuario(), 0);
                if (intentos >= 3) {
                    bloquearUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Su cuenta ha sido bloqueada.");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public void sumarintentos(Usuario usuario) {
        String nombreUsuario = usuario.getNombre_usuario();
        int intentos = intentosFallidosMap.getOrDefault(nombreUsuario, 0);
        intentos++;
        intentosFallidosMap.put(nombreUsuario, intentos);
    }

    @Override
    public void bloquearUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET estado = 0 WHERE nombre_usuario = ?";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre_usuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    @Override
    public String obtenerRolUsuario(Usuario usuario) {
        String rol = "";
        try (Connection con = (Connection) MySQLConexion.getConexion()) {
            String sql = "SELECT r.nombre FROM usuario u "
                       + "JOIN rol r ON u.rol_id = r.rol_id "
                       + "WHERE u.nombre_usuario = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre_usuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rol = rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }
 
}
