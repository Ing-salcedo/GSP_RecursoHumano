package com.example.gsp_recursohumano;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView lista;
    private AdaptadorEmpleado adapter;
    private LinearLayoutManager llm;
    private ArrayList<Empleado> empleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        lista = findViewById(R.id.lstEmpleados);

        empleados = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorEmpleado(empleados);
        llm.setOrientation(RecyclerView.VERTICAL);

        lista.setLayoutManager(llm);
        lista.setAdapter(adapter);
    }

    public void agregar(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, CrearEmpleado.class);
        startActivity(intent);
        finish();
    }
}