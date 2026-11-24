package com.tareas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private final Path archivo;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final List<Tarea> tareas = new ArrayList<>();
    private long siguienteId = 1;

    public TareaService(@Value("${tareas.archivo:tareas.json}") String ruta) {
        this.archivo = Path.of(ruta);
        cargar();
    }

    public synchronized List<Tarea> listar() {
        return new ArrayList<>(tareas);
    }

    public synchronized Optional<Tarea> buscarPorId(long id) {
        return tareas.stream().filter(t -> t.id() == id).findFirst();
    }

    public synchronized Tarea agregar(String descripcion, boolean completada) {
        Tarea tarea = new Tarea(siguienteId, descripcion, completada);
        siguienteId++;
        tareas.add(tarea);
        guardar();
        return tarea;
    }

    public synchronized boolean actualizar(long id, String descripcion, boolean completada) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).id() == id) {
                tareas.set(i, new Tarea(id, descripcion, completada));
                guardar();
                return true;
            }
        }
        return false;
    }

    public synchronized boolean eliminar(long id) {
        boolean eliminada = tareas.removeIf(t -> t.id() == id);
        if (eliminada) {
            guardar();
        }
        return eliminada;
    }

    private void cargar() {
        if (!Files.exists(archivo)) {
            return;
        }
        try (Reader lector = Files.newBufferedReader(archivo)) {
            Type tipo = new TypeToken<List<Tarea>>() { }.getType();
            List<Tarea> leidas = gson.fromJson(lector, tipo);
            if (leidas != null) {
                tareas.addAll(leidas);
                siguienteId = leidas.stream().mapToLong(Tarea::id).max().orElse(0) + 1;
            }
        } catch (IOException | JsonParseException e) {
            System.err.println("No se pudo leer " + archivo + ": " + e.getMessage());
        }
    }

    private void guardar() {
        try (Writer escritor = Files.newBufferedWriter(archivo)) {
            gson.toJson(tareas, escritor);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo escribir " + archivo, e);
        }
    }
}
