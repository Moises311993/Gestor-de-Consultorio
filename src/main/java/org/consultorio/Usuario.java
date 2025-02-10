package org.consultorio;

public abstract class Usuario {
    protected String id;
    protected String nombre;
    protected String apellido;

    public Usuario(){

    }
    public Usuario(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
