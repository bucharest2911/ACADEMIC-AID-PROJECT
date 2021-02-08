package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eduunicordoba.academic_aid_proyecto.R;

public class Mensaje extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
        btn=(Button)findViewById(R.id.aceptar_mensaje);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mensaje.this, Genero_Activity.class);
                startActivity(intent);
            }
        });
    }
}