package com.eduunicordoba.academic_aid_proyecto.Model.Entity;

public class Persona {
    private String correo;

    private String estado;
    private int telefono;
    private String apellido;
    private String nombre;
    private String contraseña;
public Persona(){
    super();
}

    public Persona(String nombre, String apellido,String correo, int telefono, String contraseña){
        super();
        this.nombre=nombre;
this.apellido=apellido;
        this.correo=correo;
        this.telefono=telefono;

        this.contraseña=contraseña;



    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
