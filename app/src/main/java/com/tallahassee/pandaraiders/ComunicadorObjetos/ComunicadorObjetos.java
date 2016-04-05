package com.tallahassee.pandaraiders.ComunicadorObjetos;

/**
 * Created by enric on 3/4/16.
 */
public class ComunicadorObjetos {
    private static Object objeto = null;

    public static void setObjeto(Object newObjeto) {
        objeto = newObjeto;
    }

    public static Object getObjeto() {
        return objeto;
    }
}