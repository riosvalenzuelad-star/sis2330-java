package modelo;

public class Camion extends Vehiculo {

    private double capacidadCarga;

    public Camion(String marca, String modelo, double velocidadMax, double capacidadCarga) {
        super(marca, modelo, velocidadMax);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    @Override
    public String describir() {
        return "Camion " + marca + " " + modelo + " con capacidad de "
                + capacidadCarga + " toneladas, velocidad maxima " + velocidadMax + " km/h";
    }
}
