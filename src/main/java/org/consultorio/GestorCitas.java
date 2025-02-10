package org.consultorio;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorCitas {
    private List<Cita> citas = new ArrayList<>();



    public void registrarCita(Cita cita) {
        citas.add(cita);
    }

    public boolean eliminarCita(String id) {
        return citas.removeIf(c -> c.getId().equals(id));
    }

    public List<Cita> listarCitas() {
        return citas;
    }

    public Optional<Cita> buscarCita(String id){

        return listarCitas().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public boolean existeCita(String id){
        return buscarCita(id).isPresent();
    }


}
