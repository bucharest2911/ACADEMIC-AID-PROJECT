package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.R;

public class Tipo_documento_tutor extends AppCompatActivity {
private RadioButton rd_registroc;
private RadioButton rd_cedula;
private RadioButton rd_pasaporte;
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_documento_tutor);
        rd_registroc=(RadioButton)findViewById(R.id.radio_pasaporte);
        rd_cedula=(RadioButton)findViewById(R.id.radio_cedula);
        rd_pasaporte=(RadioButton)findViewById(R.id.radio_pasaporte);
btn=(Button)findViewById(R.id.aceptar_mensaje);
 habilitarBoton();


    }


    public void enviarActivity(String valor){
        Intent intent=new Intent(Tipo_documento_tutor.this, Numero_Documento_Activity.class);
        intent.putExtra("tipo_documento",valor);
        startActivity(intent);


    }
    public void pasar(View view) {

        if (rd_registroc.isChecked()) {
            Toast.makeText(Tipo_documento_tutor.this, "Hola", Toast.LENGTH_LONG).show();
            enviarActivity("registrocivil");

        }
        if (rd_cedula.isChecked()) {
            enviarActivity("Cedula");
        }
        if (rd_pasaporte.isChecked()) {
            enviarActivity("Pasaporte");
        }
    }
    public void Habilitar(){
        btn.setEnabled(true);
    }
    public void Deshabilitar(){
        btn.setEnabled(false);
    }
    public void habilitarBoton( ){


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiotipodedocumento);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            Habilitar();

            }
        });




    }
}