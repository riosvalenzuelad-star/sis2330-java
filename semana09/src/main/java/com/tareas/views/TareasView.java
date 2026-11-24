package com.tareas.views;

import com.tareas.Tarea;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Route(value = "", layout = MainLayout.class)
public class TareasView extends VerticalLayout {

    private static final List<Tarea> TAREAS = new CopyOnWriteArrayList<>();
    private static long siguienteId = 1;

    static {
        agregar("Estudiar el Router de Vaadin");
        agregar("Probar la navegacion con parametros");
    }

    public static synchronized Tarea agregar(String descripcion) {
        Tarea tarea = new Tarea(siguienteId, descripcion);
        siguienteId++;
        TAREAS.add(tarea);
        return tarea;
    }

    public static Optional<Tarea> buscarPorId(long id) {
        return TAREAS.stream().filter(t -> t.id() == id).findFirst();
    }

    private final Grid<Tarea> grid = new Grid<>();
    private final TextField campo = new TextField("Nueva tarea");

    public TareasView() {
        grid.addColumn(Tarea::id).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Tarea::descripcion).setHeader("Descripcion").setFlexGrow(1);
        grid.addComponentColumn(t -> new RouterLink("Ver detalle", TareaDetalleView.class, t.id()))
                .setHeader("Detalle")
                .setAutoWidth(true);

        campo.setWidth("320px");
        Button agregar = new Button("Agregar");
        agregar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agregar.addClickListener(evento -> {
            String texto = campo.getValue().trim();
            if (texto.isEmpty()) {
                Notification.show("Escribe una descripcion");
                return;
            }
            agregar(texto);
            campo.clear();
            refrescar();
            Notification.show("Tarea agregada");
        });

        HorizontalLayout formulario = new HorizontalLayout(campo, agregar);
        formulario.setAlignItems(Alignment.BASELINE);

        add(new H2("Lista de Tareas"), formulario, grid);
        refrescar();
    }

    private void refrescar() {
        grid.setItems(new ArrayList<>(TAREAS));
    }
}
