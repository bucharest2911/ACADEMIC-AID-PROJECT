package com.eduunicordoba.academic_aid_proyecto.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.eduunicordoba.academic_aid_proyecto.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Horario_Viernes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Horario_Viernes extends Fragment {
    int ids[]={R.id.tv_hora_1,R.id.tv_hora_2,R.id.tv_hora_3,R.id.tv_hora_4,R.id.tv_hora_5,
            R.id.tv_hora_6,R.id.tv_hora_7,R.id.tv_hora_8,R.id.tv_hora_9,R.id.tv_hora_10,R.id.tv_hora_11,R.id.tv_hora_12,
            R.id.tv_hora_13,R.id.tv_hora_14,R.id.tv_hora_15,R.id.tv_hora_16,R.id.tv_hora_17,R.id.tv_hora_19,R.id.tv_hora_20,
            R.id.tv_hora_21,R.id.tv_hora_22,R.id.tv_hora_23

    };
    CheckBox checkBox[]=new CheckBox[ids.length];
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Horario_Viernes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Horario_Viernes.
     */
    // TODO: Rename and change types and number of parameters
    public static Horario_Viernes newInstance(String param1, String param2) {
        Horario_Viernes fragment = new Horario_Viernes();
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
        return inflater.inflate(R.layout.fragment_horario__viernes, container, false);
    }
}