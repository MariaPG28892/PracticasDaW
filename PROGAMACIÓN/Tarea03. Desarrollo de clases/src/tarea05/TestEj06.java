package tarea05;

/**
 * Programa de pruebas del <b>ejercicio 06</b> de la clase <code>Velero</code>
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
        System.out.println("CASO DE PRUEBAS 06: REGATAS");
        System.out.println("----------------------------------------------------\n");
        
        // Se crean tres barcos de prueba
        Velero barco1 = TestUtilidades.crearBarco("Atlantis", 2 , 4);
        Velero barco2 = TestUtilidades.crearBarco("Arrebatos", 2, 4);
        Velero barco3 = TestUtilidades.crearBarco("Tango", 3, 5);
        
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 20, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA REALIZAR REGATAS (operaciones permitidas)");
        System.out.println("-------------------------------------------------------------------");
        
        // El barco1 inicia la navegacion en ceñida a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 20, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        // El barco2 inicia la navegacion en ceñida a 25 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco2, 25, "ceñida", "Raul Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Regata de barco1 con barco2: llega primero barco1
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco2, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco2.getNombreBarco()));
        
        // El barco1 inicia la navegacion en empopada a 28 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 28, "empopada", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        // El barco2 inicia la navegacion en empopada a 5 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco2, 5, "empopada", "Raul Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Regata de barco1 con barco2: llega primero barco2
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco2, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco2.getNombreBarco()));

        // El barco1 inicia la navegacion en ceñida a 10 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 10, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        // El barco2 inicia la navegacion en ceñida a 10 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco2, 10, "ceñida", "Raul Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Regata de barco1 con barco2: ambos barcos llegan a la vez
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco2, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco2.getNombreBarco()));
        
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO PARA REALIZAR REGATAS (operaciones no permitidas)");
        System.out.println("----------------------------------------------------------------------");
        
        // El barco1 inicia la navegacion en ceñida a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 20, "ceñida", "Pepe Gonzalez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));

        // Si se intenta regatear con un barco nulo, no se puede
        System.out.println(String.format("Iniciando una regata entre %s y null...", barco1.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, null);
        
        // Si se intenta regatear con barco1 y barco2, no se puede ya que barco2 aun no ha iniciado la navegacion
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);
        
        // El barco2 inicia la navegacion en empopada a 25 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco2, 25, "empopada", "Raul Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        // Si se intenta regatear con barco1 y barco2, no se puede ya que no siguen los mismos rumbos
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco2.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco2);

        // El barco3 inicia la navegacion en ceñida a 15 nudos con 3 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco3,15, "ceñida", "Raul Martinez", 3);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco3.getNombreBarco(), barco3.getRumbo(), barco3.getVelocidad()));
         
        // Si se intenta regatear con barco1 y barco3, no se puede ya que no tienen el mismo numero de mastiles
        System.out.println(String.format("Iniciando una regata entre %s y %s...", barco1.getNombreBarco(), barco3.getNombreBarco()));
        TestUtilidades.pruebaMetodoIniciarRegata(barco1, barco3);
    }
}