package com.eduunicordoba.academic_aid_proyecto.View.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.eduunicordoba.academic_aid_proyecto.Model.Entity.Horario;
import com.eduunicordoba.academic_aid_proyecto.Model.Network.AcademicApp;
import com.eduunicordoba.academic_aid_proyecto.Model.Repository.Tutor_Repositorio;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Horario_Domingo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Horario_Domingo extends Fragment {
    int ids[]={R.id.td_hora_1,R.id.td_hora_2,R.id.td_hora_3,R.id.td_hora_4,R.id.td_hora_5,R.id.td_hora_5,
            R.id.td_hora_6,R.id.td_hora_7,R.id.td_hora_8,R.id.td_hora_9,R.id.td_hora_10,R.id.td_hora_11,R.id.td_hora_12,
            R.id.td_hora_13,R.id.td_hora_14,R.id.td_hora_15,R.id.td_hora_16,R.id.td_hora_17,R.id.td_hora_19,R.id.td_hora_20,
            R.id.td_hora_21,R.id.td_hora_22,R.id.td_hora_23

    };
    CheckBox checkBox[]=new CheckBox[ids.length];
    private Tutor_Repositorio tutor_repositorio;
    private FirebaseAuth auth;
    private Button btn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Horario_Domingo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Horario_Domingo.
     */
    // TODO: Rename and change types and number of parameters
    public static Horario_Domingo newInstance(String param1, String param2) {
        Horario_Domingo fragment = new Horario_Domingo();
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
        View view= inflater.inflate(R.layout.fragment_horario__domingo, container, false);
        btn=view.findViewById(R.id.guardar_domingo);
        AsociarElementos(view);
        tutor_repositorio=new Tutor_Repositorio(getActivity());
        auth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<ids.length;i++){
                    /// checkBox[i]=(CheckBox)view.findViewById(ids[i]);
                    //Toast.makeText(getActivity(),"SI"+checkBox[i].getText(),Toast.LENGTH_LONG).show();
                    if(checkBox[i].isChecked()){
                        // Toast.makeText(getActivity(),"SI"+checkBox[i].getText(),Toast.LENGTH_LONG).show();
                        Horario horario=new Horario("Domingo",checkBox[i].getText().toString(),auth.getUid());
                        tutor_repositorio.GuardarHorario(horario, new AcademicApp<Boolean>() {
                            @Override
                            public void hecho(Boolean respuesta) {
                                androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext()).create();
                                alertDialog.setTitle("");
                                alertDialog.setMessage("Guardado con exito");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }

                            @Override
                            public void error(Exception ex) {

                            }
                        });
                    }
                }
            }
        });
        return view;
    }
    public void AsociarElementos(View view){
        for (int i=0;i<ids.length;i++){
            checkBox[i]=(CheckBox)view.findViewById(ids[i]);
        }
    }
}