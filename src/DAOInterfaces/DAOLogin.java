package DAOInterfaces;
import Informacion.Usuario;

public interface DAOLogin {
     public int intentarlogin(Usuario usuario);
     public void sumarintentos(Usuario usuario);
     public void bloquearUsuario(Usuario usuario);
     public String obtenerRolUsuario(Usuario usuario);
}
