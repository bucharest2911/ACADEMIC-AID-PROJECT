package com.eduunicordoba.academic_aid_proyecto.Model.Repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Materia;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Estudiante_Repositorio {
    private Context context;
    private FirebaseFirestore firestore;
    private static  final String Collecion_materia="Asignatura";
    private static  final String Collecion_Tutor="Tutor";
    public ArrayList<Tutor> listadotutor;
    private ArrayList<Materia>listadomateria;
    public Estudiante_Repositorio(Context context){
        this.context=context;

        this.firestore=FirebaseFirestore.getInstance();
        this.listadomateria=new ArrayList<Materia>();
        this.listadotutor=new ArrayList<Tutor>();

    };

    public void ObtenerMateria(String id, AcademicApp<ArrayList<Materia>>obtener){
        firestore.collection(Collecion_materia).whereEqualTo("id_tutor",id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
listadomateria.clear();
                    for(DocumentSnapshot documento:task.getResult()){
                        Materia materia=documento.toObject(Materia.class);
                        listadomateria.add(materia);


                    }
                    obtener.hecho(listadomateria);
                }

            }
        });
    }



    public void ObtenerTodo(final AcademicApp<ArrayList<Materia>> capturar){
        firestore.collection(Collecion_Tutor).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                listadotutor.clear();
                if(error==null){


                    for(DocumentSnapshot item:value.getDocuments()){
                       Tutor tutor=item.toObject(Tutor.class);

                        listadotutor.add(tutor);



                    }
                    capturar.hecho(listadomateria);


                }
            }
        });
    }

}
