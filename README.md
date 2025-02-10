# Gestor de Citas Médicas
##  Descripción

Este sistema permite la administración de citas médicas de manera local, utilizando archivos JSON para el almacenamiento de datos. Permite gestionar doctores, pacientes y citas, asegurando la portabilidad del sistema como un FAT JAR.

## Instalación y Configuración

### Requisitos

- Java 11 o superior.
- Maven (para construir el proyecto).
- Un editor de texto o IDE compatible con Java (IntelliJ IDEA, NetBeans, VS Code, etc.).
### Instalación

1. Clona el repositorio:
```
git clone https://github.com/tu-usuario/gestor-citas-medicas.git
cd gestor-citas-medicas
```

2. Construye el proyecto con Maven:
```
mvn clean package
```

3.  Ejecuta el programa:

```
java -jar target/gestor-consultorio-jar-with-dependencies.jar
```

## Uso del Programa

1. Registrar un doctor o paciente: El programa permite registrar nuevos usuarios con atributos como nombre, ID y especialidad (en caso de los doctores).

2. Crear una cita: Se puede asignar una cita a un paciente con un doctor, estableciendo fecha y hora.

3. Consultar historial médico: Se accede al historial de cada paciente.

4. Guardar y cargar datos: El sistema almacena la información en archivos JSON dentro de la carpeta db/.

## Documentación

La documentación completa del sistema se encuentra en la sección Wiki del repositorio en GitHub. Las secciones incluidas son:

### Acerca de
- Explicación breve de la aplicación y su propósito.

### Proyecto

- Diagrama de flujo de la primera entrega.

- Diagrama de clases y explicación de la arquitectura del sistema.

- Descripción de las clases, sus atributos y métodos principales.

### Guías

- Pasos para configurar y ejecutar el programa.

- Cómo construir un JAR ejecutable desde el código almacenado en el repositorio.

- Puedes acceder a la documentación en el siguiente enlace: [Wiki del Proyecto](https://github.com/Moises311993/Gestor-de-Consultorio/wiki)

## Créditos

Desarrollador: Moises de Jesus Acosta Ojeda

## Licencia

Este proyecto está licenciado bajo la [MIT Licence](https://opensource.org/license/mit).

