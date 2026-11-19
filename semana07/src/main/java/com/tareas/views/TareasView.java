package com.tareas.views;

import com.tareas.Tarea;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class TareasView extends VerticalLayout {

    private final List<Tarea> tareas = new ArrayList<>();
    private final VerticalLayout lista = new VerticalLayout();
    private int siguienteId = 1;

    public TareasView() {
        TextField campo = new TextField("Descripcion");
        campo.setPlaceholder("Que hay que hacer?");
        campo.setWidth("300px");

        Button agregar = new Button("Agregar");
        agregar.addClickShortcut(Key.ENTER);
        agregar.addClickListener(evento -> {
            String texto = campo.getValue().trim();
            if (texto.isEmpty()) {
                Notification.show("Escribe una descripcion");
                return;
            }
            tareas.add(new Tarea(siguienteId, texto));
            siguienteId++;
            campo.clear();
            campo.focus();
            refrescar();
            Notification.show("Tarea agregada");
        });

        HorizontalLayout formulario = new HorizontalLayout(campo, agregar);
        formulario.setAlignItems(Alignment.BASELINE);

        lista.setPadding(false);
        lista.setSpacing(false);
        refrescar();

        add(new H2("Gestor de Tareas"), formulario, lista);
    }

    private void refrescar() {
        lista.removeAll();
        if (tareas.isEmpty()) {
            lista.add(new Paragraph("No hay tareas."));
            return;
        }
        for (Tarea t : tareas) {
            lista.add(new Paragraph(t.id() + ". " + t.descripcion()));
        }
    }
}
