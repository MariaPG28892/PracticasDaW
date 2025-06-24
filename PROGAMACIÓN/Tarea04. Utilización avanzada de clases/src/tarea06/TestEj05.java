package tarea06;

/**
 * Programa de pruebas del <b>ejercicio 05</b> de las clases <code>Embarcacion</code>, <code>Velero</code> y <code>Lancha</code>: 
 * Realizar regatas entre veleros
 * Ligadura dinámica
 * 
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
        System.out.println("CASO DE PRUEBAS 05: REGATAS");
        System.out.println("----------------------------------------------------\n");
        
        // Se crean tres barcos de prueba
        Velero barco1 = TestUtilidades.crearVelero("Atlantis", 2 , 4);
        Velero barco2 = TestUtilidades.crearVelero("Arrebatos", 2, 4);
        Velero barco3 = TestUtilidades.crearVelero("Tango", 1, 5);

        
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA REALIZAR REGATAS (operaciones permitidas)");
        System.out.println("-------------------------------------------------------------------");
        
        // El barco1 inicia la navegacion en ceñida a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco1, 20, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        // El barco2 inicia la navegacion en ceñida a 25 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco2, 25, "ceñida", "Paqui Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Regata de barco1 con barco2: llega primero barco2
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(barco2, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco2.getNombreBarco()));
        
        // El barco1 inicia la navegacion en empopada a 28 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco1, 28, "empopada", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        // El barco2 inicia la navegacion en empopada a 5 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco2, 5, "empopada", "Paqui Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Regata de barco1 con barco2: llega primero barco2
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(barco2, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco2.getNombreBarco()));

        // El barco1 inicia la navegacion en ceñida a 10 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco1, 10, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        // El barco2 inicia la navegacion en ceñida a 10 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco2, 10, "ceñida", "Paqui Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Regata de barco1 con barco2: ambos barcos llegan a la vez
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(barco2, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco2.getNombreBarco()));
        
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA REALIZAR REGATAS (operaciones no permitidas)");
        System.out.println("----------------------------------------------------------------------");
        
        // El barco1 inicia la navegacion en ceñida a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco1, 20, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));

        // Si se intenta regatear con un barco nulo, no se puede
        System.out.println(String.format("Iniciando una regata entre %s y null...", barco1.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, null);
        
        // Si se intenta regatear con barco1 y barco2, no se puede ya que barco2 aun no ha iniciado la navegacion
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        // El barco2 inicia la navegacion en empopada a 25 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco2, 25, "empopada", "Paqui Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Si se intenta regatear con barco1 y barco2, no se puede ya que no siguen los mismos rumbos
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);

        // El barco3 inicia la navegacion en ceñida a 15 nudos con 3 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(barco3,15, "ceñida", "Raul Fernández", 3);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco3.getNombreBarco(), barco3.getRumbo(), barco3.getVelocidad()));
         
        // Si se intenta regatear con barco1 y barco3, no se puede ya que no tienen el mismo numero de mastiles
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco3.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco3);
        
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO ABSTRACTO PARA PROBAR LA LIGADURA DINÁMICA         ");
        System.out.println("----------------------------------------------------------------------");
        
        Embarcacion elCano = TestUtilidades.crearVelero("El Cano", 3, 30);
        Embarcacion bluebirdK7 = TestUtilidades.crearLancha("Bluebird K7", 1, 50, 1);
        
        System.out.println("Señalizando...");
        elCano.señalizar(); // El velero El Cano señala con banderas de señalización marítima.
        System.out.println("\nSeñalizando...");
        bluebirdK7.señalizar(); // La lancha Bluebird K7 señala con bocinas o luces intermitentes.

    }
}