package servicio;

import modelo.Estudiante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class SistemaEstudiantes {

    // HashMap: busqueda por codigo en O(1)
    private final HashMap<String, Estudiante> porCodigo = new HashMap<>();
    // TreeSet: ranking siempre ordenado (usa compareTo de Estudiante)
    private final TreeSet<Estudiante> ranking = new TreeSet<>();

    public boolean registrar(Estudiante e) {
        if (porCodigo.containsKey(e.getCodigo())) {
            return false; // codigo duplicado
        }
        porCodigo.put(e.getCodigo(), e);
        ranking.add(e);
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        return porCodigo.get(codigo); // O(1)
    }

    public boolean actualizarPromedio(String codigo, double nuevoPromedio) {
        Estudiante e = porCodigo.get(codigo);
        if (e == null) return false;
        ranking.remove(e);            // 1. sacarlo antes de mutar
        e.setPromedio(nuevoPromedio); // 2. mutar
        ranking.add(e);               // 3. reinsertarlo
        return true;
    }

    // Copia: no expone el TreeSet interno
    public ArrayList<Estudiante> listarRanking() {
        return new ArrayList<>(ranking);
    }

    public ArrayList<Estudiante> topN(int n) {
        ArrayList<Estudiante> top = new ArrayList<>();
        int i = 0;
        for (Estudiante e : ranking) {
            if (i >= n) break;
            top.add(e);
            i++;
        }
        return top;
    }

    public boolean eliminar(String codigo) {
        Estudiante e = porCodigo.remove(codigo);
        if (e == null) return false;
        ranking.remove(e);
        return true;
    }
}
