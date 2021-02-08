package com.eduunicordoba.academic_aid_proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class Foto_Tutor_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto__tutor_);
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link Horario_lunes#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class Horario_lunes extends Fragment {
        int ids[]={R.id.tl_hora_0,R.id.tl_hora_1,R.id.tl_hora_2,R.id.tl_hora_3,
                R.id.tl_hora_4,R.id.tl_hora_5,R.id.tl_hora_6,R.id.tl_hora_7,R.id.tl_hora_8,R.id.tl_hora_9,R.id.tl_hora_10,
                R.id.tl_hora_11,R.id.tl_hora_12,R.id.tl_hora_13,R.id.tl_hora_14,R.id.tl_hora_15,
                R.id.tl_hora_16  ,R.id.tl_hora_17,R.id.tl_hora_18,R.id.tl_hora_19,R.id.tl_hora_20,R.id.tl_hora_21,R.id.tl_hora_22,R.id.tl_hora_23


        };
        CheckBox checks[]=new CheckBox[ids.length];
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public Horario_lunes() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Horario_lunes.
         */
        // TODO: Rename and change types and number of parameters
        public static Horario_lunes newInstance(String param1, String param2) {
            Horario_lunes fragment = new Horario_lunes();
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
            View view= inflater.inflate(R.layout.fragment_horario_lunes2, container, false);

            for(int i=0;i<checks.length;i++){
                checks[i]=(CheckBox)view.findViewById(ids[i]);
                //    Toast.makeText(getActivity(),""+checks[i].getText(),Toast.LENGTH_LONG).show();
            }

            return view;
        }
    }
}