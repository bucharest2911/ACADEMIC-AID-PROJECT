package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Estudiante;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.eduunicordoba.academic_aid_proyecto.View.Navegacion.InicioNavEstudiante;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Acceso_Estudiante extends AppCompatActivity {
private EditText cnombre;
private EditText ccorreo;
private  EditText ccontraseña;

private EditText cctelefono;
private EditText apellido;
private Button btn;
private FirebaseAuth auth;
   private  FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso__estudiante);
        cnombre=(EditText)findViewById(R.id.et_nombre_estudiante_registro);
        ccorreo=(EditText)findViewById(R.id.et_correo_estudiante_registro);
        ccontraseña=(EditText)findViewById(R.id.et_pass_estudiante_registro);
        cctelefono=(EditText)findViewById(R.id.et_telefono);
        btn=(Button)findViewById(R.id.bt_registro_estudiante);
        apellido=(EditText)findViewById(R.id.apellido_registro);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre=cnombre.getText().toString().trim();
                String correo=ccorreo.getText().toString().trim();
                String contraseña=ccontraseña.getText().toString().trim();
                int telefono=Integer.parseInt(cctelefono.getText().toString().trim());
                String apellido_est=apellido.getText().toString().trim();
                if(nombre.isEmpty()||telefono==0 ||  contraseña.isEmpty()|| correo.isEmpty()  ){
                    alertaMensaje("campos vacios");
                }else{
                    if(contraseña.length()<8){
                        alertaMensaje("la contraseña debe ser al menos de 8 caracteres");
                    }else{

                            Estudiante estudiante=new Estudiante(nombre,apellido_est,correo,telefono,contraseña);
                            auth.createUserWithEmailAndPassword(estudiante.getCorreo(),estudiante.getContraseña()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        db.collection("Estudiante").document(auth.getUid()).set(estudiante).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Intent intent=new Intent(Acceso_Estudiante.this, Estudiante_Navegacion.class);
                                                    //  Toast.makeText(Acceso_Estudiante.this,"Hecho"+task.getException(),Toast.LENGTH_LONG).show();
                                                   startActivity(intent);
                                                }else{
                                                    //   Toast.makeText(Acceso_Estudiante.this,"Error"+task.getException(),Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    }else Toast.makeText(Acceso_Estudiante.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            });



                    }
                }


            }
        });
    }
    public void alertaMensaje(String mensaje){
        AlertDialog alertDialog = new AlertDialog.Builder(Acceso_Estudiante.this).create();
        alertDialog.setTitle("ERROR");
        alertDialog.setMessage(mensaje);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        //  Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
    }
    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    }
