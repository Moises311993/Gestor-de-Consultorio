package org.consultorio;


public class Administrador{
    private String usuario;
    private String contraseña;

    public Administrador(){
    }

    public Administrador( String usuario,
                          String contraseña) {
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