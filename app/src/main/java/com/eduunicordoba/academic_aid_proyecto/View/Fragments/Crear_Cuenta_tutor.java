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
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.View.Activity.Mensaje;
import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Tutor;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Crear_Cuenta_tutor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Crear_Cuenta_tutor extends Fragment {

    FirebaseAuth auth;
    FirebaseFirestore db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText crear_cuenta_nombre;
    private EditText crear_cuenta_cedula;
    private EditText crear_cuenta_telefono;
    private EditText crear_cuenta_correo;
    private EditText crear_cuenta_contraseña;
    private EditText crear_cuenta_apellido;
    private Button btn_iniciarSesion;
    public Crear_Cuenta_tutor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Crear_Cuenta_tutor.
     */
    // TODO: Rename and change types and number of parameters
    public static Crear_Cuenta_tutor newInstance(String param1, String param2) {
        Crear_Cuenta_tutor fragment = new Crear_Cuenta_tutor();
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
       View view=(View)inflater.inflate(R.layout.fragment_crear__cuenta_tutor, container, false);
       crear_cuenta_nombre=(EditText)view.findViewById(R.id.crear_cuenta_nombre);
       crear_cuenta_correo=(EditText)view.findViewById(R.id.crear_cuenta_correo);
       crear_cuenta_telefono=(EditText)view.findViewById(R.id.crear_cuenta_telefono);
       crear_cuenta_contraseña=(EditText)view.findViewById(R.id.crear_cuenta_contraseña);
       crear_cuenta_apellido=(EditText)view.findViewById(R.id.crear_cuenta_apellido);
       btn_iniciarSesion=(Button)view.findViewById(R.id.registrar);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
btn_iniciarSesion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String nombre=crear_cuenta_nombre.getText().toString().trim();
        String apellido=crear_cuenta_apellido.getText().toString().trim();
        String correo=crear_cuenta_correo.getText().toString().trim();
        String contraseña=crear_cuenta_contraseña.getText().toString().trim();
        int telefono=Integer.parseInt(crear_cuenta_telefono.getText().toString().trim());
       Tutor tutor =  new Tutor(nombre,apellido,correo,telefono,contraseña);
Toast.makeText(getActivity(),""+tutor.getCorreo(),Toast.LENGTH_LONG).show();
         auth.createUserWithEmailAndPassword(tutor.getCorreo(),tutor.getContraseña()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     db.collection("Tutor").document(auth.getUid()).set(tutor).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){
                               Intent intent=new Intent(getActivity(), Mensaje.class);
                               startActivity(intent);



                             }else{
                                 Toast.makeText(getActivity(),"Error"+task.getException(),Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                 }else Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
             }
         });

    }
});



       return view;
    }
}