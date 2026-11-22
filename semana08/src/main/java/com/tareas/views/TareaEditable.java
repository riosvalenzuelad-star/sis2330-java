package com.tareas.views;

import com.tareas.Tarea;

public class TareaEditable {

    private Integer id;
    private String descripcion = "";
    private boolean completada;

    public TareaEditable() {
    }

    public TareaEditable(Tarea tarea) {
        this.id = tarea.id();
        this.descripcion = tarea.descripcion();
        this.completada = tarea.completada();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
