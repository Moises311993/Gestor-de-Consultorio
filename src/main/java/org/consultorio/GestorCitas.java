package org.consultorio;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorCitas implements Serializador{
    private List<Cita> citas = new ArrayList<>();
    public static final String DB_DIR = "src/main/resources/db";
    public static final String DB_CITAS = DB_DIR + "/citas.json";
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public GestorCitas(){
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void registrarCita(Cita cita) {
        citas.add(cita);
    }

    public boolean eliminarCita(String id) {
        return citas.removeIf(c -> c.getId().equals(id));
    }

    public List<Cita> listarCitas() {
        return citas;
    }

    public Optional<Cita> buscarCita(String id){

        return listarCitas().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public boolean existeCita(String id){
        return buscarCita(id).isPresent();
    }

    @Override
    public <T> void guardarEnArchivo(String archivo, T objeto) {
        File dbDir = new File(archivo).getParentFile();
        if (!dbDir.exists()) {
            dbDir.mkdirs();  // Crea la carpeta 'db' si no existe
        }

        try {

            objectMapper.writeValue(new File(archivo), objeto);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    @Override
    public <T> void cargarDesdeArchivo(String archivo, Class<T> tipo){
        try {
            citas = objectMapper.readValue(new File(archivo), new TypeReference<List<Cita>>() {
            });
        } catch (IOException e) {

        }
    }

}
