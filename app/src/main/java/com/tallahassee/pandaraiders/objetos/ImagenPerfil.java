package com.tallahassee.pandaraiders.objetos;

/**
 * Created by enric on 6/3/16.
 */
public class ImagenPerfil {
    String codigoBitmap;
    String email;
    String fecha;
    String nombreFoto;

    public ImagenPerfil() {
    }

    public ImagenPerfil(String codigoBitmap, String email, String fecha, String nombreFoto) {
        this.codigoBitmap = codigoBitmap;
        this.email = email;
        this.fecha = fecha;
        this.nombreFoto = nombreFoto;
    }

    public String getCodigoBitmap() {
        return codigoBitmap;
    }

    public void setCodigoBitmap(String codigoBitmap) {
        this.codigoBitmap = codigoBitmap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }
}
