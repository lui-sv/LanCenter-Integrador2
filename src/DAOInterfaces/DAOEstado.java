package DAOInterfaces;
import Informacion.Equipo;
import java.util.List;

public interface DAOEstado {
    List<Equipo> obtenerTodos();
    Equipo obtenerPorId(int id);
    boolean agregar(Equipo equipo);
    boolean actualizar(Equipo equipo);
    boolean eliminar(int id);
}
