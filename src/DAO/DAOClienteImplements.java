package DAO;

import Informacion.Cliente;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.MySQLConexion;
import DAOInterfaces.DAOCliente;

public class DAOClienteImplements implements DAOCliente {

    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE dni != 0";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDNICliente(rs.getString("dni"));
                cliente.setNombreCliente(rs.getString("nombres"));
                cliente.setApePaterno(rs.getString("ApePaterno"));
                cliente.setApeMaterno(rs.getString("ApeMaterno"));
                cliente.setCorreoCliente(rs.getString("correo"));
                cliente.setMinutosGuardados(rs.getInt("Minutos_Guardados"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean registrarCliente(Cliente cliente) {
        if (existeDNI(cliente.getDNICliente())) {
            // DNI ya existe
            return false;
        }

        String sql = "INSERT INTO cliente (nombres, ApePaterno, ApeMaterno, correo, dni) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getApePaterno());
            ps.setString(3, cliente.getApeMaterno());
            ps.setString(4, cliente.getCorreoCliente());
            ps.setString(5, cliente.getDNICliente());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean existeDNI(String dni) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE dni = ?";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
            String sql = "UPDATE cliente SET nombres=?, ApePaterno=?, ApeMaterno=?, correo=? WHERE dni=?";
        try (Connection con = (Connection) MySQLConexion.getConexion();
             PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getApePaterno());
            ps.setString(3, cliente.getApeMaterno());
            ps.setString(4, cliente.getCorreoCliente());
            ps.setString(5, cliente.getDNICliente());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}