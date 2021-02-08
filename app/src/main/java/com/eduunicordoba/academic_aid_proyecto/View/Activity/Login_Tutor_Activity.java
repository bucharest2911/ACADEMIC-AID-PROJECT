package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.eduunicordoba.academic_aid_proyecto.Adaptador_Tutor_Login;
import com.eduunicordoba.academic_aid_proyecto.R;
import com.google.android.material.tabs.TabLayout;

public class Login_Tutor_Activity extends AppCompatActivity {

    TabLayout tabControlesSeleccion;
    ViewPager paginas;
    //Se declara el adaptador de nombre adaptadorPaginas, del tipo de la clase AdaptadorPaginas.
    Adaptador_Tutor_Login adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__tutor_);

        tabControlesSeleccion = (TabLayout) findViewById(R.id.tabControlesSeleccion);
        paginas = (ViewPager) findViewById(R.id.paginas);
        adaptador = new Adaptador_Tutor_Login(getSupportFragmentManager(), tabControlesSeleccion.getTabCount());




        paginas.setAdapter(adaptador);
        tabControlesSeleccion.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                paginas.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    adaptador.notifyDataSetChanged();
                   // Toast.makeText(Login_tutor.this,"Holla",Toast.LENGTH_SHORT).show();
                }
                if(tab.getPosition()==1){
                    adaptador.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        paginas.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabControlesSeleccion));
    }
}