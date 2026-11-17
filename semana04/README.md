# Semana 04: POO II

## Gestion de Vehiculos

Jerarquia de vehiculos con clase abstracta `Vehiculo`, subclases `Auto`,
`Moto` y `Camion`, e interfaz `Electrico` implementada por `AutoElectrico`.
Se recorre una lista polimorfica de `Vehiculo`.

## Que se aprendio

Herencia (extends, super), polimorfismo (@Override, upcasting, binding
dinamico), clases abstractas, interfaces e instanceof.

## Compilar y ejecutar

    javac -d out src/modelo/*.java src/app/GestionVehiculos.java
    java -cp out app.GestionVehiculos
