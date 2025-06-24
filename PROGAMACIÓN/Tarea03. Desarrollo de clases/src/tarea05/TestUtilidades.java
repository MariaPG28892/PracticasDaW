package tarea05;

/**
 * Clase de utilidades necesaria para la realizacion de las pruebas escritas
 * en las clases <b>TestEj0102</b>, <b>TestEj03</b> , <b>TestEj040506</b>  
 * y <b>TestEj07</b> 
 * @author profesorado
 */
public class TestUtilidades {

    /**
     * Se crea un constructor privado para evitar que la clase pueda instanciarse
     */
    private TestUtilidades() {

    }

    /**
     * Método de consulta de atributos estáticos de la clase
     *
     * @return String Consulta de atributos estáticos de la clase
     */
    public static String consultaAtributosEstaticos() {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();
        consulta.append(String.format(" -> Número de veleros creados: %d\n", Velero.getNumBarcos()));
        consulta.append(String.format(" -> Número de veleros navegando: %d\n", Velero.getNumBarcosNavegando()));
        consulta.append(String.format(" -> Tiempo total de navegacion acumulado por todos los veleros: %.2f minutos\n", Velero.getTiempoTotalNavegacion()));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Método de consulta de atributos públicos de la clase
     *
     * @return String Consulta de atributos públicos de la clase
     */
    public static String consultaAtributosPublicos() {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();
        consulta.append(String.format(" -> Número mínimo de mástiles: %d\n", Velero.MIN_MASTILES));
        consulta.append(String.format(" -> Número máximo de mástiles: %d\n", Velero.MAX_MASTILES));
        consulta.append(String.format(" -> Velocidad mínima de navegación: %d nudos\n", Velero.MIN_VELOCIDAD));
        consulta.append(String.format(" -> Velocidad máxima de navegación: %d nudos\n", Velero.MAX_VELOCIDAD));
        consulta.append(String.format(" -> Patrón por defecto durante la navegación: %s\n", Velero.PATRON_POR_DEFECTO));
        consulta.append(String.format(" -> Rumbo por defecto durante la navegación: %s\n", Velero.RUMBO_POR_DEFECTO));
        consulta.append(String.format(" -> Número mínimo de tripulantes: %d\n", Velero.MIN_TRIPULANTES));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Método de consulta de atributos privados del objeto
     *
     * @param barco Velero sobre el que se consultan sus atributos
     * @return String Consulta de atributos privados del objeto
     */
    public static String consultarAtributosVelero(Velero barco) {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();

        // Ahora se lee el valor de cada getter() del dispositivo
        consulta.append("Leyendo los datos almacenados en el velero...\n");
        consulta.append(String.format(" -> Nombre del velero: %s\n", barco.getNombreBarco()));
        consulta.append(String.format(" -> Número de mástiles: %d\n", barco.getNumMastiles()));
        consulta.append(String.format(" -> Número máximo de tripulantes: %d\n", barco.getMaxTripulantes()));
        consulta.append(String.format(" -> Navegando: %s\n", (barco.isNavegando()? "Sí" : "No")));
        consulta.append(String.format(" -> Tiempo total de navegacion del velero: %s minutos \n", barco.getTiempoTotalNavegacionBarco()));
        consulta.append(String.format(" -> Velocidad de navegación: %d nudos\n", barco.getVelocidad()));
        consulta.append(String.format(" -> Rumbo de navegación: %s\n", barco.getRumbo()));
        consulta.append(String.format(" -> Patrón durante la navegación: %s\n", barco.getPatron()));
        consulta.append(String.format(" -> Número de tripulantes durante la navegación: %d\n\n", barco.getTripulacion()));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Lee los datos de un array de barcos y los imprime por pantalla
     *
     * @param arrayBarcos Array de barcos
     * @return String consulta de atributos de todos los veleros de un array de barcos
     */
    public static String consultarAtributosArrayVeleros(Velero[] arrayBarcos) {
        StringBuilder consulta = new StringBuilder();
        // Se leen los datos de cada barco y se devuelven
        for (Velero arrayBarco : arrayBarcos) {
            consulta.append(TestUtilidades.consultarAtributosVelero(arrayBarco));
        }
        return consulta.toString();
    }
    
     /**
     * Crea un Velero usando el constructor de tres parámetros
     * 
     * @param nombre Nombre del barco
     * @param mastiles Numero de mastiles del barco
     * @param tripulantes Numero de tripulantes maximo del barco
     * @return String Consulta de atributos públicos de la clase
     */
    public static Velero crearBarco(String nombre, int mastiles, int tripulantes) {
        // Se crea un objeto de tipo barco
        Velero barco = null;
        try {
            System.out.printf("Creando un Velero con los parámetros [%s,%d,%d]...\n", nombre, mastiles, tripulantes);
            barco = new Velero(nombre, mastiles, tripulantes);
            System.out.println(" -> Objeto creado con éxito.\n");
        } catch (IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        } catch (NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }

        // Se retorna el barco creado
        return barco;
    }

    /**
     * Crea un Velero usando el constructor sin parámetros
     * 
     * @return String Consulta de atributos públicos de la clase
     */
    public static Velero crearBarco() {
        // Se crea un objeto de tipo Velero
        Velero barco = null;
        try {
            System.out.printf("Creando un Velero con los parámetros por defecto...\n");
            barco = new Velero();
            System.out.println(" -> Objeto creado con éxito.\n");
        } catch (IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }

        // Se retorna el barco creado
        return barco;
    }

    /**
     * Crea un array de barcos con los datos por defecto usando el método fábrica
     * 
     * @param cantidad Indica la cantidad de barcos a crear en el array
     * @return Velero Array de barcos
     */
    public static Velero[] crearArrayBarcos(int cantidad) {
        // Se crea un objeto de tipo array para almacenar las aeronaves
        Velero[] arrayBarcos = null;
        try {
            System.out.printf("Intentando crear array de %d objetos de tipo Velero con los parámetros por defecto...\n", cantidad);
            arrayBarcos = Velero.crearArrayVelero(cantidad);
            System.out.println(" -> Array de Objetos creado con éxito.\n");
        } catch (IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }

        // Se retorna el vector creado
        return arrayBarcos;
    }

    /**
     * Crea un array de barcos con datos de prueba
     *
     * @return Velero Array de barcos
     */
    public static Velero[] crearArrayBarcosDatosPrueba() {
        // Se crea un array de dispositivos vacío
        Velero[] arrayBarcos = new Velero[5];

        // Se crean 5 barcos de ejemplo con datos usando el constructor de 5 parámetros
        arrayBarcos[0] = TestUtilidades.crearBarco("Atlantis", 1, 2);
        arrayBarcos[1] = TestUtilidades.crearBarco("Arrebatos", 2, 4);
        arrayBarcos[2] = TestUtilidades.crearBarco("Tango", 2, 5);
        arrayBarcos[3] = TestUtilidades.crearBarco("Peneque", 3, 7);
        arrayBarcos[4] = TestUtilidades.crearBarco("Pipiripao", 3, 9);

        // Se devuelve el array de barcos
        return arrayBarcos;
    }
    
    /**
     * Realiza una prueba del método de iniciarNavegacion del Velero controlando las excepciones
     *
     * @param barco Barco (Velero)
     * @param velocidad Velocidad de navegacion (en nudos)
     * @param rumbo Rumbo del barco
     * @param patron Patron del barco
     * @param tripulacion Numero de tripulantes (exceptuando al patron)
     */
    public static void pruebaMetodoIniciarNavegacion(Velero barco, int velocidad, String rumbo, String patron, int tripulacion) throws IllegalStateException
    {
        // Testea el método de iniciar la navegacion y captura las excepciones
        try {
            barco.iniciarNavegacion(velocidad, rumbo, patron, tripulacion);
        } catch (IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        } catch (IllegalStateException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        } catch (NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        } 
    }
    
    /**
     * Realiza una prueba del método pararNavegacion del Velero controlando las excepciones
     *
     * @param barco Barco (Velero)
     * @param tiempo Tiempo de navegación (en minutos)
     */
    public static void pruebaMetodoPararNavegacion(Velero barco, int tiempo) throws IllegalStateException
    {
        // Genera una excepción: un barco que no está navegando no puede parar la navegacion
        try {
            barco.pararNavegacion(tiempo);
        } catch (IllegalStateException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }
    
    /**
     * Realiza una prueba del método setRumbo del Velero controlando las excepciones
     *
     * @param barco Barco (Velero)
     * @param rumbo Rumbo que toma el velero: ceñida o empopada
     */
    public static void pruebaMetodoSetRumbo(Velero barco, String rumbo) throws IllegalStateException, NullPointerException, IllegalArgumentException
    {
        // Genera una excepción: no se indica rumbo o es incorrecto
        try {
            barco.setRumbo(rumbo);
        } catch (IllegalStateException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        } catch (NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }
    
    /**
     * Realiza una prueba del método iniciarRegata del Velero controlando las excepciones
     *
     * @param barco1 Barco1 (Velero)
     * @param barco2 Barco2 (Velero)
     */
    public static void pruebaMetodoIniciarRegata(Velero barco1, Velero barco2) throws IllegalStateException, NullPointerException
    {
        // Crea una cadena para almacenar el resultado de la regata
        String resultado;
        
        // Testea el método de iniciar una regata y captura las excepciones
        try {
            // Realiza la regata
            resultado = barco1.iniciarRegata(barco2);
            
            // Muestra el resultado de la regata
            System.out.println(resultado);
        } catch (IllegalStateException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        } catch (NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }
}
