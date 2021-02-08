package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eduunicordoba.academic_aid_proyecto.R;

public class Sesion_Estudiante extends AppCompatActivity {
private Button btn;
private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion__estudiante);
        btn=(Button)findViewById(R.id.iniciar_sesion_estudiante);
        btn2=(Button)findViewById(R.id.crear_cuenta_estudiante);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(Sesion_Estudiante.this, Inicio_Estudiante.class);
               startActivity(intent);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sesion_Estudiante.this, Acceso_Estudiante.class);
                startActivity(intent);
            }
        });
    }
}