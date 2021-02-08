package com.eduunicordoba.academic_aid_proyecto.View.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Materia;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Estudiante_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Adaptador_Lista_Tutor  extends RecyclerView.Adapter<Adaptador_Lista_Tutor.ViewHolderTutor>{
    private ArrayList<Materia> listado;
    private  ArrayList<Tutor>listadotutor;
    private OnitemClickListener onitemClickListener;
private Context context;

    public OnitemClickListener getOnitemClickListener() {
        return onitemClickListener;
    }

    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    public  Adaptador_Lista_Tutor(ArrayList<Tutor> lista, Context context){
        this.listadotutor=lista;
        this.context=context;
    }

    public void setListado(ArrayList<Tutor> lista){
        this.listadotutor=lista;
        this.notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderTutor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.contenedor_cartaview_tutor,parent,false);
        return new ViewHolderTutor(vista,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTutor holder, int position) {
holder.Onbind(listadotutor.get(position));
holder.Asignar(listadotutor.get(position).getToken());

    }


    @Override
    public int getItemCount() {
        return listadotutor.size();
    }

    class ViewHolderTutor extends RecyclerView.ViewHolder{
private TextView nombre_tutor;
private  TextView  precio_tutor;
private ImageView imagen;
private RecyclerView recycler;
private ArrayList<Materia> materias;
private Estudiante_Repositorio estudiante_repositorio;
private Context context;
private Asignatura_adaptador asignatura_adaptador;
         public ViewHolderTutor(@NonNull View itemView,Context context) {
             super(itemView);
             nombre_tutor=( TextView )itemView.findViewById(R.id.cv_nombre_tutor);
             precio_tutor=( TextView )itemView.findViewById(R.id.cv_valor_clase);
             imagen=(ImageView)itemView.findViewById(R.id.cv_imagen_per_tutor);
             recycler=(RecyclerView)itemView.findViewById(R.id.cv_mostrar_asignaturas);
             this.materias=new ArrayList<>();
             asignatura_adaptador=new Asignatura_adaptador(materias);

//Asignar(nombre_tutor.getText().toString(),context);

         }



        public void Onbind(Tutor tutor){
             nombre_tutor.setText(tutor.getNombre());
             precio_tutor.setText("$120.000");
            FirebaseStorage storage=FirebaseStorage.getInstance();
            StorageReference imageRef=storage.getReference();
            imageRef.child(tutor.getToken());
            ;

            imageRef.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    imagen.setImageBitmap(bitmap);
                }
            });

            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onitemClickListener.onItemClickImagen( tutor,getAdapterPosition());
                }
            });

         }
         public void Asignar(String nombre){
             LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
             recycler.setLayoutManager(new LinearLayoutManager(getContext()));
             recycler.setHasFixedSize(true);

             estudiante_repositorio=new Estudiante_Repositorio(getContext());
             estudiante_repositorio.ObtenerMateria(nombre, new AcademicApp<ArrayList<Materia>>() {
                 @Override
                 public void hecho(ArrayList<Materia> respuesta) {
                  asignatura_adaptador.setMaterias(respuesta);


                 }

                 @Override
                 public void error(Exception ex) {

                 }


             });
             recycler.setAdapter(asignatura_adaptador);
         }
     }
    public interface OnitemClickListener{
        void onItemClickImagen(Tutor pro,int posicion);

    }







}
