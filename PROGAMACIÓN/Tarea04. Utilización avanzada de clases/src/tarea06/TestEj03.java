package tarea06;

/**
 * Programa de pruebas del <b>ejercicio 03</b> de las clases <code>Embarcacion</code>, <code>Velero</code> y <code>Lancha</code>: 
 * Inicio y parada de la navegación de los barcos
 * 
 * @author profesorado
 */
public class TestEj03 {
     /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj03 ()
    {
        
    }    
    
    /**
     * Método main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main (String[] args)
    {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 03: INICIO Y PARADA DE NAVEGACIÓN");
        System.out.println("------------------------------------------------------------\n");
        
        // Se crea dos veleros y dos lanchas de prueba
        Velero velero1 = TestUtilidades.crearVelero("Atlantis", 2, 2);
        Velero velero2 = TestUtilidades.crearVelero("Tango", 1, 7);
        Velero velero3 = TestUtilidades.crearVelero("Arrebatos", 1, 4);
        Lancha lancha1 = TestUtilidades.crearLancha("Maquinon", 2, 40,2);
        Lancha lancha2 = TestUtilidades.crearLancha("Motoreta", 1, 27,1);
        
        // Se realizan pruebas correctas (probando la operativa de iniciar y parar la navegacion)
        System.out.println("\n--------------------------------------------------------------");
        System.out.println(" - USO DE LOS MÉTODOS DE ACCIÓN (iniciar y parar navegación)    ");
        System.out.println("--------------------------------------------------------------");
        
        // El barco inicia la navegacion a 20 nudos con 2 tripulantes
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(velero1, 20, "ceñida", "Jesús Martinez", 2);
        System.out.println(String.format(" -> El velero %s ha iniciado la navegacion en %s a %d nudos\n", velero1.getNombreBarco(), velero1.getRumbo(), velero1.getVelocidad()));
        
        TestUtilidades.pruebaMetodoIniciarNavegacionLancha(lancha1, 20, "norte", "Marcos Lozano", 2);
        System.out.println(String.format(" -> La lancha %s ha iniciado la navegacion en %s a %d nudos\n", lancha1.getNombreBarco(), lancha1.getRumbo(), lancha1.getVelocidad()));
        
        // Se registra el proceso de parar la navegacion indicando el tiempo navegado (en minutos)
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(velero1, 120);
        System.out.println(String.format(" -> El velero %s ha detenido la navegacion con exito\n", velero1.getNombreBarco()));
        
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(lancha1, 30);
        System.out.println(String.format(" -> La lancha %s ha detenido la navegacion con exito\n", lancha1.getNombreBarco()));

        // Se realiza una navegacion adicional
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(velero2, 15, "empopada", "Carmen Gil", 5);
        System.out.println(String.format(" -> El velero %s ha iniciado la navegacion en %s a %d nudos\n", velero2.getNombreBarco(), velero2.getRumbo(), velero2.getVelocidad()));

        TestUtilidades.pruebaMetodoIniciarNavegacionLancha(lancha2, 15, "sur", "Verónica Petre", 1);
        System.out.println(String.format(" -> La lancha %s ha iniciado la navegacion en %s a %d nudos\n", lancha2.getNombreBarco(), lancha2.getRumbo(), lancha2.getVelocidad()));
        
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(velero2, 60);
        System.out.println(String.format(" -> El velero %s ha detenido la navegacion con exito\n", velero2.getNombreBarco()));
        
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(lancha2, 25);
        System.out.println(String.format(" -> La lancha %s ha detenido la navegacion con exito\n", lancha2.getNombreBarco()));
  
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(velero1, 30, "ceñida", "Clara Llamas", 1);
        System.out.println(String.format(" -> El velero %s ha iniciado la navegacion en %s a %d nudos\n", velero1.getNombreBarco(), velero1.getRumbo(), velero1.getVelocidad()));
        
        TestUtilidades.pruebaMetodoIniciarNavegacionLancha(lancha1, 30, "este", "Tomás Navarro", 1);
        System.out.println(String.format(" -> La lancha %s ha iniciado la navegacion en %s a %d nudos\n", lancha1.getNombreBarco(), lancha1.getRumbo(), lancha1.getVelocidad()));
                
        System.out.println("---------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LOS BARCOS (durante la navegación)" );
        System.out.println("---------------------------------------------------------------");
        
        // Se consultan los datos registrados en la barco para comprobar si son correctos
        System.out.println(TestUtilidades.consultarAtributosVelero(velero1));
        System.out.println(TestUtilidades.consultarAtributosVelero(velero2));
        System.out.println(TestUtilidades.consultarAtributosVelero(velero3));
        
        System.out.println(TestUtilidades.consultarAtributosLancha(lancha1));
        System.out.println(TestUtilidades.consultarAtributosLancha(lancha2));
        
        // Se consultan los atributos de clase durante las navegaciones
        System.out.println("--------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE CLASE (durante la navegación)");
        System.out.println("--------------------------------------------------------------");
        
        System.out.println("Atributos de clase accediendo desde la clase Velero...");
        System.out.println(TestUtilidades.consultaAtributosEstaticosVelero());
        
        System.out.println("Atributos de clase accediendo desde la clase Lancha...");
        System.out.println(TestUtilidades.consultaAtributosEstaticosLancha());
        
        // Paramos la navegacion
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(velero1, 120);
        System.out.println(String.format(" -> El velero %s ha detenido la navegacion con exito\n", velero1.getNombreBarco()));
        
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(lancha1, 20);
        System.out.println(String.format(" -> La lancha %s ha detenido la navegacion con exito\n", lancha1.getNombreBarco()));
        
        System.out.println("--------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LOS BARCOS (tras la navegación)" );
        System.out.println("--------------------------------------------------------------");
        
        // Se consultan los datos registrados en el barco para comprobar si son correctos
        System.out.println(TestUtilidades.consultarAtributosVelero(velero1));
        System.out.println(TestUtilidades.consultarAtributosVelero(velero2));
        System.out.println(TestUtilidades.consultarAtributosVelero(velero3));
        
        System.out.println(TestUtilidades.consultarAtributosLancha(lancha1));
        System.out.println(TestUtilidades.consultarAtributosLancha(lancha2));
        
        // Se consultan los atributos de clase
        System.out.println("--------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE CLASE (tras la navegación)");
        System.out.println("--------------------------------------------------------------");
        
        System.out.println("Atributos de clase accediendo desde la clase Velero...");
        System.out.println(TestUtilidades.consultaAtributosEstaticosVelero());
        
        System.out.println("Atributos de clase accediendo desde la clase Lancha...");
        System.out.println(TestUtilidades.consultaAtributosEstaticosLancha());
        
        // Se realizan pruebas que generan excepciones (operaciones no permitidas o llenado de registros)
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(" - PRUEBAS DE LOS MÉTODOS DE ACCIÓN QUE GENERAN EXCEPCIONES (operaciones no permitidas) ");
        System.out.println("----------------------------------------------------------------------------------------");
        
        // Prueba 04.1 Si no inicia la navegacion el barco, no puede pararla
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(velero1, 90);              // - Excepcion IllegalStateException
        
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(lancha1, 35);              // - Excepcion IllegalStateException
        
        // Ahora, se hace que inicie la navegacion el barco para hacer el resto de pruebas.
        System.out.println("Iniciando navegacion...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(velero1, 30, "ceñida", "Jesús Martinez", 2);
        System.out.println(String.format(" -> El velero %s ha iniciado la navegacion en %s a %d nudos\n", velero1.getNombreBarco(), velero1.getRumbo(), velero1.getVelocidad()));
        
        TestUtilidades.pruebaMetodoIniciarNavegacionLancha(lancha1, 30, "oeste", "Marcos Lozano", 2);
        System.out.println(String.format(" -> La lancha %s ha iniciado la navegacion en %s a %d nudos\n", lancha1.getNombreBarco(), lancha1.getRumbo(), lancha1.getVelocidad()));

        // Prueba 04.2 Si ya está navegando, no puede volver a iniciar la navegacion
        System.out.println("Iniciando navegacion de nuevo...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(velero1, 30, "empopada", "Jesús Martinez", 2); // - Excepcion IllegalStateException
        
        TestUtilidades.pruebaMetodoIniciarNavegacionLancha(lancha1, 30, "norte", "Marcos Lozano", 2); // - Excepcion IllegalStateException
        
        // Prueba 04.3 La velocidad de navegacion es incorrecta
        System.out.println("Iniciando navegacion a 60 nudos ...");
        TestUtilidades.pruebaMetodoIniciarNavegacionVelero(velero1, 60, "empopada", "Jesús Martinez", 2); // - Excepcion IllegalArgumentException
        
        TestUtilidades.pruebaMetodoIniciarNavegacionLancha(lancha1, 60, "norte", "Marcos Lozano", 2); // - Excepcion IllegalArgumentException
        
        // Se finalizan las pruebas con errores haciendo que el barco pare la navegacion correctamente
        System.out.println("Deteniendo navegacion...");
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(velero1, 30);
        System.out.println(String.format(" -> El velero %s ha detenido la navegacion con exito\n", velero1.getNombreBarco()));
        
        TestUtilidades.pruebaMetodoPararNavegacionEmbarcacion(lancha1, 30);
        System.out.println(String.format(" -> La lancha %s ha detenido la navegacion con exito\n", lancha1.getNombreBarco()));
        
        System.out.println("\n---------------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LOS BARCOS (después de todas las pruebas)" );
        System.out.println("---------------------------------------------------------------------");
        
        // Se consultan los datos registrados en la barco para comprobar si son correctos
        System.out.println(TestUtilidades.consultarAtributosVelero(velero1));
        System.out.println(TestUtilidades.consultarAtributosVelero(velero2));
        System.out.println(TestUtilidades.consultarAtributosVelero(velero3));
        
        System.out.println(TestUtilidades.consultarAtributosLancha(lancha1));
        System.out.println(TestUtilidades.consultarAtributosLancha(lancha2));
        
        // Se consultan los atributos de clase
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS DE LA CLASE (después de todas las pruebas)");
        System.out.println("-------------------------------------------------------------------");
        System.out.println(TestUtilidades.consultaAtributosEstaticosVelero());
        
        System.out.println(TestUtilidades.consultaAtributosEstaticosLancha());
    }
}
