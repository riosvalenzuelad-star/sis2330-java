package com.tareas.views;

import com.tareas.Tarea;
import com.tareas.TareaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "", layout = MainLayout.class)
public class TareasView extends HorizontalLayout {

    private final TareaService servicio;
    private final Grid<Tarea> grid = new Grid<>();
    private final TextField descripcion = new TextField("Descripcion");
    private final Checkbox completada = new Checkbox("Completada");
    private final Button guardar = new Button("Guardar");
    private final Button eliminar = new Button("Eliminar");
    private final Button nuevo = new Button("Nuevo");
    private final Binder<TareaEditable> binder = new Binder<>(TareaEditable.class);

    public TareasView(TareaService servicio) {
        this.servicio = servicio;

        configurarGrid();
        configurarBinder();

        guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        eliminar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        guardar.addClickListener(evento -> guardar());
        eliminar.addClickListener(evento -> eliminar());
        nuevo.addClickListener(evento -> limpiar());

        descripcion.setWidthFull();

        HorizontalLayout botones = new HorizontalLayout(guardar, eliminar, nuevo);
        VerticalLayout formulario = new VerticalLayout(new H3("Tarea"), descripcion, completada, botones);
        formulario.setWidth("320px");

        setSizeFull();
        add(grid, formulario);
        setFlexGrow(1, grid);

        refrescar();
        limpiar();
    }

    private void configurarGrid() {
        grid.addColumn(Tarea::id).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Tarea::descripcion).setHeader("Descripcion").setFlexGrow(1);
        grid.addColumn(t -> t.completada() ? "Si" : "No").setHeader("Completada").setAutoWidth(true);
        grid.addComponentColumn(t -> new RouterLink("Ver detalle", TareaDetalleView.class, t.id()))
                .setHeader("Detalle")
                .setAutoWidth(true);
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(evento -> cargarEnFormulario(evento.getValue()));
    }

    private void configurarBinder() {
        binder.forField(descripcion)
                .asRequired("La descripcion es obligatoria")
                .withValidator(texto -> !texto.isBlank(), "La descripcion no puede ser solo espacios")
                .bind(TareaEditable::getDescripcion, TareaEditable::setDescripcion);
        binder.forField(completada)
                .bind(TareaEditable::isCompletada, TareaEditable::setCompletada);
    }

    private void cargarEnFormulario(Tarea tarea) {
        if (tarea == null) {
            binder.readBean(new TareaEditable());
            eliminar.setEnabled(false);
            return;
        }
        binder.readBean(new TareaEditable(tarea));
        eliminar.setEnabled(true);
    }

    private void guardar() {
        TareaEditable datos = new TareaEditable();
        if (!binder.writeBeanIfValid(datos)) {
            return;
        }
        String texto = datos.getDescripcion().trim();
        Tarea seleccionada = grid.asSingleSelect().getValue();

        if (seleccionada == null) {
            servicio.agregar(texto, datos.isCompletada());
            Notification.show("Tarea agregada");
        } else {
            servicio.actualizar(seleccionada.id(), texto, datos.isCompletada());
            Notification.show("Tarea actualizada");
        }
        refrescar();
        limpiar();
    }

    private void eliminar() {
        Tarea seleccionada = grid.asSingleSelect().getValue();
        if (seleccionada == null) {
            Notification.show("Selecciona una tarea");
            return;
        }
        servicio.eliminar(seleccionada.id());
        Notification.show("Tarea eliminada");
        refrescar();
        limpiar();
    }

    private void refrescar() {
        grid.setItems(servicio.listar());
    }

    private void limpiar() {
        grid.asSingleSelect().clear();
        cargarEnFormulario(null);
    }
}
