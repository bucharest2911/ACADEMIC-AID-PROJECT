package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.firebase.auth.FirebaseAuth;

public class Genero_Activity extends AppCompatActivity {
    private FirebaseAuth auth;
    private RadioButton rd1;
    private RadioButton rd2;
    private RadioButton rd3;
    private Button btn;

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
                Intent intent=new Intent(Genero_Activity.this,  Splash_Screen.class);
                startActivity(intent);
                finish();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }
private Tutor_Repositorio tutor_repositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero_);
        rd1=(RadioButton)findViewById(R.id.genero_masculino);
        rd2=(RadioButton)findViewById(R.id.genero_femenino);
        rd3=(RadioButton)findViewById(R.id.genero_otro);
        btn=(Button)findViewById(R.id.aceptar_genero);
        auth=FirebaseAuth.getInstance();
        tutor_repositorio=new Tutor_Repositorio(Genero_Activity.this);



    }
    public void EditarGenero(View view){
        if(rd1.isChecked()==true){
        Asignar("masculino");
        }
        if(rd2.isChecked()==true){
Asignar("femenino");
        }
        if(rd3.isChecked()==true){
            Asignar("Otro");
        }
    }
    public void Asignar(String genero){
       tutor_repositorio.ObtenerTutor(auth.getUid(), new AcademicApp<Tutor>() {
           @Override
           public void hecho(Tutor respuesta) {
               respuesta.setGenero(genero);
               respuesta.setPaso(3);
               tutor_repositorio.Editar(respuesta, new AcademicApp<Boolean>() {
                   @Override
                   public void hecho(Boolean respuesta) {
                       Intent intent=new Intent(Genero_Activity.this,Tipo_documento_tutor.class);
                       startActivity(intent);
                       finish();
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
}