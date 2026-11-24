package com.tareas.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        H1 titulo = new H1("Gestor de Tareas");
        titulo.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        addToNavbar(new DrawerToggle(), titulo);

        RouterLink lista = new RouterLink("Lista de Tareas", TareasView.class);
        RouterLink acerca = new RouterLink("Acerca de", AcercaDeView.class);
        addToDrawer(new VerticalLayout(lista, acerca));
    }
}
