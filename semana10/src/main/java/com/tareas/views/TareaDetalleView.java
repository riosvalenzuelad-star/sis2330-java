package com.tareas.views;

import com.tareas.Tarea;
import com.tareas.TareaService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.Optional;

@Route(value = "tareas", layout = MainLayout.class)
public class TareaDetalleView extends VerticalLayout implements HasUrlParameter<Long> {

    private final TareaService servicio;

    public TareaDetalleView(TareaService servicio) {
        this.servicio = servicio;
    }

    @Override
    public void setParameter(BeforeEvent evento, Long id) {
        removeAll();
        Optional<Tarea> tarea = servicio.buscarPorId(id);

        if (tarea.isEmpty()) {
            add(new H2("Tarea no encontrada"));
            add(new Paragraph("No existe una tarea con el id " + id + "."));
        } else {
            add(new H2("Detalle de la tarea"));
            add(new Paragraph("ID: " + tarea.get().id()));
            add(new Paragraph("Descripcion: " + tarea.get().descripcion()));
            add(new Paragraph("Completada: " + (tarea.get().completada() ? "Si" : "No")));
        }
        add(new RouterLink("Volver a la lista", TareasView.class));
    }
}
