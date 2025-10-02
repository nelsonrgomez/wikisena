package com.sena.wikiadmin.modelo;

public class Leccion {
    private int id;
    private int moduloId;
    private String titulo;
    private String contenido;
    private String estado;
    private boolean tieneEjercicio;
    private int orden;
    private String url;

    // Relación (para mostrar título del módulo en tablas)
    private String moduloTitulo;

    public Leccion() {}

    public Leccion(int id, int moduloId, String titulo, String contenido,
                   String estado, boolean tieneEjercicio, int orden, String url) {
        this.id = id;
        this.moduloId = moduloId;
        this.titulo = titulo;
        this.contenido = contenido;
        this.estado = estado;
        this.tieneEjercicio = tieneEjercicio;
        this.orden = orden;
        this.url = url;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getModuloId() { return moduloId; }
    public void setModuloId(int moduloId) { this.moduloId = moduloId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public boolean isTieneEjercicio() { return tieneEjercicio; }
    public void setTieneEjercicio(boolean tieneEjercicio) { this.tieneEjercicio = tieneEjercicio; }

    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getModuloTitulo() { return moduloTitulo; }
    public void setModuloTitulo(String moduloTitulo) { this.moduloTitulo = moduloTitulo; }

    @Override
    public String toString() {
        return titulo;  // así el combo mostrará el título del módulo
    }
}
