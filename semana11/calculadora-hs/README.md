# calculadora-hs

Calculadora hecha en Haskell con Stack. Lee dos numeros por consola y muestra
su suma, el doble del primero y el promedio de ambos. Separa la logica pura
(Lib.hs) de la interaccion con el usuario (Main.hs).

## Como correr

    stack build
    stack run

Para probar las funciones de forma interactiva:

    stack ghci

Para ejecutar las pruebas:

    stack test

## Modulos

- src/Lib.hs: funciones puras y tipadas, sin IO.
  - suma :: Double -> Double -> Double
  - doble :: Double -> Double
  - promedio :: [Double] -> Double
- app/Main.hs: toda la entrada y salida (IO). Lee dos numeros con validacion
  y delega los calculos a Lib.
- test/Spec.hs: pruebas de las funciones puras de Lib.

## Descripcion

Al ejecutar stack run el programa pide dos numeros. Si se escribe algo que no
es un numero, vuelve a pedirlo. Luego muestra la suma, el doble del primero y
el promedio. Como las funciones de Lib no usan IO, son puras: la misma entrada
siempre da la misma salida y se pueden probar sin preparar nada.
