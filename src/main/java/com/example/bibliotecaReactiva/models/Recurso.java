package com.example.bibliotecaReactiva.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "recursosReactivos")
public class Recurso
{
    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    private String tipo;

    private String tematica;

    private LocalDateTime fechaPrestamo;

    private String titulo;

    private Boolean disponible ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Recurso() {
    }


    public Recurso(String id, String tipo, String tematica, LocalDateTime fechaPrestamo, String titulo, Boolean disponible) {
        this.id = id;
        this.tipo = tipo;
        this.tematica = tematica;
        this.fechaPrestamo = fechaPrestamo;
        this.titulo = titulo;
        this.disponible = disponible;
    }

    public Recurso(String id, String tipo, String tematica, Boolean disponible) {
        this.id = id;
        this.tipo = tipo;
        this.tematica = tematica;
        this.disponible = disponible;
    }
}

