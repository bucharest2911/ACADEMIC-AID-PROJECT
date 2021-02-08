package com.eduunicordoba.academic_aid_proyecto.Model.Repository;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Horario;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Materia;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Tutor_Repositorio {
    private static  final String Collecion_Tutor="Tutor";
private static final String Collection_Asignatura="Asignatura";
private  static  final String  Collection_Horario="Horario";
    private Context context;
    private FirebaseFirestore firestore;
    private  ArrayList<Tutor> tutores;
    private ArrayList<Tutor> tutorencontrados;
    private  ArrayList<Materia> materias;
    public Tutor_Repositorio(Context context){
this.context=context;

this.tutorencontrados=new ArrayList<Tutor>();
         StorageReference mStorageRef;
         tutores=new ArrayList<>();
this.firestore=FirebaseFirestore.getInstance();
    }
    public void ObtenerTutor(  String id,AcademicApp<Tutor> respuesta){

        firestore.collection(Collecion_Tutor).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult()!=null){
                      Tutor t   =task.getResult().toObject(Tutor.class);
                        if(t!=null){
                        //    Toast.makeText(context,"Paso"+tutor.getPaso(),Toast.LENGTH_LONG).show();
                        //    respuesta.hecho(tutor.getPaso());
                            t.setToken(id);
              respuesta.hecho(t);
                        }

                    }
                }else{

                }
            }
        });
    }

    public void ObtenerTutorValido(  String id,AcademicApp<Tutor> respuesta){

        firestore.collection(Collecion_Tutor).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult()!=null){
                        Tutor t   =task.getResult().toObject(Tutor.class);
                        if(t!=null){
                             // Toast.makeText(context,"Paso"+t.getNombre(),Toast.LENGTH_LONG).show();
                            //    respuesta.hecho(tutor.getPaso());
                            t.setToken(id);
                            respuesta.hecho(t);
                        }

                    }
                }else{

                }
            }
        });
    }

    public void buscarAsignatura(String sub,AcademicApp<ArrayList<Tutor>>capturar){
        firestore.collection(Collection_Asignatura).whereEqualTo("subarea",sub).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
tutorencontrados.clear();
           for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
Materia materia=documentSnapshot.toObject(Materia.class);

ObtenerTutor(materia.getId_tutor(), new AcademicApp<Tutor>() {
    @Override
    public void hecho(Tutor respuesta) {
if(respuesta.getPaso()==6){

tutorencontrados.add(respuesta);

}


    }

    @Override
    public void error(Exception ex) {

    }
});
           }
           capturar.hecho(tutorencontrados);
            }
        });
    }




    public void ObtenerPaso(final String id, final AcademicApp<Integer> respuesta){
        Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
        firestore.collection(Collecion_Tutor).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult()!=null){
                        Tutor tutor=task.getResult().toObject(Tutor.class);
                        if(tutor!=null){
                             //Toast.makeText(context,"Paso"+tutor.getPaso(),Toast.LENGTH_LONG).show();
                            respuesta.hecho(tutor.getPaso());

                        }

                    }
                }else{

                }


            }
        });

    }

    public void ObtenerMateria(String id_tutor, AcademicApp<ArrayList<Materia>>obtener){
        this.firestore.collection(Collection_Asignatura).whereEqualTo("id_tutor",id_tutor).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(DocumentSnapshot documento:task.getResult()){
Materia materia=documento.toObject(Materia.class);
ObtenerTutorValido(materia.getId_tutor(), new AcademicApp<Tutor>() {
    @Override
    public void hecho(Tutor respuesta) {
        Toast.makeText(context,respuesta.getPresentacion(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void error(Exception ex) {

    }
});

                    }
                }

            }
        });
    }



     public void GuardarAsignatura(Materia materia,AcademicApp<Boolean>respuesta){

         firestore.collection(Collection_Asignatura).add(materia).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
             @Override
             public void onComplete(@NonNull Task<DocumentReference> task) {
                 if(task.isSuccessful()){
                 respuesta.hecho(true);
                 }else{
                     respuesta.error(task.getException());
                 }
             }
         });

     }

    public void GuardarHorario(Horario horario, AcademicApp<Boolean>respuesta){

        firestore.collection(Collection_Horario).add(horario).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    respuesta.hecho(true);
                }else{
                    respuesta.error(task.getException());
                }
            }
        });

    }
    public void Editar(Tutor tutor, final AcademicApp<Boolean> callback){


        firestore.collection(Collecion_Tutor).document(tutor.getToken()).set(tutor).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    callback.hecho(true);
                }else{
                    callback.error(task.getException());
                }
            }
        });
    }


    public void ObtenerTutores(final AcademicApp<ArrayList<Tutor>> capturar){
        firestore.collection(Collecion_Tutor).whereEqualTo("paso",6).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                tutores.clear();
                if(error==null){


                    for(DocumentSnapshot item:value.getDocuments()){
                        Tutor tutor=item.toObject(Tutor.class);
//Toast.makeText(context,""+tutor.getNombre(), Toast.LENGTH_LONG).show();
                   tutores.add(tutor);



                    }
                    capturar.hecho(tutores);


                }
            }
        });
    }



}
