package org.consultorio;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("paciente")
public class Paciente extends Usuario {

    public Paciente(){

    }
    public Paciente(String id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }
}
