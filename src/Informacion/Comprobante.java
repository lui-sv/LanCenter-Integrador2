package Informacion;

import java.time.LocalDate;

public class Comprobante {
    int id_comprobante;
    String tipo_Comprobante;
    double monto_Comprobante;
    Control_Tiempo control_tiempo;
    LocalDate fechaComprobante;
    int id_alquiler; 
    private Equipo equipo;
    private Cliente cliente;
    
    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public String getTipo_Comprobante() {
        return tipo_Comprobante;
    }

    public void setTipo_Comprobante(String tipo_Comprobante) {
        this.tipo_Comprobante = tipo_Comprobante;
    }

    public double getMonto_Comprobante() {
        return monto_Comprobante;
    }

    public void setMonto_Comprobante(double monto_Comprobante) {
        this.monto_Comprobante = monto_Comprobante;
    }

    public Control_Tiempo getControl_tiempo() {
        return control_tiempo;
    }

    public void setControl_tiempo(Control_Tiempo control_tiempo) {
        this.control_tiempo = control_tiempo;
    }

    public LocalDate getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(LocalDate fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }
    public int getId_alquiler() {
        return id_alquiler;
    }

    public void setId_alquiler(int id_alquiler) {
        this.id_alquiler = id_alquiler;
    }    
    
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
