package DAO;

import DAOInterfaces.DAOComprobante;
import Informacion.Cliente;
import Informacion.Comprobante;
import Informacion.Equipo;
import util.MySQLConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOComprobanteImplements implements DAOComprobante {

    @Override
    public List<Comprobante> obtenerComprobantes(String sql, Object... params) {
        List<Comprobante> lista = new ArrayList<>();
        try (Connection con = MySQLConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comprobante comprobante = new Comprobante();
                    comprobante.setId_comprobante(rs.getInt("comprobante_id"));
                    comprobante.setMonto_Comprobante(rs.getDouble("monto"));
                    comprobante.setFechaComprobante(rs.getDate("fecha_pago").toLocalDate());

                    Equipo equipo = new Equipo();
                    equipo.setNombre_equipo(rs.getString("nombre_equipo"));
                    comprobante.setEquipo(equipo);

                    Cliente cliente = new Cliente();
                    cliente.setNombreCliente(rs.getString("nombre_cliente")); // Asumiendo que "nombre_cliente" es el campo correcto
                    comprobante.setCliente(cliente);

                    comprobante.setTipo_Comprobante(rs.getString("tipo_comprobante"));

                    lista.add(comprobante);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Comprobante> obtenerTodos() {
        String sql = "SELECT cp.comprobante_id, cp.alquiler_id, e.nombre AS nombre_equipo, cl.nombres AS nombre_cliente, cp.fecha_pago, cp.monto, cp.tipo_comprobante "
                + "FROM comprobante_pago cp "
                + "INNER JOIN control_alquiler ca ON cp.alquiler_id = ca.alquiler_id "
                + "LEFT JOIN equipo e ON ca.equipo_id = e.equipo_id "
                + "LEFT JOIN cliente cl ON ca.cliente_id = cl.cliente_id";
        return obtenerComprobantes(sql);
    }

    @Override
    public List<Comprobante> filtrarPorTipo(String tipo) {
        String sql = "SELECT cp.comprobante_id, cp.alquiler_id, e.nombre AS nombre_equipo, cl.nombres AS nombre_cliente, cp.fecha_pago, cp.monto, cp.tipo_comprobante "
                + "FROM comprobante_pago cp "
                + "INNER JOIN control_alquiler ca ON cp.alquiler_id = ca.alquiler_id "
                + "LEFT JOIN equipo e ON ca.equipo_id = e.equipo_id "
                + "LEFT JOIN cliente cl ON ca.cliente_id = cl.cliente_id "
                + "WHERE cp.tipo_comprobante = ?";
        return obtenerComprobantes(sql, tipo);
    }

    @Override
    public List<Comprobante> filtrarPorFecha(String fecha) {
        String sql = "SELECT cp.comprobante_id, cp.alquiler_id, e.nombre AS nombre_equipo, cl.nombres AS nombre_cliente, cp.fecha_pago, cp.monto, cp.tipo_comprobante "
                + "FROM comprobante_pago cp "
                + "INNER JOIN control_alquiler ca ON cp.alquiler_id = ca.alquiler_id "
                + "LEFT JOIN equipo e ON ca.equipo_id = e.equipo_id "
                + "LEFT JOIN cliente cl ON ca.cliente_id = cl.cliente_id "
                + "WHERE cp.fecha_pago = ?";
        return obtenerComprobantes(sql, fecha);
    }
}