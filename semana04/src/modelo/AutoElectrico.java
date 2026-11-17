package modelo;

public class AutoElectrico extends Auto implements Electrico {

    private int autonomiaKm;

    public AutoElectrico(String marca, String modelo, double velocidadMax,
                         int numPuertas, int autonomiaKm) {
        super(marca, modelo, velocidadMax, numPuertas);
        this.autonomiaKm = autonomiaKm;
    }

    public int getAutonomiaKm() {
        return autonomiaKm;
    }

    @Override
    public void cargarBateria() {
        System.out.println("Cargando bateria del " + marca + " " + modelo + "...");
    }

    @Override
    public String describir() {
        return super.describir() + ", electrico con autonomia de " + autonomiaKm + " km";
    }
}
