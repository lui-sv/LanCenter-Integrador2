package DAOInterfaces;

import Informacion.Equipo;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JTable;

public interface DAOEquipo {
     public void CargarTablaEquipos(JTable tabla);
     public boolean actualizarEstado(Equipo equipo);
     public Map<String, Integer> ListarComboBox(JComboBox<String> cbEstado);
     public boolean agregarEquipo(String nombre); 
}
