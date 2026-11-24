# Semana 09: Vaadin, Router y Vistas

## Gestor de Tareas con navegacion multi-vista

Aplicacion Vaadin con Spring Boot dividida en varias vistas con su propia URL,
envueltas en un layout compartido con menu lateral.

## Vistas

- MainLayout: AppLayout con DrawerToggle y menu (Lista de Tareas, Acerca de).
- TareasView (ruta ""): Grid con las tareas, campo para agregar y columna con
  RouterLink "Ver detalle" hacia /tareas/{id}.
- TareaDetalleView (ruta "tareas"): implementa HasUrlParameter<Long>, recibe el
  id por la URL y muestra el detalle.
- AcercaDeView (ruta "acerca-de"): texto descriptivo del proyecto.

## Decisiones de diseno

- Cada vista usa layout = MainLayout.class para compartir el menu.
- La lista de tareas es estatica en TareasView y el detalle la consulta con
  TareasView.buscarPorId, que devuelve un Optional.
- Si el id no existe, el detalle muestra "Tarea no encontrada" en lugar de fallar.
- No hay rutas duplicadas, por lo que el arranque no lanza
  AmbiguousRouteConfigurationException.

## Ejecutar

    mvn spring-boot:run

Luego abrir http://localhost:8080 en el navegador.

## Estructura

    src/main/java/com/tareas/Application.java
    src/main/java/com/tareas/Tarea.java
    src/main/java/com/tareas/views/MainLayout.java
    src/main/java/com/tareas/views/TareasView.java
    src/main/java/com/tareas/views/TareaDetalleView.java
    src/main/java/com/tareas/views/AcercaDeView.java
    src/main/resources/application.properties
