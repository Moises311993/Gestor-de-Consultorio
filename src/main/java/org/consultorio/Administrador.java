package org.consultorio;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Administrador{
    private String usuario;
    private String contraseña;

    public Administrador(){
    }


    @JsonCreator
    public Administrador(@JsonProperty("id") String usuario,
                         @JsonProperty("contraseña") String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public boolean autenticar(String usuario, String contraseña) {
        return this.getUsuario().equals(usuario) && this.getContraseña().equals(contraseña);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

}