package com.example.gsp_recursohumano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleEmpleado extends AppCompatActivity {
    private ImageView foto;
    private TextView cedula, nombre, apellido, vinculacion, DiruOfi, celular, correo;
    private Bundle bundle;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);

        String ced, nomb, apell, vin, duf, cel, cor;
        foto = findViewById(R.id.imgFotoDetalle);
        cedula = findViewById(R.id.lblCedulaDetalle);
        nombre = findViewById(R.id.llblNombreDetalle);
        apellido = findViewById(R.id.lblApellidoDetalle);
        vinculacion = findViewById(R.id.lblVinculacionDetalle);
        DiruOfi = findViewById(R.id.lblDiruOfiDetalle);
        celular = findViewById(R.id.lblCelularDetalle);
        correo = findViewById(R.id.lblCorreoDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        ced = bundle.getString("cedula");
        nomb = bundle.getString("nombre");
        apell = bundle.getString("apellido");
        vin = bundle.getString("vinculacion");
        duf = bundle.getString("DiruOfi");
        cel = bundle.getString("celular");
        cor = bundle.getString("correo");

        cedula.setText(ced);
        nombre.setText(nomb);
        apellido.setText(apell);
        vinculacion.setText(vin);
        DiruOfi.setText(duf);
        celular.setText(cel);
        correo.setText(cor);


    }
}