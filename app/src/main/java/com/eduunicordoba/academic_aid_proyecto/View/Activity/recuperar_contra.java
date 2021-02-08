package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class recuperar_contra extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText inputEmail;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);
        inputEmail=(EditText)findViewById(R.id.correo_recuperar);
        auth = FirebaseAuth.getInstance();
        btn=(Button)findViewById(R.id.boton_recuperar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.sendPasswordResetEmail(inputEmail.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    AlertDialog alertDialog = new AlertDialog.Builder(recuperar_contra.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setMessage("Se te ha enviado un mensaje a tu correo sigue los pasos que el te proporcionara");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                    //  Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else { Toast.makeText(recuperar_contra.this, "Ha ocurrido un error!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });

}
}