package org.consultorio;


import java.util.List;
import java.util.Optional;

public class GestorUsuarios {



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

}