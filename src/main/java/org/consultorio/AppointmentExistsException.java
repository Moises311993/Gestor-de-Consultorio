package org.consultorio;

public class AppointmentExistsException extends RuntimeException {
    public AppointmentExistsException(String message) {
        super(message);
    }
}
