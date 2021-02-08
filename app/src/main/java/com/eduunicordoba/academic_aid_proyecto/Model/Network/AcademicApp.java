package com.eduunicordoba.academic_aid_proyecto.Model.Network;

public interface AcademicApp <T>{

    void hecho(T respuesta);
 void error(Exception ex);
}
