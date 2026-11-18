package modelo;

import java.util.Objects;

public class Estudiante implements Comparable<Estudiante> {

    private final String codigo;
    private final String nombre;
    private double promedio;

    public Estudiante(String codigo, String nombre, double promedio) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        validarPromedio(promedio);
        this.codigo = codigo;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    private static void validarPromedio(double promedio) {
        if (promedio < 0.0 || promedio > 100.0) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 100");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        validarPromedio(promedio);
        this.promedio = promedio;
    }

    @Override
    public int compareTo(Estudiante otro) {
        int porPromedio = Double.compare(otro.promedio, this.promedio);
        if (porPromedio != 0) {
            return porPromedio;
        }
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estudiante)) {
            return false;
        }
        return codigo.equals(((Estudiante) o).codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format("#%s %s (%.1f)", codigo, nombre, promedio);
    }
}
