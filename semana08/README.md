# Semana 08: Vaadin, Grid, Binder y CRUD

## Gestor de Tareas con CRUD

Aplicacion web con Vaadin y Spring Boot que muestra las tareas en una tabla
(Grid) y permite crear, editar y eliminar mediante un formulario lateral
validado con Binder. Los datos viven en una lista en memoria.

## Funcionalidad

- Grid con columnas ID, Descripcion y Completada.
- Formulario con descripcion obligatoria y checkbox de completada.
- Guardar agrega una tarea nueva si no hay fila seleccionada, o actualiza la
  seleccionada si la hay.
- Eliminar quita la tarea seleccionada.
- Al hacer clic en una fila, el formulario se llena con sus datos.

## Decisiones de diseno

- Tarea es un record inmutable. Como Binder necesita setters, se usa la clase
  mutable TareaEditable solo dentro del formulario, y al guardar se construye
  un Tarea nuevo.
- Las tareas se buscan por id, no por referencia, porque al editar se crea un
  objeto nuevo.
- grid.setItems se llama despues de cada operacion para refrescar la tabla.
- Se revisa el resultado de writeBeanIfValid antes de guardar.

## Ejecutar

    mvn spring-boot:run

Luego abrir http://localhost:8080 en el navegador.

## Estructura

    src/main/java/com/tareas/Application.java
    src/main/java/com/tareas/Tarea.java
    src/main/java/com/tareas/views/TareaEditable.java
    src/main/java/com/tareas/views/TareasView.java
    src/main/resources/application.properties
