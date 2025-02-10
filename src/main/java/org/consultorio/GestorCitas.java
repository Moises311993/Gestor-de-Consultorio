package org.consultorio;


import java.util.ArrayList;
import java.util.List;

public class GestorCitas {
    private List<Cita> citas = new ArrayList<>();



    public void registrarCita(Cita cita) {
        citas.add(cita);
    }

    public boolean eliminarCita(String id) {
        return citas.removeIf(c -> c.getId().equals(id));
    }

}
