package tarea06;

/**
 * Programa de pruebas del <b>ejercicio 01</b> de las clases <code>Embarcacion</code>, <code>Velero</code> y <code>Lancha</code>: 
 * Creación y consulta de objetos
 * 
 * @author profesorado
 */
public class TestEj01 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj01() {

    }

    /**
     * Método main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main(String[] args) {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 01: CONSTRUCTORES Y ATRIBUTOS");
        System.out.println("------------------------------------------------\n");
        
        
        System.out.println("------------------------------------");
        System.out.println(" - CONSULTA DE ATRIBUTOS PÚBLICOS");
        System.out.println("-------------------------------------");

        // Se prueban los atributos públicos de la clase
        System.out.println(TestUtilidades.consultaAtributosPublicosVelero());
        System.out.println(TestUtilidades.consultaAtributosPublicosLancha());

        // Se prueban los constructores y el método fábrica
        // Pruebas correctas (se crean varios objetos usando los distintos constructores y el método de fábrica)
        System.out.println("------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL CONSTRUCTOR DE TRES PARÁMETROS (con datos correctos)");
        System.out.println("------------------------------------------------------------------");

        Velero velero1 = TestUtilidades.crearVelero("Atlantis", 2, 4);
        Velero velero2 = TestUtilidades.crearVelero("Arrebatos", 3, 5);
        Lancha lancha1 = TestUtilidades.crearLancha("Guapante", 1, 15,1);
        Lancha lancha2 = TestUtilidades.crearLancha("Rapidisima", 2, 15,2);
        
        System.out.println("-----------------------------------------");
        System.out.println(" - PRUEBA DEL CONSTRUCTOR SIN PARÁMETROS");
        System.out.println("-----------------------------------------");        
        
        Velero velero3 = TestUtilidades.crearVelero();
        Lancha lancha3 = TestUtilidades.crearLancha();

        // Pruebas que deben lanzar excepciones
        // Pruebas incorrectas (se crean varios objetos usando los distintos constructores y el método de fábrica)
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" - PRUEBA DEL CONSTRUCTOR DE TRES PARÁMETROS (con datos no válidos)");
        System.out.println("-------------------------------------------------------------------");

        // Se instancian varios objetos utilizando valores nulos en el constructor o la cadena vacía
        Velero velero4 = TestUtilidades.crearVelero(null, 1, 2);
        Velero velero5 = TestUtilidades.crearVelero("", 2,4);
        Lancha lancha4 = TestUtilidades.crearLancha(null, 1, 15,2);
        Lancha lancha5 = TestUtilidades.crearLancha("", 2,15,4);
        
    }
}
