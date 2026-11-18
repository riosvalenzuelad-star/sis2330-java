# Sistema de Estudiantes con Maven y JUnit

Proyecto Maven que implementa un sistema de estudiantes con registro,
busqueda por codigo (HashMap, O(1)), ranking por promedio descendente
(TreeSet), top N y eliminacion. Incluye una suite de 14 tests con JUnit 5.

## Compilar, testear y empaquetar

    mvn compile
    mvn test
    mvn package
    java -jar target/sistema-estudiantes-1.0-SNAPSHOT.jar

## Tests

1. registrarEstudianteNuevoDevuelveTrue: registrar un codigo nuevo funciona.
2. registrarCodigoDuplicadoDevuelveFalse: no se permite repetir codigo.
3. buscarCodigoExistenteDevuelveElEstudiante: la busqueda encuentra al estudiante.
4. buscarCodigoInexistenteDevuelveNull: un codigo desconocido devuelve null.
5. rankingSinEstudiantesDevuelveListaVacia: caso borde, ranking vacio.
6. rankingOrdenaPorPromedioDescendente: el mayor promedio va primero.
7. rankingConPromediosIgualesDesempataPorCodigo: empate resuelto por codigo.
8. eliminarCodigoExistenteDevuelveTrueYLoQuitaDelRanking: elimina de ambas colecciones.
9. eliminarCodigoInexistenteDevuelveFalse: eliminar algo que no existe.
10. actualizarPromedioReordenaElRanking: cambiar el promedio cambia la posicion.
11. actualizarPromedioDeCodigoInexistenteDevuelveFalse: actualizar un codigo desconocido.
12. actualizarPromedioInvalidoLanzaExcepcionYConservaAlEstudiante: promedio invalido no pierde al estudiante.
13. topNDevuelveSoloLosPrimerosN: el top N respeta el limite.
14. crearEstudianteConPromedioFueraDeRangoLanzaExcepcion: validacion de rango 0 a 100.

## Salida de mvn test

    [INFO] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
