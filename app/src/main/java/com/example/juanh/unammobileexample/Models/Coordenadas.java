package com.example.juanh.unammobileexample.Models;

/**
 * Created by juanh on 08/04/2017.
 */

public class Coordenadas {

    private double latitud;
    private double longitud;

    public Coordenadas(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;

    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
