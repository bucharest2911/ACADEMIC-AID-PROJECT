package com.eduunicordoba.academic_aid_proyecto.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.eduunicordoba.academic_aid_proyecto.R;
import com.eduunicordoba.academic_aid_proyecto.View.Navegacion.InicioNavEstudiante;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Estudiante_Navegacion extends AppCompatActivity {






    InicioNavEstudiante inicioNavEstudianteFragment = new InicioNavEstudiante();
    private FirebaseAuth auth;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cerrar_mi_sesion:
                auth.signOut();
                Intent intent=new Intent(Estudiante_Navegacion.this,  Splash_Screen.class);
                startActivity(intent);
                finish();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante__navegacion);
        BottomNavigationView navigation = findViewById(R.id.nav_estudiante);

        navigation.setOnNavigationItemSelectedListener(mOnnavigationItemSelectedListener);

        loadFragment(inicioNavEstudianteFragment);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnnavigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inicio:
                    loadFragment(inicioNavEstudianteFragment);
                    return true;


            }
            return false;
        }
    };
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_navestudiante, fragment);
        transaction.commit();
    }
}