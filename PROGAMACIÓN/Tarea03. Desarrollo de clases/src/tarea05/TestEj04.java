package tarea05;

/**
 * Programa de pruebas de los <b>ejercicio 04</b>  de la clase <code>Velero</code>
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
     * Método main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main (String[] args)
    {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 04: INICIO Y PARADA DE NAVEGACIÓN");
        System.out.println("------------------------------------------------------------\n");
        
        // Se crea una barco barco de prueba
        Velero barco1 = TestUtilidades.crearBarco("Atlantis", 1, 2);
        Velero barco2 = TestUtilidades.crearBarco("Tango", 1, 7);
       
        
        // Se realizan pruebas correctas (probando la operativa de iniciar y parar la navegacion)
        System.out.println("\n--------------------------------------------------------------");
        System.out.println(" - USO DE LOS MÉTODOS DE ACCIÓN (iniciar y parar navegación)    ");
        System.out.println("--------------------------------------------------------------");
        
        // El barco inicia la navegacion a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 20, "ceñida", "Pepe Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
        
        // Se registra el proceso de parar la navegacion indicando el tiempo navegado (en minutos)
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 120);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));

        // Se realizan una navegacion adicional
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco2, 15, "empopada", "Jose Jiménez", 5);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco2.getNombreBarco(), barco2.getRumbo(), barco2.getVelocidad()));

        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco2, 60);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco2.getNombreBarco()));
  
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 30, "ceñida", "María Navarro", 1);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));
                
        System.out.println("---------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LOS BARCOS (durante la navegación)" );
        System.out.println("---------------------------------------------------------------");
        
        // Se consultan los datos registrados en la barco para comprobar si son correctos
        System.out.println(TestUtilidades.consultarAtributosVelero(barco1));
        System.out.println(TestUtilidades.consultarAtributosVelero(barco2));
        
        // Se consultan los atributos de clase durante las navegaciones
        System.out.println("--------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE CLASE (durante la navegación)");
        System.out.println("--------------------------------------------------------------");
        
        System.out.println(TestUtilidades.consultaAtributosEstaticos());
        
        // Paramos la navegacion
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 120);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("--------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LOS BARCOS (tras la navegación)" );
        System.out.println("--------------------------------------------------------------");
        
        // Se consultan los datos registrados en la barco para comprobar si son correctos
        System.out.println(TestUtilidades.consultarAtributosVelero(barco1));
        System.out.println(TestUtilidades.consultarAtributosVelero(barco2));
        
        // Se consultan los atributos de clase
        System.out.println("--------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE CLASE (tras la navegación)");
        System.out.println("--------------------------------------------------------------");
        System.out.println(TestUtilidades.consultaAtributosEstaticos());
        
        // Se realizan pruebas que generan excepciones (operaciones no permitidas o llenado de registros)
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(" - PRUEBAS DE LOS MÉTODOS DE ACCIÓN QUE GENERAN EXCEPCIONES (operaciones no permitidas) ");
        System.out.println("----------------------------------------------------------------------------------------");
        
        // Prueba 04.1 Si no inicia la navegacion el barco, no puede pararla
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 90);              // - Excepcion IllegalStateException
        
        // Ahora, se hace que inicie la navegacion el barco para hacer el resto de pruebas.
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 30, "ceñida", "Pepe Martinez", 2);
        System.out.println(String.format(" -> El barco %s ha iniciado la navegacion en %s a %d nudos\n", barco1.getNombreBarco(), barco1.getRumbo(), barco1.getVelocidad()));

        // Prueba 04.2 Si ya está navegando, no puede volver a iniciar la navegacion
        System.out.println("Iniciando navegacion de nuevo...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 30, "empopada", "Pepe Martinez", 2); // - Excepcion IllegalStateException
        
        // Prueba 04.3 La velocidad de navegacion es incorrecta
        System.out.println("Iniciando navegacion a 60 nudos ...");
        TestUtilidades.pruebaMetodoIniciarNavegacion(barco1, 60, "empopada", "Pepe Martinez", 2); // - Excepcion IllegalArgumentException
        
        // Se finalizan las pruebas con errores haciendo que el barco pare la navegacion correctamente
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacion(barco1, 30);
        System.out.println(String.format(" -> El barco %s ha detenido la navegacion con exito\n", barco1.getNombreBarco()));
        
        System.out.println("\n---------------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LOS BARCOS (después de todas las pruebas)" );
        System.out.println("---------------------------------------------------------------------");
        
        // Se consultan los datos registrados en la barco para comprobar si son correctos
        System.out.println(TestUtilidades.consultarAtributosVelero(barco1));
        System.out.println(TestUtilidades.consultarAtributosVelero(barco2));
        
        // Se consultan los atributos de clase
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LA CLASE (después de todas las pruebas)");
        System.out.println("-------------------------------------------------------------------");
        System.out.println(TestUtilidades.consultaAtributosEstaticos());
    }
}
