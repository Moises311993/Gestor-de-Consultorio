package org.consultorio;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GestorUsuarios implements Serializador{
    private static final String DB_DIR = "src/main/resources/db";
    public static final String DB_USUARIOS = DB_DIR + "/usuarios.json";

    private final static ObjectMapper objectMapper = new ObjectMapper();


    private List<Usuario> usuarios;


    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);

    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Optional<Usuario> buscarUsuario(String id){

        return listarUsuarios().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public boolean existeUsuario(String id){
        return buscarUsuario(id).isPresent();
    }

    public boolean eliminarUsuario(String id) {
        return usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public <T> void guardarEnArchivo(String archivo, T objeto) {
        File file = new File(archivo);
        File dbDir = file.getParentFile();
        dbDir.mkdirs();


        try {
            file.createNewFile();
        }catch (IOException e){
            System.out.println("Error al crear la base de datos de usuarios");
        }

        try {
            objectMapper.writeValue(new File(archivo), objeto);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    @Override
    public <T>  void cargarDesdeArchivo(String archivo, Class<T> tipo) {

        try {

                this.usuarios = objectMapper.readValue(new File(archivo), new TypeReference<List<Usuario>>() {
                });

        } catch (IOException e) {
        }
    }


}