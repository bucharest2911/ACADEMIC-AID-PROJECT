package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.View.Navegacion.Navegacion_Tutor;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Informacion_academica extends AppCompatActivity {
    private Dialog nivelacademico;
    private Dialog video;
    private Dialog presentacion;
    private EditText c_nivelacademico;
    private EditText c_presentacion;
    private EditText c_video;
    private TextView f_nivelacademico;
    private   TextView f_presentacion;
    private TextView f_video;
    private static final int foto=100;
    private ImageView imageView;
    FirebaseStorage storage;
    FirebaseAuth auth;
    StorageReference storageReference;
    private Button btn_dialog_nivelacademico;
    private Button btn_dialog_presentacion;
    private Button btn_dialog_video;
    private Button button;
    private Button btn_informacion;
    private Uri filePath;
    private Tutor tutor;
    private Tutor_Repositorio tutor_repositorio;

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
                Intent intent=new Intent(Informacion_academica.this, Splash_Screen.class);
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

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        auth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_informacion_academica);
nivelacademico=new Dialog(Informacion_academica.this);
presentacion=new Dialog(Informacion_academica.this);
video=new Dialog(Informacion_academica.this);
btn_informacion=(Button)findViewById(R.id.boton_recuperar);
tutor_repositorio=new Tutor_Repositorio(Informacion_academica.this);
imageView=(ImageView)findViewById(R.id.imagen_del_tutor);
tutor_repositorio.ObtenerTutor(auth.getUid(), new AcademicApp<Tutor>() {
    @Override
    public void hecho(Tutor respuesta) {
        if(respuesta.getPaso()==6){
            Cargar(respuesta);


            btn_informacion.setText("Actualizar");
        }
    }

    @Override
    public void error(Exception ex) {

    }
});





        AsociarElementos();
        AsignarAlertas();

        Aceptar(R.id.btn_nivel_academico,nivelacademico,f_nivelacademico,1);
       Aceptar(R.id.aceptar_presentacion,presentacion,f_presentacion,2);
        Aceptar(R.id.aceptar_video,video,f_video,3);








        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
showFileChooser();
            }
        });
        btn_informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tutor_repositorio.ObtenerTutor(auth.getUid(), new AcademicApp<Tutor>() {
                    @Override
                    public void hecho(Tutor respuesta) {


                            String nivelacademico=f_nivelacademico.getText().toString().trim();
                            String presentacion=f_presentacion.getText().toString().trim();
                            String video=f_video.getText().toString().trim();
                            respuesta.setNivel_academico(nivelacademico);
                            respuesta.setPresentacion(presentacion);
                            respuesta.setVideo(video);
                        respuesta.setPaso(6);


                            tutor_repositorio.Editar(respuesta, new AcademicApp<Boolean>() {
                                @Override
                                public void hecho(Boolean respuesta) {
                                    Intent intent=new Intent(Informacion_academica.this,Navegacion_Tutor.class);

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
        });
    }


    public void Desplegar(TextView texto,Dialog dialog){
texto.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dialog.show();

    }
});
    }
public void    AsociarElementos(){
        f_nivelacademico=(TextView) findViewById(R.id.crear_nivel_academico);
        f_presentacion=(TextView)findViewById(R.id.crear_presentacion);
        f_video=(TextView)findViewById(R.id.crear_presentacion_video);
        imageView=(ImageView)findViewById(R.id.imagen_tutor);


    }
    public void AsignarAlertas(){
        nivelacademico.setContentView(R.layout.t_dialogo_nivelacademico);
        presentacion.setContentView(R.layout.t_dialogo_presentacion);
        video.setContentView(R.layout.t_dialogo_videopresentacion);
        Desplegar(f_nivelacademico,nivelacademico);
        Desplegar(f_presentacion,presentacion);
        Desplegar(f_video,video);

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), foto);
    }

    public void Cargar(Tutor respuesta){
        f_nivelacademico.setText(respuesta.getNivel_academico());
        f_presentacion.setText(respuesta.getPresentacion());
        f_video.setText(respuesta.getVideo());
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == foto && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

//Toast.makeText(Informacion_academica.this,""+filePath,Toast.LENGTH_LONG).show();
            StorageReference riversRef = storageReference.child(auth.getUid());

         UploadTask   uploadTask = riversRef.putFile(filePath);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                 riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                     @Override
                     public void onSuccess(Uri uri) {

                     }
                 });
                }
            });
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Aceptar(int id,Dialog dialogo,TextView texto ,int editext){
        button=(Button)dialogo.findViewById(id);

c_nivelacademico=(EditText)nivelacademico.findViewById(R.id.tv_nivel_academico);
       c_presentacion=(EditText)presentacion.findViewById(R.id.tv_dialog_presentaciontutor);
        c_video=(EditText)video.findViewById(R.id.tv_videopresentacion);



if(button!=null){

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

if(editext==1){
    texto.setText(c_nivelacademico.getText().toString().trim());
}
if(editext==2){
    texto.setText(c_presentacion.getText().toString().trim());
}
if(editext==3){
    texto.setText(c_video.getText().toString().trim());
}
dialogo.cancel();
           // Toast.makeText(Informacion_academica.this,"holiii",Toast.LENGTH_LONG).show()
            ;
        }
    });
}

    }

    public void Cancelar(Button btn,Dialog dialog){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
}