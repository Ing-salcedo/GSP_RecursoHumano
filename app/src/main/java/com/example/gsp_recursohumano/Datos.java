package com.example.gsp_recursohumano;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    private static String bd = "Empleados";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Empleado> empleados = new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Empleado e){
        databaseReference.child(bd).child(e.getId()).setValue(e);
    }

    public static ArrayList<Empleado> obtener(){
        return empleados;
    }
}
