package com.example.juanh.unammobileexample.Models;

import android.graphics.Bitmap;

/**
 * Created by juanh on 08/04/2017.
 */

public class Negocio {

    private String nombre;
    private String imagen;
    private Coordenadas coordenadas;
    private Bitmap bitmap;

    public Negocio(String nombre, String imagen, double latitud, double longitud) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.coordenadas = new Coordenadas(latitud, longitud);
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap b) {
        this.bitmap = bitmap;
    }
}
