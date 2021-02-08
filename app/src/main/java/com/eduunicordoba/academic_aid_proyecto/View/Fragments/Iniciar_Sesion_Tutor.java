package com.eduunicordoba.academic_aid_proyecto.View.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.View.Activity.Mensaje;
import com.eduunicordoba.academic_aid_proyecto.View.Navegacion.Navegacion_Tutor;
import com.eduunicordoba.academic_aid_proyecto.View.Activity.Asignaturas_Activity;
import com.eduunicordoba.academic_aid_proyecto.View.Activity.Genero_Activity;
import com.eduunicordoba.academic_aid_proyecto.View.Activity.Informacion_academica;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.eduunicordoba.academic_aid_proyecto.View.Activity.Tipo_documento_tutor;
import com.eduunicordoba.academic_aid_proyecto.View.Activity.recuperar_contra;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Iniciar_Sesion_Tutor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Iniciar_Sesion_Tutor extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
private Button button;
    // TODO: Rename and change types of parameters
    private EditText icorreo;
    private EditText icontraseña;
    private String mParam1;
    private String mParam2;
    private TextView texto;
FirebaseAuth auth;
private Tutor_Repositorio tutor_repositorio;
    public Iniciar_Sesion_Tutor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Iniciar_Sesion_Tutor.
     */
    // TODO: Rename and change types and number of parameters
    public static Iniciar_Sesion_Tutor newInstance(String param1, String param2) {
        Iniciar_Sesion_Tutor fragment = new Iniciar_Sesion_Tutor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view=inflater.inflate(R.layout.fragment_iniciar__sesion__tutor, container, false);
        icorreo=(EditText)view.findViewById(R.id.iniciar_sesion_correo);
        icontraseña=(EditText)view.findViewById(R.id.iniciar_sesion_contraseña);
        button=(Button)view.findViewById(R.id.ini_sesion);
        texto=(TextView)view.findViewById(R.id.olvidar_tutor);

        auth= FirebaseAuth.getInstance();
        tutor_repositorio=new Tutor_Repositorio(getActivity());

        texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), recuperar_contra.class);
                startActivity(intent);

            }
        });
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String correo=icorreo.getText().toString().trim();
        String contraseña=icontraseña.getText().toString().trim();
        auth.signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(),"Hecho",Toast.LENGTH_LONG).show();
                    tutor_repositorio.ObtenerPaso(auth.getUid(), new AcademicApp<Integer>() {
                        @Override
                        public void hecho(Integer respuesta) {
                             pasar(respuesta);
                        }

                        @Override
                        public void error(Exception ex) {

                        }
                    }

                    );
                }else{
                    Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
});
        return view;
    }

public void pasar(int res){
        if(res==1){
            Intent intent=new Intent(getActivity(), Mensaje.class);
            startActivity(intent);
            getActivity().finish();
        }
        if(res==2){
            Intent intent=new Intent(getActivity(), Genero_Activity.class);
            startActivity(intent);
            getActivity().finish();
        }
        if(res==3){
            Intent intent=new Intent(getActivity(), Tipo_documento_tutor.class);
            startActivity(intent);
            getActivity().finish();
        }
        if(res==4){
            Intent intent=new Intent(getActivity(), Asignaturas_Activity.class);
            startActivity(intent);
            getActivity().finish();
        }
        if(res==5){
            Intent intent=new Intent(getActivity(), Informacion_academica.class);
            startActivity(intent);
            getActivity().finish();
        }
        if(res==6){
            Intent intent=new Intent(getActivity(), Navegacion_Tutor.class);
            startActivity(intent);
            getActivity().finish();
        }
}
}