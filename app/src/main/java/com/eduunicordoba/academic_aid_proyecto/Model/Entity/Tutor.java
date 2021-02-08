package com.eduunicordoba.academic_aid_proyecto.Model.Entity;

import java.io.Serializable;
import java.text.ParseException;

public class Tutor extends Persona implements Serializable {
    private int id;

    private String  imagen;
    private String estado;
private String tipo_documento;
    private int paso;
    private  String genero;
    private int documento;
    private String token;
    private String presentacion;
private String nivel_academico;
private String video;
    private String contraseña;

public Tutor(){
    super();
}
    public Tutor(String nombre, String apellido,String correo, int telefono, String contraseña){
        super( nombre, apellido, correo,  telefono,  contraseña);

        this.paso=1;
        this.estado="invalido";


    }

    public String getVideo() {
        return video;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNivel_academico() {
        return nivel_academico;
    }

    public void setNivel_academico(String nivel_academico) {
        this.nivel_academico = nivel_academico;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPaso() {
        return paso;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }




}
