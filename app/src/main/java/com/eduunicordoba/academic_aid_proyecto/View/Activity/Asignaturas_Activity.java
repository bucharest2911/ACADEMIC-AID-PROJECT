package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Materia;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Asignaturas_Activity extends AppCompatActivity {
    private ArrayList<CheckBox> checkboxes;
    private CheckBox matematica;
    private CheckBox fisica;
    private CheckBox quimica;
    private CheckBox csociales;
    private CheckBox PHP;
    private CheckBox java;
    private  CheckBox html;
    private CheckBox javascript;
    private CheckBox python;
    private Button btn;
    private Materia materia;
    private FirebaseAuth auth;
    private static final String cbasicas="Ciencias Basicas";
    private static final String programacion="Programacion";
    private Tutor_Repositorio tutor_repositorio;
    private Tutor tutor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cerrar_mi_sesion:
                auth.signOut();
                Intent intent=new Intent(Asignaturas_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas_);
        AsignarElementos();
        auth = FirebaseAuth.getInstance();
        tutor_repositorio=new Tutor_Repositorio(Asignaturas_Activity.this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Validar(matematica, cbasicas);
Validar(PHP,programacion);

Validar(csociales, cbasicas);
Validar(quimica, cbasicas);
Validar(fisica,cbasicas);
Validar(javascript,programacion);
Validar(java,programacion);
Validar(html,programacion);

tutor_repositorio.ObtenerTutor(auth.getUid(), new AcademicApp<Tutor>() {
    @Override
    public void hecho(Tutor respuesta) {
        respuesta.setPaso(5);

        tutor_repositorio.Editar(respuesta, new AcademicApp<Boolean>() {
            @Override
            public void hecho(Boolean respuesta) {
 Intent intent=new Intent(Asignaturas_Activity.this, Informacion_academica.class);

 startActivity(intent);
            }

            @Override
            public void error(Exception ex) {

            }
        });
    }

    @Override
    public void error(Exception ex) {

    }
});
            }
        });

    }

    public void AsignarElementos() {
        matematica = (CheckBox) findViewById(R.id.c_matematicas_t);
        fisica = (CheckBox) findViewById(R.id.c_fisica_t);
        quimica = (CheckBox) findViewById(R.id.c_quimica_t);
        csociales = (CheckBox) findViewById(R.id.c_sociales_t);
        PHP = (CheckBox) findViewById(R.id.c_php_t);
        java = (CheckBox) findViewById(R.id.c_java_t);
        javascript = (CheckBox) findViewById(R.id.c_java_script_t);
        btn = (Button) findViewById(R.id.guardar_asignatura);
        html=(CheckBox)findViewById(R.id.c_html_t);

    }

    public void Validar(CheckBox area,String nombre) {
        if (area.isChecked()) {
            materia = new Materia(nombre, area.getText().toString().trim(), auth.getUid());
            tutor_repositorio.GuardarAsignatura(materia, new AcademicApp<Boolean>() {
                @Override
                public void hecho(Boolean respuesta) {
                  //  Toast.makeText(Asignaturas_Activity.this,""+materia.getNombre_materia(),Toast.LENGTH_LONG).show();
                }

                @Override
                public void error(Exception ex) {

                }
            });

        }
    }
}