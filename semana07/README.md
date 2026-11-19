# Semana 07: Vaadin, Fundamentos

## Gestor de Tareas

Aplicacion web hecha solo con Java usando Vaadin y Spring Boot. Permite
agregar tareas a una lista en memoria y verlas como texto en la pagina.

## Que se aprendio

- Anotacion @Route para publicar una vista en una URL.
- Vistas como clases que extienden VerticalLayout.
- Componentes TextField, Button, HorizontalLayout, Paragraph y Notification.
- Listeners con lambdas que se ejecutan en el servidor.
- Proyecto Maven con Spring Boot y la dependencia de Vaadin.

## Ejecutar

    mvn spring-boot:run

Luego abrir http://localhost:8080 en el navegador.

## Estructura

    src/main/java/com/tareas/Application.java
    src/main/java/com/tareas/Tarea.java
    src/main/java/com/tareas/views/TareasView.java
    src/main/resources/application.properties
