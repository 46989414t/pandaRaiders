package com.tallahassee.pandaraiders.objetos;

/**
 * Created by enric on 6/3/16.
 */
public class Car {
    private String email;
    private String marca;
    private String modelo;
    private String ano;

    public Car() {
    }

    public Car(String email, String marca, String modelo, String ano) {
        this.email = email;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
