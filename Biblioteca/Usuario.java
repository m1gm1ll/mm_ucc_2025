// Usuario.java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class Usuario {
    private String nombre;
    private String id;
    private LinkedList<String> historialPrestamos = new LinkedList<>();

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public LinkedList<String> getHistorial() { return historialPrestamos; }

    public static void agregarUsuario(Usuario u) {
        listaUsuarios.add(u);
        System.out.println("Usuario agregado correctamente.");
    }

    public static Usuario buscarUsuario(String id) {
        Iterator<Usuario> it = listaUsuarios.iterator();
        while (it.hasNext()) {
            Usuario u = it.next();
            if (u.getId().equalsIgnoreCase(id)) return u;
        }
        return null;
    }

    public static void listarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : listaUsuarios) {
            System.out.println(u);
        }
    }

    public static void eliminarUsuario(String id) {
        Usuario u = buscarUsuario(id);
        if (u != null) {
            listaUsuarios.remove(u);
            System.out.println("Usuario eliminado.");
        } else {
            System.out.println("No se encontró el usuario.");
        }
    }

    public void mostrarHistorial() {
        Iterator<String> it = historialPrestamos.iterator();
        System.out.println("Historial de " + nombre + ":");
        while (it.hasNext()) {
            System.out.println("- " + it.next());
        }
    }

    @Override
    public String toString() {
        return "[Nombre: " + nombre + ", ID: " + id + "]";
    }
}