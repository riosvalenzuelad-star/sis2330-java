import servicio.SistemaEstudiantes;
import modelo.Estudiante;
import java.util.Scanner;

public class Principal {

    static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    static double leerPromedio(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double p = Double.parseDouble(sc.nextLine().trim());
                if (p < 0.0 || p > 100.0) {
                    System.out.println("El promedio debe estar entre 0 y 100.");
                    continue;
                }
                return p;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    public static void main(String[] args) {
        SistemaEstudiantes sistema = new SistemaEstudiantes();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n1. Registrar 2. Buscar 3. Actualizar 4. Ranking 5. Top N 6. Eliminar 7. Salir");
            opcion = leerEntero(sc, "Opcion: ");

            switch (opcion) {
                case 1 -> {
                    System.out.print("Codigo: ");
                    String cod = sc.nextLine().trim();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine().trim();
                    double prom = leerPromedio(sc, "Promedio: ");
                    try {
                        boolean ok = sistema.registrar(new Estudiante(cod, nom, prom));
                        System.out.println(ok ? "Registrado" : "Codigo duplicado");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Codigo a buscar: ");
                    Estudiante e = sistema.buscarPorCodigo(sc.nextLine().trim());
                    System.out.println(e != null ? e : "No encontrado");
                }
                case 3 -> {
                    System.out.print("Codigo: ");
                    String c2 = sc.nextLine().trim();
                    if (sistema.buscarPorCodigo(c2) == null) {
                        System.out.println("No encontrado");
                    } else {
                        double np = leerPromedio(sc, "Nuevo promedio: ");
                        sistema.actualizarPromedio(c2, np);
                        System.out.println("OK");
                    }
                }
                case 4 -> {
                    var lista = sistema.listarRanking();
                    if (lista.isEmpty()) System.out.println("No hay estudiantes.");
                    for (Estudiante r : lista) {
                        System.out.println(r);
                    }
                }
                case 5 -> {
                    int n = leerEntero(sc, "N: ");
                    System.out.println(sistema.topN(n));
                }
                case 6 -> {
                    System.out.print("Codigo a eliminar: ");
                    boolean ok = sistema.eliminar(sc.nextLine().trim());
                    System.out.println(ok ? "Eliminado" : "No encontrado");
                }
                case 7 -> System.out.println("Adios.");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 7);

        sc.close();
    }
}
