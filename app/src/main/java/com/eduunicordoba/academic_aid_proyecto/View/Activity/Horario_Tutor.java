package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.eduunicordoba.academic_aid_proyecto.R;
import com.eduunicordoba.academic_aid_proyecto.View.Adaptadores.CPPaginasHorarioT;
import com.google.android.material.tabs.TabLayout;

public class Horario_Tutor extends AppCompatActivity {

    TabLayout tabControleshorario;
    ViewPager paginashoraio;

    CPPaginasHorarioT ceppaginashorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario__tutor);
        tabControleshorario = (TabLayout) findViewById(R.id.tab_Controles_horario);
        paginashoraio = (ViewPager) findViewById(R.id.paginas_horario);
        ceppaginashorario = new CPPaginasHorarioT(getSupportFragmentManager(),
                tabControleshorario.getTabCount());
        paginashoraio.setAdapter(ceppaginashorario);

        tabControleshorario.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                paginashoraio.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    ceppaginashorario.notifyDataSetChanged();
                }
                if(tab.getPosition() == 1){
                    ceppaginashorario.notifyDataSetChanged();
                }
                if(tab.getPosition() == 2){
                    ceppaginashorario.notifyDataSetChanged();
                }
                if(tab.getPosition() == 3){
                    ceppaginashorario.notifyDataSetChanged();
                }
                if(tab.getPosition() == 4){
                    ceppaginashorario.notifyDataSetChanged();
                }
                if(tab.getPosition() == 5){
                    ceppaginashorario.notifyDataSetChanged();
                }
                if(tab.getPosition() == 6){
                    ceppaginashorario.notifyDataSetChanged();
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }


        });

        paginashoraio.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabControleshorario));
    }
}