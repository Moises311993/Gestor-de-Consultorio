package org.consultorio;

public class Menu {
    private static final GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private static final GestorCitas gestorCitas = new GestorCitas();

    public static void main(String args[]){

    }



    public static void listarDoctores() {
        if(gestorUsuarios.listarUsuarios().stream().noneMatch(u -> u instanceof Doctor)){
            System.out.println("No hay doctores registrados");
            return;
        }
        gestorUsuarios.listarUsuarios().stream()
                .filter(u -> u instanceof Doctor)
                .forEach(u -> System.out.println(u.getId() + ": " + u.getNombre() + " " + u.getApellido() + " - " + ((Doctor) u).getEspecialidad()));
    }

    public static void listarPacientes() {
        if(gestorUsuarios.listarUsuarios().stream().noneMatch(u -> u instanceof Paciente)){
            System.out.println("No hay pacientes registrados");
            return;
        }
        gestorUsuarios.listarUsuarios().stream()
                .filter(u -> u instanceof Paciente)
                .forEach(u -> System.out.println(u.getId() + ": " + u.getNombre() + " " + u.getApellido()));
    }
    public static void listarCitas() {
        if(gestorCitas.listarCitas().isEmpty()){
            System.out.println("No hay citas registradas");
            return;
        }
        gestorCitas.listarCitas().forEach(c -> {
            System.out.println();
            System.out.println("ID de Cita: " + c.getId());
            System.out.println("Paciente: " + c.getPaciente().getNombre() + " " + c.getPaciente().getApellido());
            System.out.println("Doctor: " + c.getDoctor().getNombre() + " " + c.getDoctor().getApellido() + " | Especialidad: " + c.getDoctor().getEspecialidad());
            System.out.println("Hora: " + c.getFechaHora().toLocalDate() + " " + c.getFechaHora().getHour() + ":" + c.getFechaHora().getMinute());
            System.out.println("Motivo: " + c.getMotivo());


        });
    }
}
