package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.firebase.auth.FirebaseAuth;

public class Numero_Documento_Activity extends AppCompatActivity {
private EditText e1;
private Button btn;
private Tutor_Repositorio tutor_repositorio;
FirebaseAuth auth;
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
                Intent intent=new Intent(Numero_Documento_Activity.this, Splash_Screen.class);
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
        setContentView(R.layout.activity_numero__documento_);
        String tipo = getIntent().getStringExtra("tipo_documento");
        btn=(Button)findViewById(R.id.guardar_documento);
        e1=(EditText)findViewById(R.id.numero_documento);
        tutor_repositorio=new Tutor_Repositorio(Numero_Documento_Activity.this);
     //   Toast.makeText(Numero_Documento_Activity.this,""+tipo,Toast.LENGTH_LONG).show();
        auth=FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Numero_Documento_Activity.this,""+tipo,Toast.LENGTH_LONG).show();
                int documento=Integer.parseInt(e1.getText().toString().trim());
             tutor_repositorio.ObtenerTutor(auth.getUid(), new AcademicApp<Tutor>() {
                 @Override
                 public void hecho(Tutor respuesta) {
                     int documento=Integer.parseInt(e1.getText().toString().trim());
                     respuesta.setDocumento(documento);
                     respuesta.setTipo_documento(tipo);
                     respuesta.setPaso(4);
                     tutor_repositorio.Editar(respuesta, new AcademicApp<Boolean>() {
                         @Override
                         public void hecho(Boolean respuesta) {
Intent intent=new Intent(Numero_Documento_Activity.this,Asignaturas_Activity.class);
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
    public void Alerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Numero_Documento_Activity.this);
// Add the buttons
     //   builder.setMessage("")
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

// Set other dialog properties


// Create the AlertDialog
        AlertDialog dialog = builder.create();
    }
}