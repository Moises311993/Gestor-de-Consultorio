package org.consultorio;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Doctor.class, name = "doctor"),
        @JsonSubTypes.Type(value = Paciente.class, name = "paciente")
})
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
