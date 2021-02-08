package com.eduunicordoba.academic_aid_proyecto.View.Adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Crear_Cuenta_tutor;
import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Iniciar_Sesion_Tutor;

public class Adaptador_Tutor_Login extends FragmentPagerAdapter {

private int numeropaginas;
    public Adaptador_Tutor_Login(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numeropaginas=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


            switch (position){
                case 0:
                    return new Iniciar_Sesion_Tutor();
                case 1:
                    return new Crear_Cuenta_tutor();

                default: return null;
            }

    }
        @Override
    public int getCount() {
        return this.numeropaginas;
    }
}
