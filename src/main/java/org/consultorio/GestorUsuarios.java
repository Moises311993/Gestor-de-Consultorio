package org.consultorio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class GestorUsuarios implements Serializador {
    private static final String DB_DIR = "src/main/resources/db";
    public static final String DB_USUARIOS = DB_DIR + "/usuarios.json";
    public static final String DB_ADMIN = DB_DIR + "/admin.json";

    private final static ObjectMapper objectMapperUser = new ObjectMapper();
    private final static ObjectMapper objectMapperAdmin = new ObjectMapper();

    private Administrador administrador;

    private ListaUsuarios usuarios = new ListaUsuarios();



    public void agregarUsuario(Usuario usuario) {
        usuarios.getUsuarios().add(usuario);

    }

    public ListaUsuarios getUsuarios() {
        return usuarios;
    }

    public Administrador getAdministrador() {
        return this.administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public boolean eliminarUsuario(String id) {
        return usuarios.getUsuarios().removeIf(usuario -> usuario.getId().equals(id));
    }

    public List<Usuario> listarUsuarios() {
        return usuarios.getUsuarios();
    }

    public Optional<Usuario> buscarUsuario(String id){

        return listarUsuarios().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public boolean existeUsuario(String id){
        return buscarUsuario(id).isPresent();
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
            if(archivo.equals(DB_ADMIN)){
                objectMapperAdmin.writeValue(new File(archivo), objeto);
            } else {
                objectMapperUser.writeValue(new File(archivo), objeto);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    @Override
    public <T>  void cargarDesdeArchivo(String archivo, Class<T> tipo) {

        try {
            if(archivo.equals(DB_ADMIN)){
                this.administrador = objectMapperAdmin.readValue(new File(archivo), Administrador.class);
            } else {
                this.usuarios = objectMapperUser.readValue(new File(archivo), ListaUsuarios.class);
            }
        } catch (IOException e) {
        }
    }

}