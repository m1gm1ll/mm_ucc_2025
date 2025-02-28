public class Ejecutar {
    public static void main(String[] args) {

        //Creacion de los objetos de la clase Vehiculo

        //Forma1
        Vehiculo objVehiculo = new Vehiculo("Toyota","Blanco", 4500.0, "TS3456WE", 
        2.3, "150HP");
        //Forma2
        Vehiculo objVehiculo2;
        objVehiculo2 = new Vehiculo("Ferrari", "Rojo", 5000.0, "SEWR567UY", 
        1.8, "450HP");

        //Mostrar marca Vehiculo "objVehiculo"
        System.out.println(objVehiculo.getMarca());

        //Modificar marca Vehiculo del "objVehiculo"
        objVehiculo.setMarca("KIA");
        
        //Mostrar todo objeto
        System.out.println(objVehiculo.toString());
        System.out.println(objVehiculo2);




    }



}