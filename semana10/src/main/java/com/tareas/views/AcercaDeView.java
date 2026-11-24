package com.tareas.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "acerca-de", layout = MainLayout.class)
public class AcercaDeView extends VerticalLayout {

    public AcercaDeView() {
        add(new H2("Acerca de"));
        add(new Paragraph("Gestor de tareas hecho con Vaadin y Spring Boot."));
        add(new Paragraph("Muestra navegacion entre vistas con Router, un layout compartido y parametros en la URL."));
    }
}
