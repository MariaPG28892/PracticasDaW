package tarea06;

/**
 * Programa de pruebas del <b>ejercicio 02</b> de las clases <code>Embarcacion</code>, <code>Velero</code> y <code>Lancha</code>: 
 * Consulta de los atributos de objeto y de clase
 * 
 * @author profesorado
 */
public class TestEj02 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj02() {

    }

    /**
     * Método main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main(String[] args) {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 02: GETTERS Y MÉTODOS ESTÁTICOS");
        System.out.println("------------------------------------------------\n");

        // Se prueban los atributos públicos de la clase antes de crear los barcos
        System.out.println("--------------------------------------------------------");
        System.out.println(" CONSULTA DE ATRIBUTOS DE CLASE (antes de crear objetos)");
        System.out.println("--------------------------------------------------------");
        System.out.println(TestUtilidades.consultaAtributosEstaticosVelero());
        System.out.println(TestUtilidades.consultaAtributosEstaticosLancha());

        // Se crea un objeto de tipo Velero
        // utilizando un método de pruebas de la clase TestUtilidades
        System.out.println("---------------------------------");
        System.out.println(" CREACIÓN DE UN VELERO DE PRUEBA ");
        System.out.println("---------------------------------");
        Velero velero1 = TestUtilidades.crearVelero("Arrebatos", 1, 4);
        
        // Se crea un objeto de tipo Lancha
        // utilizando un método de pruebas de la clase TestUtilidades
        System.out.println("----------------------------------");
        System.out.println(" CREACIÓN DE UNA LANCHA DE PRUEBA ");
        System.out.println("----------------------------------");
        Lancha lancha1 = TestUtilidades.crearLancha("Rapidisima", 1, 15,2);
        
        System.out.println("----------------------------------");
        System.out.println(" CONSULTA DE LOS DATOS DEL VELERO ");
        System.out.println("----------------------------------");
        System.out.println(TestUtilidades.consultarAtributosVelero(velero1));
        System.out.println("------------------------------------");
        System.out.println(" CONSULTA DE LOS DATOS DE LA LANCHA ");
        System.out.println("------------------------------------");
        System.out.println(TestUtilidades.consultarAtributosLancha(lancha1));
        
        
        // Se prueban los atributos públicos de la clase después de crear los barcos
        System.out.println("----------------------------------------------------------");
        System.out.println(" CONSULTA DE ATRIBUTOS DE CLASE (después de crear objetos)");
        System.out.println("----------------------------------------------------------");
        System.out.println(TestUtilidades.consultaAtributosEstaticosVelero());
        System.out.println(TestUtilidades.consultaAtributosEstaticosLancha());
    }
}
