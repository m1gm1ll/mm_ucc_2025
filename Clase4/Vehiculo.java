public class Vehiculo {

    // Atributos de la clase
    private String marca;
    private String color;
    private double cilindraje;
    private String chasis;
    private double peso;
    private String potencia;

    // Constructor de la clase -> permite inicializar el objeto
    // El constructor de la clase se reconoce por que tiene el mismo nombre de la
    // clase
    public Vehiculo(String marca, String color, double cilindraje, String chasis, double peso, String potencia) {
        this.marca = marca;
        this.potencia = potencia;
        this.color = color;
        this.cilindraje = cilindraje;
        this.chasis = chasis;
        this.peso = peso;
    }

    // Metodos getter / setter
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Permite mostrar el objeto
    public String toString() {
        return "Vehiculo { Marca:" + marca + " Color:" + color +
                " Cilindraje:" + cilindraje + " Chasis:" + chasis + " Peso:" + peso +
                " Potencia:" + potencia + "}";
    }

    // Metodos de la clase
    public void acelerar() {
        System.out.println("Vehiculo acelerando....");
    }

    public void frenar() {
        System.out.println("Vehiculo frenando....");
    }

    public void girarizq() {
        System.out.println("Vehiculo girando izq....");
    }

    public void girarder() {
        System.out.println("Vehiculo girando der....");
    }

    public void retroceder() {
        System.out.println("Vehiculo retrocediendo....");
    }

}
