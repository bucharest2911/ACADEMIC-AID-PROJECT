package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Tutor_perfil_estudiante extends AppCompatActivity {
private Tutor tutor;
private TextView tcorreo;
private  TextView ttelefono;
private  TextView ttitulo;
private  TextView tpresentacion;
private FirebaseFirestore firestore;
private FirebaseAuth auth;
 private Tutor_Repositorio tutor_repositorio;
 private Button btn;
 private ImageView imagen;
private RatingBar rating;
private  TextView tvideo;


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
                Intent intent=new Intent(Tutor_perfil_estudiante.this,  Splash_Screen.class);
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

        setContentView(R.layout.activity_perfil_tutor_estudiante);

        tutor_repositorio=new Tutor_Repositorio(Tutor_perfil_estudiante.this);
        tutor=(Tutor) getIntent().getSerializableExtra("Tutor");
        tcorreo=(TextView)findViewById(R.id.tv_correo_tutor_es);
        ttelefono=(TextView)findViewById(R.id.tvtelefono_tutor_es);
        ttitulo=(TextView)findViewById(R.id.tvtitulo_tutor_es);
        tpresentacion=(TextView)findViewById(R.id.tv_presentacion_tutor_es);
        tvideo=(TextView)findViewById(R.id.edtvpresentvideo_per_tutor);
        btn=(Button)findViewById(R.id.llamar);
        imagen=(ImageView)findViewById(R.id.imagen_del_tutor);
rating=(RatingBar)findViewById(R.id.ratingBar_es);
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference imageRef=storage.getReference();
        imageRef.child("images"+tutor.getToken());
        imageRef.getBytes(1024*768).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imagen.setImageBitmap(bitmap);
            }
        });


        tutor_repositorio.ObtenerTutor(tutor.getToken(), new AcademicApp<Tutor>() {
       @Override
       public void hecho(Tutor respuesta) {

           tcorreo.setText(respuesta.getCorreo());
           tpresentacion.setText(respuesta.getPresentacion());
           ttitulo.setText(respuesta.getNivel_academico());
         //  Toast.makeText(Tutor_perfil_estudiante.this,"HOLA"+respuesta.getTelefono(),Toast.LENGTH_LONG).show();
        ttelefono.setText(""+respuesta.getTelefono());
           tpresentacion.setText(respuesta.getPresentacion());
           tvideo.setText(respuesta.getVideo());

       }

       @Override
       public void error(Exception ex) {
Toast.makeText(Tutor_perfil_estudiante.this,"Error"+ex.getMessage(),Toast.LENGTH_LONG).show();
       }
   });

   btn.setOnClickListener(new View.OnClickListener() {

       @Override
       public void onClick(View view) {
           String telefono=""+ttelefono.getText();
           Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null));
           startActivity(intent);
        
/*sta
Intent i = new Intent(Intent.ACTION_DIAL);
i.setData(Uri.parse("tel:0612312312"));
if (i.resolveActivity(getPackageManager()) != null) {
      startActivity(i);
}*/

       }
   });
    }
}