import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaCalificaciones {

    static final int ESTUDIANTES = 5;
    static final int NOTAS = 3;

    static double leerNota(Scanner sc, String mensaje) {
        double nota = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensaje);
                nota = sc.nextDouble();
                if (nota < 0 || nota > 100) {
                    System.out.println("Error: la nota debe estar entre 0 y 100.");
                    continue;
                }
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un numero valido.");
                sc.next(); 
            }
        }
        return nota;
    }

    static double promedio(double[] notas) {
        double suma = 0;
        for (double n : notas) {
            suma += n;
        }
        return suma / notas.length;
    }

    static double maximo(double[] notas) {
        double max = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
            }
        }
        return max;
    }

    static double minimo(double[] notas) {
        double min = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < min) {
                min = notas[i];
            }
        }
        return min;
    }

    static String estado(double promedio) {
        return promedio >= 51 ? "Aprobado" : "Reprobado";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nombres = new String[ESTUDIANTES];
        double[][] notas = new double[ESTUDIANTES][NOTAS];

        for (int i = 0; i < ESTUDIANTES; i++) {
            System.out.print("Nombre del estudiante " + (i + 1) + ": ");
            nombres[i] = sc.next();
            for (int j = 0; j < NOTAS; j++) {
                notas[i][j] = leerNota(sc, "  Nota " + (j + 1) + ": ");
            }
        }

        System.out.println();
        System.out.println("===== REPORTE FINAL =====");
        System.out.printf("%-12s %-10s %-8s %-8s %-10s%n",
                "Nombre", "Promedio", "Max", "Min", "Estado");
        for (int i = 0; i < ESTUDIANTES; i++) {
            double prom = promedio(notas[i]);
            System.out.printf("%-12s %-10.2f %-8.1f %-8.1f %-10s%n",
                    nombres[i], prom, maximo(notas[i]), minimo(notas[i]), estado(prom));
        }

        sc.close();
    }
}