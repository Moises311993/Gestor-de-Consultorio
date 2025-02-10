package org.consultorio;

public interface Serializador {
    <T> void guardarEnArchivo(String archivo, T objeto);
    <T> void cargarDesdeArchivo(String archivo, Class<T> tipo);
}