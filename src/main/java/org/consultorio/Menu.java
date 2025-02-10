package org.consultorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import static org.consultorio.GestorCitas.DB_CITAS;
import static org.consultorio.GestorUsuarios.DB_ADMIN;
import static org.consultorio.GestorUsuarios.DB_USUARIOS;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private static final GestorCitas gestorCitas = new GestorCitas();

    public static void main(String args[]){

    }


    public static void mostrarMenuPublico() {
        boolean continuar = true;
        boolean login = false;
        while (continuar && !login) {
            System.out.println("\n--- Menú ---");
            System.out.println("0. Ver Doctores disponibles");
            System.out.println("1. Ver Pacientes registrados");
            System.out.println("2. Ver Citas disponibes");
            System.out.println("3. Salir");

            int opcion = solicitarEntero("Seleccione una opción: ");

            switch (opcion) {
                case 0:
                    listarDoctores();
                    break;
                case 1:
                    listarPacientes();
                    break;
                case 2:
                    listarCitas();
                    break;

                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

    }

    public static int solicitarEntero(String mensaje){
        int numero;
        while(true){
            System.out.println(mensaje);
            try{
                numero = scanner.nextInt();
                scanner.nextLine();
                return numero;
            } catch (Exception e) {
                System.out.println("Porfavor ingresa un número entero como respuesta");
                scanner.nextLine();
            }
        }
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
    private static void eliminarUsuario() {
        System.out.print("Ingrese ID del usuario a eliminar: ");
        String id = scanner.nextLine();
        if(gestorUsuarios.eliminarUsuario(id)){
            System.out.println("El usuario se eliminó exitosamente");
        } else {
            System.out.println("El usuario con ese ID no existe");
        }

    }
    public static void registrarPaciente() {
        try {
            System.out.print("Ingrese ID del paciente: ");
            String id = scanner.nextLine();
            if (gestorUsuarios.existeUsuario(id)) throw new UserExistsException("");
            System.out.print("Ingrese nombre del paciente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese apellido del paciente: ");
            String apellido = scanner.nextLine();

            Paciente paciente = new Paciente(id, nombre, apellido);
            gestorUsuarios.agregarUsuario(paciente);
            gestorUsuarios.guardarEnArchivo(DB_USUARIOS, gestorUsuarios.getUsuarios());
            System.out.println("Paciente registrado.");
        } catch (UserExistsException e){
            System.out.println("El paciente con ese ID ya existe");
        }
    }

    public static LocalDateTime soliticitarFecha(String mensaje){
        String dateFormatStr = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatStr);
        while(true){
            System.out.println(mensaje + "en formato " + dateFormatStr);
            String fechaHoraStr = scanner.nextLine();
            try{
                return LocalDateTime.parse(fechaHoraStr,formatter);
            } catch (Exception e) {
                System.out.println("Porfavor ingresa una fecha en el formato solicitado");
            }
        }
    }


    public static void crearCita() {
        try {
            System.out.print("Ingrese ID de la cita: ");
            String id = scanner.nextLine();
            if (gestorCitas.existeCita(id)) throw new AppointmentExistsException("");

            LocalDateTime fechaHora = soliticitarFecha("Ingrese fecha y hora de la cita");
            System.out.print("Ingrese motivo de la cita: ");
            String motivo = scanner.nextLine();

            System.out.print("Ingrese ID del doctor: ");
            String idDoctor = scanner.nextLine();
            Optional<Usuario> doctor = gestorUsuarios.buscarUsuario(idDoctor);
            if(doctor.isEmpty()) throw new UserNotFoundException("El doctor ingresado no existe");

            System.out.print("Ingrese ID del paciente: ");
            String idPaciente = scanner.nextLine();
            Optional<Usuario> paciente = gestorUsuarios.buscarUsuario(idPaciente);
            if(paciente.isEmpty()) throw new UserNotFoundException("El paciente ingresado no existe");

            Cita cita = new Cita(id, fechaHora, motivo, (Doctor) doctor.get(), (Paciente) paciente.get());
            gestorCitas.registrarCita(cita);
            gestorCitas.guardarEnArchivo(DB_CITAS, gestorCitas.listarCitas());
            System.out.println("Cita creada.");

        } catch (UserNotFoundException e){
            System.out.println(e.getMessage());
        } catch (AppointmentExistsException e){
            System.out.println("La cita con este ID ya existe");
        }
    }

    private static void eliminarCita() {
        System.out.print("Ingrese ID de la cita a eliminar: ");
        String id = scanner.nextLine();
        if(gestorCitas.eliminarCita(id)){
            System.out.println("La cita se eliminó exitosamente");
        } else {
            System.out.println("La cita con ese ID no existe");
        }
    }

}
