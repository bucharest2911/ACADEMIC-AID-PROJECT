package com.eduunicordoba.academic_aid_proyecto.View.Navegacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.eduunicordoba.academic_aid_proyecto.R;
import com.eduunicordoba.academic_aid_proyecto.View.Activity.Informacion_academica;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navegacion_Tutor extends AppCompatActivity {
private ImageButton btn_asignatura;
private  ImageButton btn_materias;
InicioNavTutor navTutor=new InicioNavTutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion__tutor);


        BottomNavigationView navigation = findViewById(R.id.nav_tutor);

        navigation.setOnNavigationItemSelectedListener(mOnnavigationItemSelectedListener);

        loadFragment(navTutor);

    }



    private final BottomNavigationView.OnNavigationItemSelectedListener mOnnavigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inicio:
                    loadFragment(navTutor);
                    return true;

                case R.id.menu_perfil:
    Intent intent=new Intent(Navegacion_Tutor.this, Informacion_academica.class);
startActivity(intent);
return true;
            }
            return false;
        }
    };
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_navtutor, fragment);
        transaction.commit();
    }



}