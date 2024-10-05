package DAOInterfaces;

import Informacion.Usuario;
import javax.swing.JTable;

public interface DAORecepcionista {
    public boolean agregarRecepcionista(Usuario usuario);
    public Usuario obtenerInformacionRecepcionista(Usuario usu);
     public boolean actualizarRecepcionista(Usuario usu);

    public void CargarTablaRecepcionista(JTable tabla);
    

}
