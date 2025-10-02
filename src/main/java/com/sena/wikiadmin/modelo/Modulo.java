package com.sena.wikiadmin.modelo;

import java.util.Date;

public class Modulo {
    private int id;
    private String titulo;
    private String descripcion;
    private String competencia; // html, css, js, java, php
    private boolean activo;
    private Date fechaCreacion;
    private int orden;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }
    
    @Override
    public String toString() {
        return titulo;  // así el combo mostrará el título del módulo
    }
}

