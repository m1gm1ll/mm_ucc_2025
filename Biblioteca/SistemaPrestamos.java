// SistemaPrestamos.java
import java.util.LinkedList;
import java.util.Queue;

public class SistemaPrestamos {
    private static Queue<Usuario> colaEspera = new LinkedList<>();

    public static void prestarLibro(Usuario u, Libro l) {
        if (l.isDisponible()) {
            l.setDisponible(false);
            u.getHistorial().add("Prestado: " + l.getTitulo());
            System.out.println("Libro prestado correctamente.");
        } else {
            colaEspera.add(u);
            System.out.println("Libro no disponible. Usuario en cola de espera.");
        }
    }

    public static void devolverLibro(Usuario u, Libro l) {
        l.setDisponible(true);
        u.getHistorial().add("Devuelto: " + l.getTitulo());
        System.out.println("Libro devuelto correctamente.");

        if (!colaEspera.isEmpty()) {
            Usuario siguiente = colaEspera.poll();
            prestarLibro(siguiente, l);
            System.out.println("El libro fue prestado al siguiente en la cola: " + siguiente.getNombre());
        }
    }
}