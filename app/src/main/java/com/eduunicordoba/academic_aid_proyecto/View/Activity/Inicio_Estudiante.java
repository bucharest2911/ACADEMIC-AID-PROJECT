package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Inicio_Estudiante extends AppCompatActivity {
private EditText inicio;
private EditText contraseña;
private Button btn;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__estudiante);
        inicio=(EditText)findViewById(R.id.correo_recuperar);
        contraseña=(EditText)findViewById(R.id.et_pass_estudiante_sesion);
        btn=(Button)findViewById(R.id.bt_sesion_estudiante);
        auth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c_inicio=inicio.getText().toString().trim();
                String c_contraseña=contraseña.getText().toString().trim();
                auth.signInWithEmailAndPassword(c_inicio,c_contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Inicio_Estudiante.this,"Hecho",Toast.LENGTH_LONG).show();
Intent intent=new Intent(Inicio_Estudiante.this, Estudiante_Navegacion.class);
startActivity(intent);

                        }else{
                            Toast.makeText(Inicio_Estudiante.this,"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}