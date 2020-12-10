package com.example.gsp_recursohumano;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class CrearEmpleado extends AppCompatActivity {
    private EditText cedula, nombre, apellido, celular, correo;
    private ImageView foto;
    private Spinner comboVinculacion, comboDiruOfi;
    private String opcVinculacion[], opcDiruOfi[];
    private ArrayAdapter<String> adapter1, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empleado);

        cedula = findViewById(R.id.txtCedula);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        comboVinculacion = findViewById(R.id.cmbTipoVinculacion);
        comboDiruOfi = findViewById(R.id.cmbDirecciónuOficina);
        celular = findViewById(R.id.txtCelular);
        correo = findViewById(R.id.txtCorreo);

        opcVinculacion = getResources().getStringArray(R.array.ArraVinculacion);
        opcDiruOfi = getResources().getStringArray(R.array.ArraDiruOfi);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcVinculacion);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcDiruOfi);

        comboVinculacion.setAdapter(adapter1);
        comboDiruOfi.setAdapter(adapter2);
    }

    public void guardar(View v){
        String ced, nom, apell, id, corr, cel, vin = "", DirOf = "";
        int poscmbvincula, poscmbDiruO;
        Empleado e;
        ced = cedula.getText().toString();
        nom = nombre.getText().toString();
        apell = apellido.getText().toString();
        corr = correo.getText().toString();
        cel = celular.getText().toString();
        poscmbvincula = comboVinculacion.getSelectedItemPosition();
        poscmbDiruO = comboDiruOfi.getSelectedItemPosition();
        switch (poscmbvincula){
            case 0:
                vin = getString(R.string.ops);
                break;
            case 1:
                vin = getString(R.string.provisional);
                break;
            case 2:
                vin = getString(R.string.libre_nombramiento);
                break;
            case 3:
                vin = getString(R.string.carrera_adm);
                break;
        }
        switch (poscmbDiruO){
            case 0:
                DirOf = getString(R.string.apoyo_gestion);
                break;
            case 1:
                DirOf = getString(R.string.salud_publica);
                break;
            case 2:
                DirOf = getString(R.string.atencion_ciudadano);
                break;
            case 3:
                DirOf = getString(R.string.gestion_servicios);
                break;
        }
        e = new Empleado(ced,nom,apell,"",vin,DirOf,cel,corr);
        e.guardar();
        limpiar();
        Snackbar.make(v,R.string.empleado_creado,Snackbar.LENGTH_LONG).show();
    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        cedula.setText("");
        nombre.setText("");
        apellido.setText("");
        celular.setText("");
        correo.setText("");
        cedula.requestFocus();
    }
}