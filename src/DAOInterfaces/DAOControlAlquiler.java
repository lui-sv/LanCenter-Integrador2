package DAOInterfaces;

import Informacion.Control_Tiempo;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JTable;

public interface DAOControlAlquiler {

    public void ListarControlAlquiler(Control_Tiempo ControlTiempo, JTable JT);

    public void ActualizarEstadoEquipo(Control_Tiempo ControlTiempo);

    public boolean InsertarAlquiler(Control_Tiempo ControlTiempo);

    public boolean ActualizarAlquiler(Control_Tiempo ControlTiempo);

    public void ActualizarEstadosAutomatica(Control_Tiempo ControlTiempo);

    public void ActualizarEstadoEquipoLibre(Control_Tiempo ControlTiempo);

    public void ActualizarEstadoEquipoControl(Control_Tiempo ControlTiempo);

    public void ActualizarMinutos(Control_Tiempo ControlTiempo);

    public boolean CobrarAlquiler(Control_Tiempo ControlTiempo);

    public Map<String, Integer> ComboEquipos(JComboBox JC);

    public Map<String, Integer> ComboClientes(JComboBox JC);

    public Map<String, Integer> ListarIDEq(Control_Tiempo CT);
}
