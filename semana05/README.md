# Sistema de Estudiantes Avanzado

Sistema de consola para gestionar estudiantes (codigo unico, nombre y
promedio). Permite registrar, buscar, actualizar, eliminar, ver el ranking
y obtener el top N. Combina tres colecciones del Java Collections Framework.

## Por que cada coleccion

- **HashMap<String, Estudiante>**: se usa para buscar, actualizar y eliminar
  por codigo. Su acceso por clave es O(1) en promedio; con un ArrayList habria
  que recorrer toda la lista (O(n)) en cada busqueda.
- **TreeSet<Estudiante>**: mantiene el ranking siempre ordenado por promedio
  descendente (con desempate por codigo) gracias a `Comparable`, sin tener que
  ordenar manualmente en cada consulta. No admite duplicados.
- **ArrayList<Estudiante>**: es lo que devuelven `listarRanking()` y `topN()`.
  Se devuelve una copia y no el TreeSet interno para no exponer la estructura:
  si se devolviera el TreeSet, el codigo externo podria modificarlo y romper la
  sincronizacion con el HashMap.

## Decisiones de diseno

- `equals` y `hashCode` se basan solo en `codigo`, que es `final`.
- Al actualizar un promedio se hace `remove -> mutar -> add` en el TreeSet,
  porque cambiar el promedio de un objeto ya insertado dejaria el arbol
  desordenado.

## Compilar y ejecutar

    javac -d out src/modelo/*.java src/servicio/*.java src/Principal.java
    java -cp out Principal

## Ejemplo de salida

    1. Registrar 2. Buscar 3. Actualizar 4. Ranking 5. Top N 6. Eliminar 7. Salir
    Opcion: 4
    #A1 Ana (90.0)
    #C3 Marta (90.0)
    #B2 Luis (75.0)

    Opcion: 5
    N: 2
    [#A1 Ana (90.0), #C3 Marta (90.0)]
