package tarea05;

/**
 * Programa de pruebas del <b>ejercicio 03</b>  de la clase <code>Velero</code>
 * @author profesorado
 */
public class TestEj03 {
    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestEj03() {

    }

    /**
     * Método main del programa de pruebas
     * @param args argumentos de consola
     */
    public static void main(String[] args) {
        // Cabecera de salida de las pruebas
        System.out.println("CASO DE PRUEBAS 03: GETTERS Y MÉTODOS ESTÁTICOS");
        System.out.println("------------------------------------------------\n");

        // Se prueban los atributos públicos de la clase antes de crear los barcos
        System.out.println("--------------------------------------------------------");
        System.out.println(" CONSULTA DE ATRIBUTOS DE CLASE (antes de crear objetos)");
        System.out.println("--------------------------------------------------------");
        System.out.println(TestUtilidades.consultaAtributosEstaticos());

        // Se crea un objeto de tipo Velero
        // utilizando un método de pruebas de la clase TestUtilidades
        System.out.println("---------------------------------");
        System.out.println(" CREACIÓN DE UN VELERO DE PRUEBA ");
        System.out.println("---------------------------------");
        Velero barco = TestUtilidades.crearBarco("Arrebatos", 1, 4);
        
        System.out.println("----------------------------------");
        System.out.println(" CONSULTA DE LOS DATOS DEL VELERO ");
        System.out.println("----------------------------------");
        System.out.println(TestUtilidades.consultarAtributosVelero(barco));
        
        // Se crean tres objetos de tipo Velero con los datos por defecto almacenados en un array
        System.out.println("--------------------------------------------------------");
        System.out.println(" CREACIÓN DE UN ARRAY DE 3 VELEROS CON DATOS POR DEFECTO ");
        System.out.println("--------------------------------------------------------");
        Velero[] arrayBarcos1 = TestUtilidades.crearArrayBarcos(3);

        // Se leen los atributos de cada barco usando los getter() pertinentes
        System.out.println("-------------------------------------------------");
        System.out.println(" CONSULTA DE LOS DATOS DE LOS VELEROS POR DEFECTO ");
        System.out.println("-------------------------------------------------");
        System.out.println(TestUtilidades.consultarAtributosArrayVeleros(arrayBarcos1));
        
        
        // Se crean varios objetos de tipo Velero almacenados en un array
        // utilizando un método de pruebas de la clase TestUtilidades (por defecto, crea 5 barcos de ejemplo).
        System.out.println("------------------------------------------------------");
        System.out.println(" CREACIÓN DE UN ARRAY DE 5 VELEROS CON DATOS DE PRUEBA ");
        System.out.println("------------------------------------------------------");
        Velero[] arrayBarcos2 = TestUtilidades.crearArrayBarcosDatosPrueba();

        // Se leen los atributos de cada barco usando los getter() pertinentes
        System.out.println("------------------------------------------------");
        System.out.println(" CONSULTA DE LOS DATOS DE LOS VELEROS DE PRUEBA ");
        System.out.println("------------------------------------------------");
        System.out.println(TestUtilidades.consultarAtributosArrayVeleros(arrayBarcos2));

        // Se prueban los atributos públicos de la clase después de crear los barcos
        System.out.println("----------------------------------------------------------");
        System.out.println(" CONSULTA DE ATRIBUTOS DE CLASE (después de crear objetos)");
        System.out.println("----------------------------------------------------------");
        System.out.println(TestUtilidades.consultaAtributosEstaticos());
    }
}
