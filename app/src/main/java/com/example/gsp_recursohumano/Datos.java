package com.example.gsp_recursohumano;

import java.util.ArrayList;

public class Datos {
    public static ArrayList<Empleado> empleados = new ArrayList();

    public static void guardar(Empleado e){
        empleados.add(e);
    }

    public static ArrayList<Empleado> obtener(){
        return empleados;
    }
}
