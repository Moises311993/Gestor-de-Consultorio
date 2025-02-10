package org.consultorio;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("doctor")
public class Doctor extends Usuario {
    private String especialidad;

    public Doctor(){

    }
    public Doctor(String id, String nombre, String apellido, String especialidad) {
        super(id, nombre, apellido);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}