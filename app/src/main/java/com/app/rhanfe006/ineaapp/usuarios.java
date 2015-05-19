package com.app.rhanfe006.ineaapp;

/**
 * Created by rhanfe006 on 28/04/2015.
 */
public class usuarios {

    private String idusuario ;
    private String nombres ;
    private String apellidos ;
    private String actividades;
    private String nivel;
    private String modulo;
    private String fecha;


    public usuarios (String idusuario, String nombres, String apellidos, String actividades, String nivel, String modulo, String fecha) {

        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.actividades = actividades;
        this.nivel = nivel;
        this.modulo = modulo;
        this.fecha = fecha;
    }
    public String getIdusuario() {
        return idusuario;
    }


    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getActividades() {
        return actividades;
    }

    public String getNivel() {
        return nivel;
    }

    public String getModulo() {
        return modulo;
    }

    public String getFecha() {
        return fecha;
    }
}
