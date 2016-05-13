package com.tallahassee.pandaraiders.objetos;

/**
 * Created by enric on 6/4/16.
 */
public class Etapa {
    private String nombre;
    private String inicio;
    private String fin;
    private String descripcion;
    private double latIncio;
    private double longIncio;
    private double latFinal;
    private double longFinal;

    public Etapa() {
    }

    public Etapa(String nombre, String inicio, String fin, String descripcion, double latIncio, double longIncio, double latFinal, double longFinal) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
        this.descripcion = descripcion;
        this.latIncio = latIncio;
        this.longIncio = longIncio;
        this.latFinal = latFinal;
        this.longFinal = longFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatIncio() {
        return latIncio;
    }

    public void setLatIncio(double latIncio) {
        this.latIncio = latIncio;
    }

    public double getLongIncio() {
        return longIncio;
    }

    public void setLongIncio(double longIncio) {
        this.longIncio = longIncio;
    }

    public double getLatFinal() {
        return latFinal;
    }

    public void setLatFinal(double latFinal) {
        this.latFinal = latFinal;
    }

    public double getLongFinal() {
        return longFinal;
    }

    public void setLongFinal(double longFinal) {
        this.longFinal = longFinal;
    }
}
