package Informacion;


public class Usuario {
    String nombre_usuario, clave_usuario, nombre_rol;
    boolean estado_usu;
    int id_usuario, id_rol, intentos_fallidos;
    //--------Informacion Usuario----
    String direccion_usu, telefono_usu, correo_usu, apellidoMaterno_usu, apellidoPaterno_usu, nombres_usu;

    public int getIntentos_fallidos() {
        return intentos_fallidos;
    }

    public void setIntentos_fallidos(int intentos_fallidos) {
        this.intentos_fallidos = intentos_fallidos;
    }
    
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public boolean isEstado_usu() {
        return estado_usu;
    }

    public void setEstado_usu(boolean estado_usu) {
        this.estado_usu = estado_usu;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
    
    public String getNombres_usu() {
        return nombres_usu;
    }

    public void setNombres_usu(String nombre) {
        this.nombres_usu = nombre;
    }    
    
    public String getApellidoPaterno_usu() {
        return apellidoPaterno_usu;
    }

    public void setApellidoPaterno_usu(String apellidoPaterno) {
        this.apellidoPaterno_usu = apellidoPaterno;
    }    

    public String getApellidoMaterno_usu() {
        return apellidoMaterno_usu;
    }

    public void setApellidoMaterno_usu(String apellidoMaterno) {
        this.apellidoMaterno_usu = apellidoMaterno;
    }    
    
    public String getDireccion_usu() {
        return direccion_usu;
    }

    public void setDireccion_usu(String direccion_usu) {
        this.direccion_usu = direccion_usu;
    }

    public String getTelefono_usu() {
        return telefono_usu;
    }

    public void setTelefono_usu(String telefono_usu) {
        this.telefono_usu = telefono_usu;
    }

    public String getCorreo_usu() {
        return correo_usu;
    }

    public void setCorreo_usu(String correo_usu) {
        this.correo_usu = correo_usu;
    }

}