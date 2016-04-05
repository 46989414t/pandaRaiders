package com.tallahassee.pandaraiders.objetos;

/**
 * Created by enric on 4/4/16.
 */
public class Localizacion {
    private String email;
    private double latitud;
    private double logitud;

    public Localizacion() {
    }

    public Localizacion(String email, double latitud, double logitud) {
        this.email = email;
        this.latitud = latitud;
        this.logitud = logitud;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLogitud() {
        return logitud;
    }

    public void setLogitud(double logitud) {
        this.logitud = logitud;
    }
}
