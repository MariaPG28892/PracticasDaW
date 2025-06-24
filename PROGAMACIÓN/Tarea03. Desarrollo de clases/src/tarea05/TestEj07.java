
package tarea05;

/**
 * Programa de pruebas del <b>ejercicio 07</b>  de la clase <code>Velero</code>
 * @author profesorado
 */
public class TestEj07 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj07 ()
    {
        
    }    
    
    /**
     * Metodo main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main (String[] args)
    {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 07: MÉTODO toString()   ");
        System.out.println("--------------------------------------\n");
        
        // Se crea una barco barco de prueba
        Velero barco = TestUtilidades.crearBarco("Atlantis", 1, 2);
        
        System.out.println("-----------------------------------------------");
        System.out.println(" - PRUEBA PARA VISUALIZAR EL ESTADO DEL OBJETO ");
        System.out.println("-----------------------------------------------");
        
        // Se realizan algunas operaciones básicas para leer el estado del barco
        // El barco inicia la navegacion a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        barco.iniciarNavegacion(20, "ceñida", "Pepe Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco.getNombreBarco(), barco.getRumbo(), barco.getVelocidad()));
        
        // Se imprime el estado del barco
        System.out.println("Obteniendo los datos del barco...");
        System.out.println(barco.toString() + "\n");
        
        // Se modifican algunos parámetros de estado del objeto
        System.out.println("Cambiando rumbo...");
        barco.setRumbo("empopada");
        System.out.println(String.format(" -> El barco %s ha cambiado de rumbo a %s\n", barco.getNombreBarco(), barco.getRumbo()));
        
        // Se imprime el estado del barco
        System.out.println("Obteniendo los datos del barco...");
        System.out.println(barco.toString() + "\n");
        
        // Se registra la operación de parar la navegacion
        System.out.println("Deteniendo navegacion...");
        barco.pararNavegacion(60);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco.getNombreBarco()));

        // Se imprime el estado del barco
        System.out.println("Obteniendo los datos del barco...");
        System.out.println(barco.toString() + "\n");

        // Se realiza una nueva navegacion
        // El barco inicia la navegacion a 10 nudos con 1 tripulante
        System.out.println("Iniciando navegacion...");
        barco.iniciarNavegacion(10, "ceñida", "Pepe Martinez", 1);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco.getNombreBarco(), barco.getRumbo(), barco.getVelocidad()));
        
        // Se imprime el estado de la barco
        System.out.println("Obteniendo los datos del barco...");
        System.out.println(barco.toString() + "\n");
        
        // Se modifican algunos parámetros de estado del objeto
        System.out.println("Cambiando rumbo...");
        barco.setRumbo("empopada");
        System.out.println(String.format(" -> El barco %s ha cambiado de rumbo a %s\n", barco.getNombreBarco(), barco.getRumbo()));
        
        // Se imprime el estado del barco
        System.out.println("Obteniendo los datos del barco...");
        System.out.println(barco.toString() + "\n");
        
        // Se registra la operación de parar la navegacion
        System.out.println("Deteniendo navegacion...");
        barco.pararNavegacion(30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco.getNombreBarco()));
        
        // Se imprime el estado del barco
        System.out.println("Obteniendo los datos del barco...");
        System.out.println(barco.toString());        
    }
}