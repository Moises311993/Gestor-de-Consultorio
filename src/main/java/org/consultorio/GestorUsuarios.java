package org.consultorio;


import java.util.List;

public class GestorUsuarios {



    private List<Usuario> usuarios;


    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);

    }

    public boolean eliminarUsuario(String id) {
        return usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}