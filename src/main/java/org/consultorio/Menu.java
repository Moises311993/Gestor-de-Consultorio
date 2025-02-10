package org.consultorio;

import java.util.Scanner;

import static org.consultorio.GestorUsuarios.DB_ADMIN;
import static org.consultorio.GestorUsuarios.DB_USUARIOS;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
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


    public static void crearAdministrador() {
        System.out.println("Por favor, ingresa el nombre de usuario para el administrador:");
        String usuario = scanner.nextLine();

        System.out.println("Por favor, ingresa una contraseña para el administrador:");
        String contraseña = scanner.nextLine();

        gestorUsuarios.setAdministrador(new Administrador( usuario, contraseña));
        gestorUsuarios.guardarEnArchivo(DB_ADMIN, gestorUsuarios.getAdministrador());
    }
    public static boolean autenticarUsuario() {
        if(gestorUsuarios.getAdministrador() == null) {
            crearAdministrador();
            return true;
        } else {
            System.out.println("Por favor, ingresa tu nombre de usuario:");
            String usuario = scanner.nextLine();
            System.out.println("Por favor, ingresa tu contraseña:");
            String contrasena = scanner.nextLine();
            boolean isAdmin = gestorUsuarios.getAdministrador().autenticar(usuario,contrasena);
            if(isAdmin) {
                System.out.println("Bienvenido superusuario administrador");
            } else {
                System.out.println("Credenciales incorrectas");
            }
            return isAdmin;
        }
    }


    public static void cambiarCredenciales(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa el nuevo nombre de usuario:");
        String nuevoUsuario = scanner.nextLine();

        System.out.println("Ingresa la nueva contraseña:");
        String nuevaContrasena = scanner.nextLine();

        gestorUsuarios.getAdministrador().setUsuario(nuevoUsuario);
        gestorUsuarios.getAdministrador().setContraseña(nuevaContrasena);

        // Guardar los nuevos datos del administrador en JSON
        gestorUsuarios.guardarEnArchivo(DB_ADMIN, gestorUsuarios.getAdministrador());
    }

    public static void registrarDoctor() {
        try{
            System.out.print("Ingrese ID del doctor: ");
            String id = scanner.nextLine();
            if(gestorUsuarios.existeUsuario(id)) throw new UserExistsException("");
            System.out.print("Ingrese nombre del doctor: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese apellido del doctor: ");
            String apellido = scanner.nextLine();
            System.out.print("Ingrese especialidad del doctor: ");
            String especialidad = scanner.nextLine();

            Doctor doctor = new Doctor(id, nombre, apellido, especialidad);
            gestorUsuarios.agregarUsuario(doctor);
            gestorUsuarios.guardarEnArchivo(DB_USUARIOS, gestorUsuarios.getUsuarios());
            System.out.println("Doctor registrado.");
        } catch (UserExistsException e){
            System.out.println("El doctor con ese ID ya existe");
        }

    }

}
