package com.example.novelas5;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "novelas")
public class Novela {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private boolean leido;

    // Constructor
    public Novela(String titulo, String autor, String genero, boolean leido) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.leido = leido;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
