package com.example.juanh.unammobileexample.Models;

import android.graphics.Bitmap;

/**
 * Created by juanh on 08/04/2017.
 */

public class Post {

    private String userpost;
    private String titulo;
    private String texto;
    private String imagen;

    public Post(String userpost, String titulo, String texto, String imagen) {
        this.userpost = userpost;
        this.titulo = titulo;
        this.texto = texto;
        this.imagen = imagen;
    }

    public String getUserpost() {
        return userpost;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public String getImagen() {
        return imagen;
    }

    public String toString() {
        return String.format("Titulo:%s\nUsuario:%s\nTexto:%s\nURL IMAGEN:%s",
                this.titulo,
                this.userpost,
                this.texto,
                this.imagen);
    }
}
