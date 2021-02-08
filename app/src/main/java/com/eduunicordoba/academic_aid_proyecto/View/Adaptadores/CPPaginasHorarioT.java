package com.eduunicordoba.academic_aid_proyecto.View.Adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Horario_Domingo;
import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Horario_Jueves;
import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Horario_Sabado;
import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Horario_Viernes;
import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Horario_lunes;
import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Horario_martes;
import com.eduunicordoba.academic_aid_proyecto.View.Fragments.Horario_miercoles;

public class CPPaginasHorarioT extends FragmentPagerAdapter {

    int numeroPaginas;
    public CPPaginasHorarioT(@NonNull FragmentManager fm, int numPaginas) {
        super(fm, numPaginas);
        this.numeroPaginas = numPaginas;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Horario_Domingo();
            case 1:
                return new Horario_lunes();
            case 3:
                return new Horario_martes();
            case 2:
                return new Horario_miercoles();
            case 4:
                return new Horario_Jueves();
            case 5:
                return new Horario_Viernes();
            case 6:
                return new Horario_Sabado();
            default: return null;
        }
    }
    @Override
    public int getCount() {
        return numeroPaginas;
    }
}
