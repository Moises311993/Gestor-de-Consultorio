package org.consultorio;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Cita {
    private  String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private  LocalDateTime fechaHora;
    private  String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(){

    }
    public Cita(String id, LocalDateTime fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

}