package com.eduunicordoba.academic_aid_proyecto.View.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Materia;
import com.eduunicordoba.academic_aid_proyecto.R;

import java.util.ArrayList;

public class Asignatura_adaptador  extends RecyclerView.Adapter<Asignatura_adaptador.ViewHolderAsignatura> {
private ArrayList<Materia> materias;
private  Context context;

public Asignatura_adaptador( ArrayList<Materia> lista){

    this.materias=lista;
}
public void setMaterias(ArrayList<Materia>materias){
    this.materias=materias;
    notifyDataSetChanged();
}


    @NonNull
    @Override
    public ViewHolderAsignatura onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.contenedor_asignatura,parent,false);
        return new Asignatura_adaptador.ViewHolderAsignatura(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAsignatura holder, int position) {
holder.Onbind(materias.get(position));
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    class ViewHolderAsignatura extends  RecyclerView.ViewHolder{
private TextView texto;
        public ViewHolderAsignatura(@NonNull View itemView) {
            super(itemView);
            texto=(TextView)itemView.findViewById(R.id.txt_asignatura);

        }
        public void Onbind(Materia materia){
            if(materia!=null){
             //
                texto.setText(materia.getSubarea());
               // Toast.makeText(context,""+materia.getSubarea(),Toast.LENGTH_LONG).show();
            }

        }
    }
}
