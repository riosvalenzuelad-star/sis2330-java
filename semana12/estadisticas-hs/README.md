# estadisticas-hs

Proyecto Haskell con Stack que procesa una lista de estudiantes, cada uno
representado como una tupla (nombre, nota). Calcula el promedio, el estudiante
con la nota mas alta, la lista ordenada por nota y los aprobados. Main.hs lee
los datos por consola y muestra el reporte.

## Modulos

- src/Estadisticas.hs: funciones puras (sin IO) sobre listas de estudiantes.
  - type Estudiante = (String, Double)
  - promedio :: [Estudiante] -> Double (recursiva)
  - masAlto :: [Estudiante] -> Estudiante (recursiva con guardas)
  - ordenarPorNota :: [Estudiante] -> [Estudiante] (usa Data.List.sortBy)
  - aprobados :: [Estudiante] -> [Estudiante] (comprension de lista, nota >= 51)
- app/Main.hs: toda la entrada y salida. Lee N estudiantes con validacion y
  delega los calculos a Estadisticas.
- test/Spec.hs: pruebas de las funciones puras.

## Ejemplo de entrada y salida

    Cuantos estudiantes? 3
    Nombre del estudiante 1: Ana
    Nota de Ana: 80
    Nombre del estudiante 2: Luis
    Nota de Luis: 45
    Nombre del estudiante 3: Marta
    Nota de Marta: 95

    ===== REPORTE =====
    Promedio: 73.33333333333333
    Mas alto: Marta (95.0)
    Ordenados por nota:
      Marta (95.0)
      Ana (80.0)
      Luis (45.0)
    Aprobados:
      Marta (95.0)
      Ana (80.0)

Con 0 estudiantes el programa muestra promedio 0.0 y "No hay estudiantes."
sin llamar a masAlto.

## Como compilar y ejecutar

    stack build
    stack run
    stack test
