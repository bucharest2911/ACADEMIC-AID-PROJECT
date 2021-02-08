package com.eduunicordoba.academic_aid_proyecto.View.Navegacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.Adaptador_Lista_Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Materia;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Estudiante_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.eduunicordoba.academic_aid_proyecto.View.Activity.Tutor_perfil_estudiante;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioNavEstudiante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioNavEstudiante extends Fragment {
private Estudiante_Repositorio estudiante_repositorio;
private  FirebaseAuth auth;
private  FirebaseFirestore store;
private ArrayList<Materia>materia;
private EditText buscardato;
    private ArrayList<Tutor>tutores;
private RecyclerView recycler;
private Button buscar;
private Adaptador_Lista_Tutor adaptador_lista_tutor;
private  Tutor_Repositorio tutor_repositorio;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InicioNavEstudiante() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioNavEstudiante.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioNavEstudiante newInstance(String param1, String param2) {
        InicioNavEstudiante fragment = new InicioNavEstudiante();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        store=FirebaseFirestore.getInstance();
        View view=inflater.inflate(R.layout.fragment_inicio_nav_estudiante2, container, false);
        recycler=(RecyclerView)view.findViewById(R.id.recycler) ;
        buscar=(Button)view.findViewById(R.id.buscar);
        buscardato=(EditText)view.findViewById(R.id.asignatura_buscar);
        materia=new ArrayList<Materia>();
tutores=new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setHasFixedSize(true);
        adaptador_lista_tutor=new Adaptador_Lista_Tutor(tutores,getActivity());
buscar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Adaptar(buscar.getText().toString());
    }
});
        Toast.makeText(getContext(),"Hola",Toast.LENGTH_LONG).show();
       // btn_producto=(Button)findViewById(R.id.crear_producto);
        ;

        estudiante_repositorio=new Estudiante_Repositorio(getActivity());
 tutor_repositorio=new Tutor_Repositorio(getActivity());

 tutor_repositorio.ObtenerTutores(new AcademicApp<ArrayList<Tutor>>() {
     @Override
     public void hecho(ArrayList<Tutor> respuesta) {
        adaptador_lista_tutor.setListado(respuesta);

     }

     @Override
     public void error(Exception ex) {

     }


 });
 adaptador_lista_tutor.setOnitemClickListener(new Adaptador_Lista_Tutor.OnitemClickListener() {
     @Override
     public void onItemClickImagen(Tutor pro, int posicion) {
         Intent intent=new Intent(getActivity(), Tutor_perfil_estudiante.class);
         intent.putExtra("Tutor",pro);
       //  Toast.makeText(getContext(),pro.getToken(),Toast.LENGTH_LONG).show();
         startActivity(intent);
     }
 });
        recycler.setAdapter(adaptador_lista_tutor);
                return view;
    }
    public void Adaptar( String campo){


        tutor_repositorio.buscarAsignatura(campo,new AcademicApp<ArrayList<Tutor>>() {
            @Override
            public void hecho(ArrayList<Tutor> respuesta) {
                adaptador_lista_tutor.setListado(respuesta);
               // Toast.makeText(getContext(),""+respuesta.size(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(Exception ex) {

            }
        });
        adaptador_lista_tutor.setOnitemClickListener(new Adaptador_Lista_Tutor.OnitemClickListener() {
            @Override
            public void onItemClickImagen(Tutor pro, int posicion) {
                Intent intent=new Intent(getActivity(), Tutor_perfil_estudiante.class);
                intent.putExtra("Tutor",pro);
                //  Toast.makeText(getContext(),pro.getToken(),Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        recycler.setAdapter(adaptador_lista_tutor);
    }

}