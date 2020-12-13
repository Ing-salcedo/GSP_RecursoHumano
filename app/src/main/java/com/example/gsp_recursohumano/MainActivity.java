package com.example.gsp_recursohumano;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorEmpleado.OnEmpleadoClickListener{
    private RecyclerView lista;
    private AdaptadorEmpleado adapter;
    private LinearLayoutManager llm;
    private ArrayList<Empleado> empleados;
    private DatabaseReference databaseReference;
    private String bd = "Empleados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        lista = findViewById(R.id.lstEmpleados);


        empleados = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorEmpleado(empleados, this);
        llm.setOrientation(RecyclerView.VERTICAL);

        lista.setLayoutManager(llm);
        lista.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(bd).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                empleados.clear();
                if(snapshot.exists()){
                    for(DataSnapshot snap: snapshot.getChildren()){
                        Empleado e = snap.getValue(Empleado.class);
                        empleados.add(e);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setEmpleados(empleados);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void agregar(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, CrearEmpleado.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onEmpleadoClick(Empleado e) {
        Intent intent;
        Bundle bundle;
        bundle = new Bundle();
        bundle.putString("id",e.getId());
        bundle.putString("cedula", e.getCedula());
        bundle.putString("nombre",e.getNombre());
        bundle.putString("apellido",e.getApellido());
        bundle.putString("vinculacion",e.getVinculacion());
        bundle.putString("DiruOfi",e.getDiruofi());
        bundle.putString("celular",e.getCelular());
        bundle.putString("correo",e.getCorreo());

        intent = new Intent(MainActivity.this, DetalleEmpleado.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
    }
}