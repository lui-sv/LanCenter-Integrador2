package Informacion;

import java.time.LocalDate;
import java.time.LocalTime;

public class Control_Tiempo {
     Usuario usuario;
     Cliente cliente;
     Equipo equipo;
     LocalDate fechaAlquilerInicial;
     LocalDate fechaAlquilerFinal;
     LocalTime horaInicio;
     double Precio;
     LocalTime horaFinalEstimada;
     int tiempoRestante,tiempoAgregador, id_Alquiler; // en minutos

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public int getTiempoAgregador() {
        return tiempoAgregador;
    }

    public void setTiempoAgregador(int tiempoAgregador) {
        this.tiempoAgregador = tiempoAgregador;
    }

    public int getId_Alquiler() {
        return id_Alquiler;
    }

    public void setId_Alquiler(int id_Alquiler) {
        this.id_Alquiler = id_Alquiler;
    }

    // Getters and Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public LocalDate getFechaAlquilerInicial() {
        return fechaAlquilerInicial;
    }

    public void setFechaAlquilerInicial(LocalDate fechaAlquilerInicial) {
        this.fechaAlquilerInicial = fechaAlquilerInicial;
    }

    public LocalDate getFechaAlquilerFinal() {
        return fechaAlquilerFinal;
    }

    public void setFechaAlquilerFinal(LocalDate fechaAlquilerFinal) {
        this.fechaAlquilerFinal = fechaAlquilerFinal;
    }

 

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }



    public LocalTime getHoraFinalEstimada() {
        return horaFinalEstimada;
    }

    public void setHoraFinalEstimada(LocalTime horaFinalEstimada) {
        this.horaFinalEstimada = horaFinalEstimada;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
}
