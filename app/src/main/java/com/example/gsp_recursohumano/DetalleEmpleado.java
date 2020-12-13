package com.example.gsp_recursohumano;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleEmpleado extends AppCompatActivity {
    private ImageView foto;
    private TextView cedula, nombre, apellido, vinculacion, DiruOfi, celular, correo;
    private Bundle bundle;
    private Intent intent;
    private StorageReference storageReference;
    private Empleado e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);

        String ced, nomb, apell, vin, duf, cel, cor, id;
        foto = findViewById(R.id.imgFotoDetalle);
        cedula = findViewById(R.id.lblCedulaDetalle);
        nombre = findViewById(R.id.llblNombreDetalle);
        apellido = findViewById(R.id.lblApellidoDetalle);
        vinculacion = findViewById(R.id.lblVinculacionDetalle);
        DiruOfi = findViewById(R.id.lblDiruOfiDetalle);
        celular = findViewById(R.id.lblCelularDetalle);
        correo = findViewById(R.id.lblCorreoDetalle);
        storageReference = FirebaseStorage.getInstance().getReference();

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        id = bundle.getString("id");
        ced = bundle.getString("cedula");
        nomb = bundle.getString("nombre");
        apell = bundle.getString("apellido");
        vin = bundle.getString("vinculacion");
        duf = bundle.getString("DiruOfi");
        cel = bundle.getString("celular");
        cor = bundle.getString("correo");
        e = new Empleado(ced,nomb,apell,id,vin,duf,cel,cor);

        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        cedula.setText(ced);
        nombre.setText(nomb);
        apellido.setText(apell);
        vinculacion.setText(vin);
        DiruOfi.setText(duf);
        celular.setText(cel);
        correo.setText(cor);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_eliminar);
        builder.setMessage(R.string.mensaje_eliminar);
        positivo = getString(R.string.mensaje_positivo);
        negativo = getString(R.string.mensaje_negativo);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                e.eliminar();
                onBackPressed();
            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleEmpleado.this, MainActivity.class);
        startActivity(intent);
    }
}