# Semana 10: Vaadin, Spring Boot y Deploy

## Gestor de Tareas con servicio y persistencia

Aplicacion Vaadin con Spring Boot que separa la logica de negocio en un
servicio (@Service) y guarda las tareas en tareas.json con Gson. Las tareas
sobreviven a los reinicios del servidor y la aplicacion se empaqueta como jar
ejecutable.

## Arquitectura

- TareaService (@Service): listar, agregar, actualizar, eliminar y buscarPorId.
  Carga tareas.json al arrancar y lo reescribe tras cada cambio.
- TareasView: Grid con formulario (Binder) para el CRUD y enlace al detalle.
- TareaDetalleView: recibe el id por URL (HasUrlParameter) y consulta el
  servicio.
- MainLayout y AcercaDeView: menu y vista informativa.

## Decisiones de diseno

- Las vistas reciben TareaService por constructor; Spring inyecta la misma
  instancia en todas. Nunca se usa new TareaService().
- Los metodos del servicio son synchronized para que varias sesiones no
  corrompan la lista.
- Si tareas.json no existe, se arranca con una lista vacia (Files.exists).
- El siguiente id se calcula como el maximo existente mas uno.
- tareas.json son datos de ejecucion y esta en .gitignore.

## Ejecutar en desarrollo

    mvn spring-boot:run

## Empaquetar y ejecutar como jar

    mvn clean package -Pproduction
    java -jar target/gestor-tareas-final-1.0-SNAPSHOT.jar

El puerto se puede cambiar sin tocar codigo:

    java -jar target/gestor-tareas-final-1.0-SNAPSHOT.jar --server.port=9090

El archivo tareas.json se crea en la carpeta desde donde se ejecuta el comando.

## Verificacion de persistencia

1. Agregar tareas desde la interfaz.
2. Detener el servidor y comprobar el contenido de tareas.json.
3. Volver a arrancar: las tareas siguen apareciendo.
