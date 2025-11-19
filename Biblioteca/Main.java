// Main.java
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static Stack<Operacion> pilaOperaciones = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n==== MENÚ PRINCIPAL ====");
            System.out.println("1. Gestión de Libros");
            System.out.println("2. Gestión de Usuarios");
            System.out.println("3. Sistema de Préstamos");
            System.out.println("4. Deshacer última operación");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: menuLibros(sc); break;
                case 2: menuUsuarios(sc); break;
                case 3: menuPrestamos(sc); break;
                case 4: deshacerOperacion(); break;
                case 5: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    // === Módulo Libros ===
    private static void menuLibros(Scanner sc) {
        System.out.println("1. Agregar libro");
        System.out.println("2. Listar libros");
        System.out.println("3. Modificar libro");
        System.out.println("4. Eliminar libro");
        int op = sc.nextInt(); sc.nextLine();

        switch (op) {
            case 1:
                System.out.print("Título: ");
                String t = sc.nextLine();
                System.out.print("Autor: ");
                String a = sc.nextLine();
                System.out.print("ISBN: ");
                String i = sc.nextLine();
                System.out.print("Categoría: ");
                String c = sc.nextLine();
                Libro l = new Libro(t, a, i, c);
                Libro.agregarLibro(l);
                pilaOperaciones.push(new Operacion(Operacion.TipoOperacion.AGREGAR_LIBRO, l, null));
                break;
            case 2: Libro.listarLibros(); break;
            case 3:
                System.out.print("ISBN del libro: ");
                String isbn = sc.nextLine();
                System.out.print("Nuevo título: ");
                String nuevoTitulo = sc.nextLine();
                Libro anterior = Libro.buscarPorISBN(isbn);
                if (anterior != null)
                    pilaOperaciones.push(new Operacion(Operacion.TipoOperacion.MODIFICAR_LIBRO, anterior, anterior.getTitulo()));
                Libro.modificarLibro(isbn, nuevoTitulo);
                break;
            case 4:
                System.out.print("ISBN a eliminar: ");
                String id = sc.nextLine();
                Libro elim = Libro.buscarPorISBN(id);
                if (elim != null)
                    pilaOperaciones.push(new Operacion(Operacion.TipoOperacion.ELIMINAR_LIBRO, elim, null));
                Libro.eliminarLibro(id);
                break;
        }
    }

    // === Módulo Usuarios ===
    private static void menuUsuarios(Scanner sc) {
        System.out.println("1. Agregar usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Eliminar usuario");
        int op = sc.nextInt(); sc.nextLine();

        switch (op) {
            case 1:
                System.out.print("Nombre: ");
                String n = sc.nextLine();
                System.out.print("ID: ");
                String id = sc.nextLine();
                Usuario u = new Usuario(n, id);
                Usuario.agregarUsuario(u);
                pilaOperaciones.push(new Operacion(Operacion.TipoOperacion.AGREGAR_USUARIO, u, null));
                break;
            case 2: Usuario.listarUsuarios(); break;
            case 3:
                System.out.print("ID del usuario: ");
                String uid = sc.nextLine();
                Usuario elim = Usuario.buscarUsuario(uid);
                if (elim != null)
                    pilaOperaciones.push(new Operacion(Operacion.TipoOperacion.ELIMINAR_USUARIO, elim, null));
                Usuario.eliminarUsuario(uid);
                break;
        }
    }

    // === Módulo Préstamos ===
    private static void menuPrestamos(Scanner sc) {
        System.out.println("1. Prestar libro");
        System.out.println("2. Devolver libro");
        int op = sc.nextInt(); sc.nextLine();

        System.out.print("ID de usuario: ");
        String uid = sc.nextLine();
        System.out.print("ISBN del libro: ");
        String isbn = sc.nextLine();

        Usuario u = Usuario.buscarUsuario(uid);
        Libro l = Libro.buscarPorISBN(isbn);

        if (u == null || l == null) {
            System.out.println("Usuario o libro no encontrado.");
            return;
        }

        switch (op) {
            case 1:
                SistemaPrestamos.prestarLibro(u, l);
                pilaOperaciones.push(new Operacion(Operacion.TipoOperacion.PRESTAR_LIBRO, l, u));
                break;
            case 2:
                SistemaPrestamos.devolverLibro(u, l);
                pilaOperaciones.push(new Operacion(Operacion.TipoOperacion.DEVOLVER_LIBRO, l, u));
                break;
        }
    }

    // === Deshacer ===
    private static void deshacerOperacion() {
        if (pilaOperaciones.isEmpty()) {
            System.out.println("No hay operaciones para deshacer.");
            return;
        }

        Operacion ultima = pilaOperaciones.pop();
        switch (ultima.getTipo()) {
            case AGREGAR_LIBRO:
                Libro l = (Libro) ultima.getObjetoAfectado();
                Libro.listaLibros.remove(l);
                System.out.println("Se deshizo el agregado del libro.");
                break;
            case ELIMINAR_LIBRO:
                Libro.listaLibros.add((Libro) ultima.getObjetoAfectado());
                System.out.println("Se deshizo la eliminación del libro.");
                break;
            case MODIFICAR_LIBRO:
                Libro mod = (Libro) ultima.getObjetoAfectado();
                mod.modificarLibro(mod.getIsbn(), (String) ultima.getEstadoAnterior());
                System.out.println("Se deshizo la modificación del libro.");
                break;
            case AGREGAR_USUARIO:
                Usuario.listaUsuarios.remove((Usuario) ultima.getObjetoAfectado());
                System.out.println("Se deshizo el agregado del usuario.");
                break;
            case ELIMINAR_USUARIO:
                Usuario.listaUsuarios.add((Usuario) ultima.getObjetoAfectado());
                System.out.println("Se deshizo la eliminación del usuario.");
                break;
            case PRESTAR_LIBRO:
                Libro l1 = (Libro) ultima.getObjetoAfectado();
                Usuario u1 = (Usuario) ultima.getEstadoAnterior();
                l1.setDisponible(true);
                u1.getHistorial().removeLast();
                System.out.println("Se deshizo el préstamo.");
                break;
            case DEVOLVER_LIBRO:
                Libro l2 = (Libro) ultima.getObjetoAfectado();
                Usuario u2 = (Usuario) ultima.getEstadoAnterior();
                l2.setDisponible(false);
                u2.getHistorial().removeLast();
                System.out.println("Se deshizo la devolución.");
                break;
        }
    }
}