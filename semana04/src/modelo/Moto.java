package modelo;

public class Moto extends Vehiculo {

    private boolean tieneSidecar;

    public Moto(String marca, String modelo, double velocidadMax, boolean tieneSidecar) {
        super(marca, modelo, velocidadMax);
        this.tieneSidecar = tieneSidecar;
    }

    public boolean isTieneSidecar() {
        return tieneSidecar;
    }

    @Override
    public String describir() {
        String sidecar = tieneSidecar ? "con sidecar" : "sin sidecar";
        return "Moto " + marca + " " + modelo + " " + sidecar
                + ", velocidad maxima " + velocidadMax + " km/h";
    }
}
