package tarea05;

/**
 * Programa de pruebas del <b>ejercicio 05</b> de la clase <code>Velero</code>
 * @author profesorado
 */
public class TestEj05 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj05 ()
    {
        
    }    
    
    /**
     * Metodo main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main (String[] args)
    {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 05: ESTABLECER RUMBOS");
        System.out.println("----------------------------------------------------\n");
        
        // Se crean tres barcos de prueba
        Velero barco1 = TestUtilidades.crearBarco("Atlantis", 2 , 4);
        
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 20, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        System.out.println("\n------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA ESTABLECER RUMBOS (con datos correctos)");
        System.out.println("------------------------------------------------------------------");        

        // El barco1 cambia el rumbo correctamente a empopada
        System.out.println("Cambiando rumbo a empopada...");
        TestUtilidades.pruebaMetodoSetRumbo(barco1, "empopada");
        System.out.println(String.format(" -> El barco %s ha cambiado de rumbo a %s\n", barco1.getNombreBarco(), barco1.getRumbo()));
        
        // El barco1 cambia el rumbo correctamente a ceñida
        System.out.println("Cambiando rumbo a ceñida...");
        TestUtilidades.pruebaMetodoSetRumbo(barco1, "ceñida");
        System.out.println(String.format(" -> El barco %s ha cambiado de rumbo a %s\n", barco1.getNombreBarco(), barco1.getRumbo()));
        
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA ESTABLECER RUMBOS (con datos no válidos)");
        System.out.println("-------------------------------------------------------------------"); 
        
        // Pruebas que deben lanzar excepciones
        // Prueba incorrecta (se intenta establecer el mismo rumbo)
        System.out.println("Cambiando rumbo a ceñida...");
        TestUtilidades.pruebaMetodoSetRumbo(barco1, "ceñida");
        
        // Pruebas incorrectas (se intenta establecer un rumbo nulo, vacio o incorrecto)
        System.out.println("Cambiando rumbo a null...");
        TestUtilidades.pruebaMetodoSetRumbo(barco1, null);
        
        System.out.println("Cambiando rumbo a ...");
        TestUtilidades.pruebaMetodoSetRumbo(barco1, "");
        
        System.out.println("Cambiando rumbo a incorrecto...");
        TestUtilidades.pruebaMetodoSetRumbo(barco1, "incorrecto");
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        // Prueba incorrecta (se intenta establecer un rumbo cuando el barco no esta navegando)
        System.out.println("Cambiando rumbo a ceñida...");
        TestUtilidades.pruebaMetodoSetRumbo(barco1, "ceñida");
    }
}