package com.eduunicordoba.academic_aid_proyecto.Model.Entity;

public class Horario {
    private String dia;
    private String hora;
    private String id_tutor;
    private int id;
    private String token;
    private Tutor tutor;

    public Horario(String dia,String hora,String id_tutor){
this.dia=dia;
this.hora=hora;
this.id_tutor=id_tutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Horario(){

    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(String id_tutor) {
        this.id_tutor = id_tutor;
    }
}
