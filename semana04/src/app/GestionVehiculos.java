package app;

import modelo.Auto;
import modelo.AutoElectrico;
import modelo.Camion;
import modelo.Electrico;
import modelo.Moto;
import modelo.Vehiculo;
import java.util.ArrayList;

public class GestionVehiculos {

    public static void main(String[] args) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Auto("Toyota", "Corolla", 180, 4));
        vehiculos.add(new Moto("Honda", "CB500", 200, false));
        vehiculos.add(new Camion("Volvo", "FH16", 120, 25.5));
        vehiculos.add(new AutoElectrico("Tesla", "Model 3", 225, 4, 500));

        System.out.println("===== LISTA DE VEHICULOS =====");
        for (Vehiculo v : vehiculos) {
            System.out.println(v.describir());

            if (v instanceof Electrico e) {
                e.cargarBateria();
            }
        }
    }
}
