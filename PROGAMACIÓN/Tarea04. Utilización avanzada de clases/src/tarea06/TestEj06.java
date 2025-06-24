
package tarea06;

/**
 * Programa de pruebas del <b>ejercicio 06</b> de las clases <code>Embarcacion</code>, <code>Velero</code> y <code>Lancha</code>:
 * Método toString
 * 
 * @author profesorado
 */
public class TestEj06 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj06 ()
    {
        
    }    
    
    /**
     * Metodo main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main (String[] args)
    {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 06: MÉTODO toString()   ");
        System.out.println("--------------------------------------\n");
        
        // Se crea un velero y una lancha de prueba
        Velero velero1 = TestUtilidades.crearVelero("Atlantis", 1, 2);
        Lancha lancha1 = TestUtilidades.crearLancha("Rapidisima", 1, 50,2);
        
        System.out.println("-----------------------------------------------");
        System.out.println(" - PRUEBA PARA VISUALIZAR EL ESTADO DEL OBJETO ");
        System.out.println("-----------------------------------------------");
        
        // Se realizan algunas operaciones básicas para leer el estado del velero
        // El velero inicia la navegacion a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        velero1.iniciarNavegacion(20, "ceñida", "Pepe Martinez", 2);
        System.out.println(String.format(" -> El velero %s ha iniciado la navegacion en %s a %d nudos\n", velero1.getNombreBarco(), velero1.getRumbo(), velero1.getVelocidad()));
        // La lancha inicia la navegacion a 30 nudos con 1 tripulante
        lancha1.iniciarNavegacion(30, "norte", "Juan Lopez", 1);
        System.out.println(String.format(" -> La lancha %s ha iniciado la navegacion en %s a %d nudos\n", lancha1.getNombreBarco(), lancha1.getRumbo(), lancha1.getVelocidad()));
        
        // Se imprime el estado del velero y la lancha
        System.out.println("Obteniendo los datos del velero...");
        System.out.println(velero1.toString() + "\n");
        
        System.out.println("Obteniendo los datos de la lancha...");
        System.out.println(lancha1.toString() + "\n");
        
        // Se modifican algunos parámetros de estado del objeto
        System.out.println("Cambiando rumbo...");
        velero1.setRumbo("empopada");
        System.out.println(String.format(" -> El velero %s ha cambiado de rumbo a %s\n", velero1.getNombreBarco(), velero1.getRumbo()));
        
        lancha1.setRumbo("este");
        System.out.println(String.format(" -> La lancha %s ha cambiado de rumbo a %s\n", lancha1.getNombreBarco(), lancha1.getRumbo()));
        
        // Se imprime el estado del velero y la lancha
        System.out.println("Obteniendo los datos del velero...");
        System.out.println(velero1.toString() + "\n");
        
        System.out.println("Obteniendo los datos de la lancha...");
        System.out.println(lancha1.toString() + "\n");
        
        // Se registra la operación de parar la navegacion
        System.out.println("Deteniendo navegacion...");
        velero1.pararNavegacion(60);
        System.out.println(String.format(" -> El velero %s ha detenido la navegacion con exito\n", velero1.getNombreBarco()));
        
        lancha1.pararNavegacion(25);
        System.out.println(String.format(" -> La lancha %s ha detenido la navegacion con exito\n", lancha1.getNombreBarco()));

        // Se imprime el estado del velero y la lancha
        System.out.println("Obteniendo los datos del velero...");
        System.out.println(velero1.toString() + "\n");

        System.out.println("Obteniendo los datos de la lancha...");
        System.out.println(lancha1.toString() + "\n");
        
        // Se realiza una nueva navegacion
        // El velero inicia la navegacion a 10 nudos con 1 tripulante
        // La lancha inicia la navegacion a 25 nudos con 2 tripulante2
        System.out.println("Iniciando navegacion...");
        velero1.iniciarNavegacion(10, "ceñida", "Pepe Martinez", 1);
        System.out.println(String.format(" -> El velero %s ha iniciado la navegacion en %s a %d nudos\n", velero1.getNombreBarco(), velero1.getRumbo(), velero1.getVelocidad()));
        
        lancha1.iniciarNavegacion(25, "sur", "Juan Lopez", 2);
        System.out.println(String.format(" -> La lancha %s ha iniciado la navegacion en %s a %d nudos\n", lancha1.getNombreBarco(), lancha1.getRumbo(), lancha1.getVelocidad()));
        
        // Se imprime el estado del velero y la lancha
        System.out.println("Obteniendo los datos del velero1...");
        System.out.println(velero1.toString() + "\n");
        
        System.out.println("Obteniendo los datos de la lancha...");
        System.out.println(lancha1.toString() + "\n");
        
        // Se modifican algunos parámetros de estado del objeto
        System.out.println("Cambiando rumbo...");
        velero1.setRumbo("empopada");
        System.out.println(String.format(" -> El velero %s ha cambiado de rumbo a %s\n", velero1.getNombreBarco(), velero1.getRumbo()));
        
        lancha1.setRumbo("oeste");
        System.out.println(String.format(" -> La lancha %s ha cambiado de rumbo a %s\n", lancha1.getNombreBarco(), lancha1.getRumbo()));
        
        // Se imprime el estado del velero y la lancha
        System.out.println("Obteniendo los datos del velero...");
        System.out.println(velero1.toString() + "\n");
        
        System.out.println("Obteniendo los datos de la lancha...");
        System.out.println(lancha1.toString() + "\n");
        
        // Se registra la operación de parar la navegacion
        System.out.println("Deteniendo navegacion...");
        velero1.pararNavegacion(30);
        System.out.println(String.format(" -> El velero %s ha detenido la navegacion con exito\n", velero1.getNombreBarco()));
        
        lancha1.pararNavegacion(20);
        System.out.println(String.format(" -> La lancha %s ha detenido la navegacion con exito\n", lancha1.getNombreBarco()));
        
        // Se imprime el estado del velero y la lancha
        System.out.println("Obteniendo los datos del velero...");
        System.out.println(velero1.toString());
        
        System.out.println("Obteniendo los datos de la lancha...");
        System.out.println(lancha1.toString() + "\n");
    }
}