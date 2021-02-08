package com.eduunicordoba.academic_aid_proyecto.Model.Entity;

import java.util.ArrayList;

public class Materia {
    private String nombre_materia;
    private String id_tutor;
    private String token_materia;
    private String subarea;
    private Tutor tutor;




    public String getNombre_materia() {
        return nombre_materia;
    }
    public Materia(){

    }

    public Materia(String nombre,String subarea,String id_tutor){
        this.nombre_materia=nombre;
        this.subarea=subarea;
        this.id_tutor=id_tutor;

        this.token_materia="";
    }

    public String getSubarea() {
        return subarea;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public String getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(String id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getToken_materia() {
        return token_materia;
    }

    public void setToken_materia(String token_materia) {
        this.token_materia = token_materia;
    }

    @Override
    public String toString() {
        return  tutor.getNombre();
    }
}
