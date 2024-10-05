package DAOInterfaces;

import Informacion.Cliente;
import java.util.List;

public interface DAOCliente {
    List<Cliente> obtenerClientes();
    boolean registrarCliente(Cliente cliente);
    boolean existeDNI(String dni);
    boolean modificarCliente(Cliente cliente);
}
