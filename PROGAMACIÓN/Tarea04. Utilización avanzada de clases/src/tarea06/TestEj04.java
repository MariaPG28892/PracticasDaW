package tarea06;

/**
 * Programa de pruebas del <b>ejercicio 04</b> de las clases <code>Embarcacion</code>, <code>Velero</code> y <code>Lancha</code>: 
 * Trabajo con los rumbos de los barcos
 * 
 * @author profesorado
 */
public class TestEj04 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj04 ()
    {
        
    }    
    
    /**
     * Metodo main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main (String[] args)
    {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 04: ESTABLECER RUMBOS");
        System.out.println("----------------------------------------------------\n");
        
        // Se crean tres barcos de prueba
        Velero velero1 = TestUtilidades.crearVelero("Atlantis", 2 , 4);
        Lancha lancha1 = TestUtilidades.crearLancha("Rapidisima", 2 , 15,2);
        
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(velero1, 20, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El velero %s ha iniciado la navegacion en %s a %d nudos\n", velero1.getNombreBarco(), velero1.getRumbo(), velero1.getVelocidad()));
        
        TestUtilidades.pruebaMetodoIniciarNavegacionLancha(lancha1, 20, "norte", "Paula Rodríguez", 2);
        System.out.println(String.format(" -> La lancha %s ha iniciado la navegacion en %s a %d nudos\n", lancha1.getNombreBarco(), lancha1.getRumbo(), lancha1.getVelocidad()));
        
        System.out.println("\n------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA ESTABLECER RUMBOS (con datos correctos)");
        System.out.println("------------------------------------------------------------------");        

        // El velero1 cambia el rumbo correctamente a empopada
        System.out.println("Cambiando rumbo a empopada...");
        TestUtilidades.pruebaMetodoSetRumboVelero(velero1, "empopada");
        System.out.println(String.format(" -> El velero %s ha cambiado de rumbo a %s\n", velero1.getNombreBarco(), velero1.getRumbo()));
        
        // La lancha1 cambia el rumbo correctamente a sur
        System.out.println("Cambiando rumbo a sur...");
        TestUtilidades.pruebaMetodoSetRumboLancha(lancha1, "sur");
        System.out.println(String.format(" -> La lancha %s ha cambiado de rumbo a %s\n", lancha1.getNombreBarco(), lancha1.getRumbo()));
        
        // El velero1 cambia el rumbo correctamente a ceñida
        System.out.println("Cambiando rumbo a ceñida...");
        TestUtilidades.pruebaMetodoSetRumboVelero(velero1, "ceñida");
        System.out.println(String.format(" -> El velero %s ha cambiado de rumbo a %s\n", velero1.getNombreBarco(), velero1.getRumbo()));
        
        // La lancha1 cambia el rumbo correctamente a este
        System.out.println("Cambiando rumbo a este...");
        TestUtilidades.pruebaMetodoSetRumboLancha(lancha1, "este");
        System.out.println(String.format(" -> La lancha %s ha cambiado de rumbo a %s\n", lancha1.getNombreBarco(), lancha1.getRumbo()));
        
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA ESTABLECER RUMBOS (con datos no válidos)");
        System.out.println("-------------------------------------------------------------------"); 
        
        // Pruebas que deben lanzar excepciones
        // Prueba incorrecta (se intenta establecer el mismo rumbo)
        System.out.println("Cambiando rumbo a ceñida...");
        TestUtilidades.pruebaMetodoSetRumboVelero(velero1, "ceñida");
        
        System.out.println("Cambiando rumbo a este...");
        TestUtilidades.pruebaMetodoSetRumboLancha(lancha1, "este");
        
        // Pruebas incorrectas (se intenta establecer un rumbo nulo, vacio o incorrecto)
        System.out.println("Cambiando rumbo a null...");
        TestUtilidades.pruebaMetodoSetRumboVelero(velero1, null);
        TestUtilidades.pruebaMetodoSetRumboLancha(lancha1, null);
        
        System.out.println("Cambiando rumbo a ...");
        TestUtilidades.pruebaMetodoSetRumboVelero(velero1, "");
        TestUtilidades.pruebaMetodoSetRumboLancha(lancha1, "");
        
        System.out.println("Cambiando rumbo a incorrecto...");
        TestUtilidades.pruebaMetodoSetRumboVelero(velero1, "incorrecto");
        TestUtilidades.pruebaMetodoSetRumboLancha(lancha1, "incorrecto");
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(velero1, 30);
        System.out.println(String.format(" -> El velero %s ha detenido la navegacion con exito\n", velero1.getNombreBarco()));
        
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(lancha1, 30);
        System.out.println(String.format(" -> La lancha %s ha detenido la navegacion con exito\n", lancha1.getNombreBarco()));
        
        // Prueba incorrecta (se intenta establecer un rumbo cuando el barco no esta navegando)
        System.out.println("Cambiando rumbo a ceñida...");
        TestUtilidades.pruebaMetodoSetRumboVelero(velero1, "ceñida");
        
        System.out.println("Cambiando rumbo a oeste...");
        TestUtilidades.pruebaMetodoSetRumboLancha(lancha1, "oeste");
    }
}