package com.example.gsp_recursohumano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CrearEmpleado extends AppCompatActivity {
    private EditText cedula, nombre, apellido, celular, correo;
    private ImageView foto;
    private Uri uri;
    private Spinner comboVinculacion, comboDiruOfi;
    private String opcVinculacion[], opcDiruOfi[];
    private ArrayAdapter<String> adapter1, adapter2;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private Boolean check = false;

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
        foto = findViewById(R.id.imgFotoSeleccionada);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Empleados");

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
        InputMethodManager imm;
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(cedula.getWindowToken(),0);

        if(validar()){
        id = Datos.getId();
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
        verificarexistencia(ced,nom,apell,id,vin,DirOf,cel,corr,v);
        }
    }

    public void subir_foto(String id){
        StorageReference child = storageReference.child(id);
        UploadTask uploadTask = child.putFile(uri);
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
        foto.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    public void onBackPressed(){
       finish();
       Intent i = new Intent(CrearEmpleado.this, MainActivity.class);
       startActivity(i);
    }
    public void seleccionarFoto(View v){
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(in,getString(R.string.seleccionar_foto)),1);
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data){
        super.onActivityResult(requesCode, resultCode, data);
        if(requesCode == 1){
            uri = data.getData();
            if(uri != null){
                foto.setImageURI(uri);
            }
        }
    }
    public boolean validar(){
        if(cedula.getText().toString().isEmpty()){
            cedula.setError(getString(R.string.mensaje_error_cedula));
            cedula.requestFocus();
            return false;
        }
        if(nombre.getText().toString().isEmpty()){
            nombre.setError(getString(R.string.mensaje_error_nombre));
            nombre.requestFocus();
            return false;
        }
        if(apellido.getText().toString().isEmpty()){
            apellido.setError(getString(R.string.mensaje_error_apellido));
            apellido.requestFocus();
            return false;
        }
        if(celular.getText().toString().isEmpty()){
            celular.setError(getString(R.string.mensaje_error_celular));
            celular.requestFocus();
            return false;
        }
        if(correo.getText().toString().isEmpty()){
            correo.setError(getString(R.string.mensaje_error_correo));
            correo.requestFocus();
            return false;
        }
        if(uri == null){
            Snackbar.make((View)cedula,R.string.seleccionar_foto,Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public void verificarexistencia(String ced, String nom, String apell, String id, String vin, String DirOf, String cel, String corr, View v){
        databaseReference.orderByChild("cedula").equalTo(ced).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Snackbar.make(v,R.string.mensaje_existente,Snackbar.LENGTH_LONG).show();
                }
                else {
                    Empleado e;
                    e = new Empleado(ced,nom,apell,id,vin,DirOf,cel,corr);
                    e.guardar();
                    limpiar();
                    subir_foto(id);
                    Snackbar.make(v,R.string.empleado_creado,Snackbar.LENGTH_LONG).show();
                    uri = null;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}