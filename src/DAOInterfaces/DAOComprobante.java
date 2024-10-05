package DAOInterfaces;

import Informacion.Comprobante;
import java.util.List;

public interface DAOComprobante {
    List<Comprobante> obtenerTodos();
    List<Comprobante> filtrarPorTipo(String tipo);
    List<Comprobante> filtrarPorFecha(String fecha);
    List<Comprobante> obtenerComprobantes(String sql, Object... params);
}
    