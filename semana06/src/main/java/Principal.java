import modelo.Estudiante;
import servicio.SistemaEstudiantes;

public class Principal {

    public static void main(String[] args) {
        SistemaEstudiantes sistema = new SistemaEstudiantes();
        sistema.registrar(new Estudiante("A1", "Ana", 90));
        sistema.registrar(new Estudiante("B2", "Luis", 75));
        sistema.registrar(new Estudiante("C3", "Marta", 90));

        System.out.println("===== RANKING =====");
        for (Estudiante e : sistema.listarRanking()) {
            System.out.println(e);
        }

        System.out.println("===== TOP 2 =====");
        System.out.println(sistema.topN(2));
    }
}
