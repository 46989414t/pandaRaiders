package com.tallahassee.pandaraiders.objetos;

/**
 * Created by enric on 29/3/16.
 */
public class Mensaje {
    private String mensaje;
    private String remitente;
    private String destinatario;

    public Mensaje() {
    }

    public Mensaje(String mensaje, String remitente, String destinatario) {
        this.mensaje = mensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "mensaje='" + mensaje + '\'' +
                ", remitente='" + remitente + '\'' +
                '}';
    }
}
