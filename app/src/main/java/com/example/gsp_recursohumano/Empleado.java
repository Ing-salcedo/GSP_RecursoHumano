package com.example.gsp_recursohumano;

public class Empleado {
    private String cedula, nombre, apellido, id, vinculacion, diruofi, celular, correo;

    public Empleado(String cedula, String nombre, String apellido, String id, String vinculacion, String diruofi, String celular, String correo){
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.vinculacion = vinculacion;
        this.diruofi = diruofi;
        this.celular = celular;
        this.correo = correo;
    }

    public Empleado(){

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVinculacion() {
        return vinculacion;
    }

    public void setVinculacion(String vinculacion) {
        this.vinculacion = vinculacion;
    }

    public String getDiruofi() {
        return diruofi;
    }

    public void setDiruofi(String diruofi) {
        this.diruofi = diruofi;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void guardar(){
        Datos.guardar(this);
    }

    public void eliminar(){
        Datos.eliminar(this);
    }
}
