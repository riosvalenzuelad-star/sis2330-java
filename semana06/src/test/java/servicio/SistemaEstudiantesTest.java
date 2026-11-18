package servicio;

import modelo.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SistemaEstudiantesTest {

    private SistemaEstudiantes sistema;

    @BeforeEach
    void iniciar() {
        sistema = new SistemaEstudiantes();
    }

    @Test
    void registrarEstudianteNuevoDevuelveTrue() {
        assertTrue(sistema.registrar(new Estudiante("A1", "Ana", 90)));
        assertEquals(1, sistema.cantidad());
    }

    @Test
    void registrarCodigoDuplicadoDevuelveFalse() {
        sistema.registrar(new Estudiante("A1", "Ana", 90));
        assertFalse(sistema.registrar(new Estudiante("A1", "Otra", 50)));
        assertEquals(1, sistema.cantidad());
    }

    @Test
    void buscarCodigoExistenteDevuelveElEstudiante() {
        sistema.registrar(new Estudiante("A1", "Ana", 90));
        Estudiante e = sistema.buscarPorCodigo("A1");
        assertNotNull(e);
        assertEquals("Ana", e.getNombre());
    }

    @Test
    void buscarCodigoInexistenteDevuelveNull() {
        assertNull(sistema.buscarPorCodigo("Z9"));
    }

    @Test
    void rankingSinEstudiantesDevuelveListaVacia() {
        assertTrue(sistema.listarRanking().isEmpty());
    }

    @Test
    void rankingOrdenaPorPromedioDescendente() {
        sistema.registrar(new Estudiante("A1", "Ana", 70));
        sistema.registrar(new Estudiante("B2", "Luis", 95));
        sistema.registrar(new Estudiante("C3", "Marta", 85));
        List<Estudiante> ranking = sistema.listarRanking();
        assertEquals("B2", ranking.get(0).getCodigo());
        assertEquals("C3", ranking.get(1).getCodigo());
        assertEquals("A1", ranking.get(2).getCodigo());
    }

    @Test
    void rankingConPromediosIgualesDesempataPorCodigo() {
        sistema.registrar(new Estudiante("C3", "Marta", 90));
        sistema.registrar(new Estudiante("A1", "Ana", 90));
        List<Estudiante> ranking = sistema.listarRanking();
        assertEquals(2, ranking.size());
        assertEquals("A1", ranking.get(0).getCodigo());
        assertEquals("C3", ranking.get(1).getCodigo());
    }

    @Test
    void eliminarCodigoExistenteDevuelveTrueYLoQuitaDelRanking() {
        sistema.registrar(new Estudiante("A1", "Ana", 90));
        assertTrue(sistema.eliminar("A1"));
        assertNull(sistema.buscarPorCodigo("A1"));
        assertTrue(sistema.listarRanking().isEmpty());
    }

    @Test
    void eliminarCodigoInexistenteDevuelveFalse() {
        assertFalse(sistema.eliminar("Z9"));
    }

    @Test
    void actualizarPromedioReordenaElRanking() {
        sistema.registrar(new Estudiante("A1", "Ana", 90));
        sistema.registrar(new Estudiante("B2", "Luis", 75));
        assertTrue(sistema.actualizarPromedio("B2", 99));
        assertEquals("B2", sistema.listarRanking().get(0).getCodigo());
    }

    @Test
    void actualizarPromedioDeCodigoInexistenteDevuelveFalse() {
        assertFalse(sistema.actualizarPromedio("Z9", 80));
    }

    @Test
    void actualizarPromedioInvalidoLanzaExcepcionYConservaAlEstudiante() {
        sistema.registrar(new Estudiante("A1", "Ana", 90));
        assertThrows(IllegalArgumentException.class,
                () -> sistema.actualizarPromedio("A1", 150));
        assertEquals(1, sistema.listarRanking().size());
        assertEquals(90, sistema.buscarPorCodigo("A1").getPromedio());
    }

    @Test
    void topNDevuelveSoloLosPrimerosN() {
        sistema.registrar(new Estudiante("A1", "Ana", 70));
        sistema.registrar(new Estudiante("B2", "Luis", 95));
        sistema.registrar(new Estudiante("C3", "Marta", 85));
        List<Estudiante> top = sistema.topN(2);
        assertEquals(2, top.size());
        assertEquals("B2", top.get(0).getCodigo());
        assertEquals("C3", top.get(1).getCodigo());
    }

    @Test
    void crearEstudianteConPromedioFueraDeRangoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("A1", "Ana", -1));
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("A1", "Ana", 100.5));
    }
}
