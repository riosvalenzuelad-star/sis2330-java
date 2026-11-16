package modelo;

public class Tarea {

    private static int contador = 1;

    private int id;
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.id = contador;
        contador++;
        setDescripcion(descripcion); // reutiliza la validacion
        this.completada = false;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia");
        }
        this.descripcion = descripcion.trim();
    }

    public void marcarCompletada() {
        this.completada = true;
    }

    public void marcarPendiente() {
        this.completada = false;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        String estado = completada ? "[X]" : "[ ]";
        return estado + " (" + id + ") " + descripcion;
    }
}