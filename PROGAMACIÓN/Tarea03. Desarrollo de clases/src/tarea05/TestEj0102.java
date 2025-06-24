package tarea05;

/**
 * Programa de pruebas de los <b>ejercicios 01</b> y <b>02</b> para la clase <code>Velero</code>
 * @author profesorado
 */
public class TestEj0102 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj0102() {

    }

    /**
     * Método main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main(String[] args) {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 01-02: CONSTRUCTORES Y ATRIBUTOS");
        System.out.println("------------------------------------------------\n");
        
        
        System.out.println("------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS PÚBLICOS");
        System.out.println("-------------------------------------");

        // Se prueban los atributos públicos de la clase
        System.out.println(TestUtilidades.consultaAtributosPublicos());

        // Se prueban los constructores y el método fábrica
        // Pruebas correctas (se crean varios objetos usando los distintos constructores y el método de fábrica)
        System.out.println("------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL CONSTRUCTOR DE TRES PARÁMETROS (con datos correctos)");
        System.out.println("------------------------------------------------------------------");

        Velero barco1 = TestUtilidades.crearBarco("Atlantis", 2, 4);
        Velero barco2 = TestUtilidades.crearBarco("Arrebatos", 3, 5);
        
        System.out.println("-----------------------------------------");
        System.out.println(" - PRUEBA DEL CONSTRUCTOR SIN PARÁMETROS");
        System.out.println("-----------------------------------------");        
        
        Velero barco3 = TestUtilidades.crearBarco();
        
        System.out.println("--------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO FÁBRICA (con datos correctos)");
        System.out.println("--------------------------------------------------");             
        
        Velero[] arrayBarcos1 = TestUtilidades.crearArrayBarcos(5);
        Velero[] arrayBarcos2 = TestUtilidades.crearArrayBarcos(1);  // Se prueban también los valores límite
        Velero[] arrayBarcos3 = TestUtilidades.crearArrayBarcos(10); // Se prueba también los valores límite
        System.out.println();

        // Pruebas que deben lanzar excepciones
        // Pruebas incorrectas (se crean varios objetos usando los distintos constructores y el método de fábrica)
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL CONSTRUCTOR DE TRES PARÁMETROS (con datos no válidos)");
        System.out.println("-------------------------------------------------------------------");

        // Se instancian varias aeronaves utilizando valores nulos en el constructor o la cadena vacía
        Velero barco4 = TestUtilidades.crearBarco(null, 1, 2);
        Velero barco5 = TestUtilidades.crearBarco("", 2,4);
        
        System.out.println("---------------------------------------------------");
        System.out.println(" - PRUEBA DEL MÉTODO FÁBRICA (con datos no válidos)");
        System.out.println("---------------------------------------------------");
        // Se utiliza el método fábrica especificando cantidades incorrectas (negativas, y valores límite)
        Velero[] arrayBarcos4 = TestUtilidades.crearArrayBarcos(-1);
        Velero[] arrayBarcos5 = TestUtilidades.crearArrayBarcos(0);  // Se prueban también los valores límite
        Velero[] arrayBarcos6 = TestUtilidades.crearArrayBarcos(12); // Se prueban también los valores límite
        System.out.println();
    }
}
