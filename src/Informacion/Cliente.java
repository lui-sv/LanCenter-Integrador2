package Informacion;

public class Cliente {
    String DNICliente, NombreCliente, CorreoCliente,apePaterno, apeMaterno;
    int MinutosGuardados;

    public String getDNICliente() {
        return DNICliente;
    }

    public void setDNICliente(String DNICliente) {
        this.DNICliente = DNICliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public String getApePaterno() { 
        return apePaterno; 
    }
    
    public void setApePaterno(String apePaterno) { 
        this.apePaterno = apePaterno; 
    }

    public String getApeMaterno() { 
        return apeMaterno; 
    }
    
    public void setApeMaterno(String apeMaterno) { 
        this.apeMaterno = apeMaterno; 
    }    
    
    public String getCorreoCliente() {
        return CorreoCliente;
    }

    public void setCorreoCliente(String CorreoCliente) {
        this.CorreoCliente = CorreoCliente;
    }

    public int getMinutosGuardados() {
        return MinutosGuardados;
    }

    public void setMinutosGuardados(int MinutosGuardados) {
        this.MinutosGuardados = MinutosGuardados;
    }
    
}
