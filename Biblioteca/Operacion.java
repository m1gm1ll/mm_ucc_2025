// Operacion.java
// Clase para manejar operaciones y permitir deshacer

public class Operacion {
    public enum TipoOperacion {
        AGREGAR_LIBRO, MODIFICAR_LIBRO, ELIMINAR_LIBRO,
        AGREGAR_USUARIO, MODIFICAR_USUARIO, ELIMINAR_USUARIO,
        PRESTAR_LIBRO, DEVOLVER_LIBRO
    }

    private TipoOperacion tipo;
    private Object objetoAfectado;
    private Object estadoAnterior;

    public Operacion(TipoOperacion tipo, Object objetoAfectado, Object estadoAnterior) {
        this.tipo = tipo;
        this.objetoAfectado = objetoAfectado;
        this.estadoAnterior = estadoAnterior;
    }

    public TipoOperacion getTipo() {
        return tipo;
    }

    public Object getObjetoAfectado() {
        return objetoAfectado;
    }

    public Object getEstadoAnterior() {
        return estadoAnterior;
    }
}