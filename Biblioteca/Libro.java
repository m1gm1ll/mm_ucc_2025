// Libro.java
import java.util.ArrayList;

public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private boolean disponible;

    public static ArrayList<Libro> listaLibros = new ArrayList<>();

    public Libro(String titulo, String autor, String isbn, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.disponible = true;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean d) { this.disponible = d; }

    public static void agregarLibro(Libro l) {
        listaLibros.add(l);
        System.out.println("Libro agregado correctamente.");
    }

    public static void listarLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (Libro l : listaLibros) {
            System.out.println(l);
        }
    }

    public static Libro buscarPorISBN(String isbn) {
        for (Libro l : listaLibros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) return l;
        }
        return null;
    }

    public static void eliminarLibro(String isbn) {
        Libro l = buscarPorISBN(isbn);
        if (l != null) {
            listaLibros.remove(l);
            System.out.println("Libro eliminado.");
        } else {
            System.out.println("No se encontró el libro.");
        }
    }

    public static void modificarLibro(String isbn, String nuevoTitulo) {
        Libro l = buscarPorISBN(isbn);
        if (l != null) {
            l.titulo = nuevoTitulo;
            System.out.println("Libro modificado.");
        } else {
            System.out.println("No se encontró el libro.");
        }
    }

    @Override
    public String toString() {
        return "[Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Disponible: " + disponible + "]";
    }
}