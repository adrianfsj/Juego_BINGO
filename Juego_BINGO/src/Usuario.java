public class Usuario {
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String contrasena;
    private int edad;

    public Usuario(String nombre, String apellidos, String correoElectronico, String contrasena, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getEdad() {
        return edad;
    }

    
    public String toString() {
        return "Nombre: " + nombre +
                "\nApellidos: " + apellidos +
                "\nCorreo electrónico: " + correoElectronico +
                "\nContraseña: " + contrasena +
                "\nEdad: " + edad;
    }
}
