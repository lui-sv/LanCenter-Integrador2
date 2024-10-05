package DAO;

import DAOInterfaces.DAOEstado;
import Informacion.Equipo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import util.MySQLConexion;
import java.sql.ResultSet;

public class DAOEstadoImplements implements DAOEstado {

    @Override
    public List<Equipo> obtenerTodos() {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM estado_equipo";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setEstado_id_equipo(rs.getInt("estado_id"));
                equipo.setNombre_estado_equipo(rs.getString("descripcion"));
                lista.add(equipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Equipo obtenerPorId(int id) {
        Equipo equipo = null;
        String sql = "SELECT * FROM estado_equipo WHERE estado_id = ?";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    equipo = new Equipo();
                    equipo.setEstado_id_equipo(rs.getInt("estado_id"));
                    equipo.setNombre_estado_equipo(rs.getString("descripcion"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipo;
    }

    @Override
    public boolean agregar(Equipo equipo) {
        String sql = "INSERT INTO estado_equipo (descripcion) VALUES (?)";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre_estado_equipo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Equipo equipo) {
        String sql = "UPDATE estado_equipo SET descripcion = ? WHERE estado_id = ?";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre_estado_equipo());
            ps.setInt(2, equipo.getEstado_id_equipo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM estado_equipo WHERE estado_id = ?";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }    
}
