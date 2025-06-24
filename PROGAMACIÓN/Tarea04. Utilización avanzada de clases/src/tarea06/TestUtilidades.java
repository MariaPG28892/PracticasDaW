package tarea06;

/**
 * Clase de utilidades necesaria para la realizacion de las pruebas escritas en
 * las clases <b>TestEj01</b>, <b>TestEj02</b>, <b>TestEj03</b> ,
 * <b>TestEj04</b>, <b>TestEj05</b> y <b>TestEj06</b>
 *
 * @author profesorado
 */
public class TestUtilidades {

    /**
     * Se crea un constructor privado para evitar que la clase pueda
     * instanciarse
     */
    private TestUtilidades() {

    }

    /**
     * Método de consulta de atributos estáticos de la clase Velero.
     *
     * @return String Consulta de atributos estáticos de la clase Velero
     */
    public static String consultaAtributosEstaticosVelero() {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();
        consulta.append(String.format(" -> Número de veleros creados: %d\n", Velero.getNumVeleros()));
        consulta.append(String.format(" -> Número de barcos creados: %d\n", Velero.getNumBarcos()));
        consulta.append(String.format(" -> Número de barcos navegando: %d\n", Velero.getNumBarcosNavegando()));
        consulta.append(String.format(" -> Tiempo total de navegacion acumulado por todos los barcos: %.2f minutos\n", Velero.getTiempoTotalNavegacion()));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Método de consulta de atributos estáticos de la clase Lancha.
     *
     * @return String Consulta de atributos estáticos de la clase Lancha.
     */
    public static String consultaAtributosEstaticosLancha() {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();
        consulta.append(String.format(" -> Número de lanchas creadas: %d\n", Lancha.getNumLanchas()));
        consulta.append(String.format(" -> Número de barcos creados: %d\n", Velero.getNumBarcos()));
        consulta.append(String.format(" -> Número de barcos navegando: %d\n", Velero.getNumBarcosNavegando()));
        consulta.append(String.format(" -> Tiempo total de navegacion acumulado por todos los barcos: %.2f minutos\n", Velero.getTiempoTotalNavegacion()));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Método de consulta de atributos públicos de la clase Velero
     *
     * @return String Consulta de atributos públicos de la clase Velero
     */
    public static String consultaAtributosPublicosVelero() {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();
        consulta.append(String.format(" -> Número mínimo de mástiles: %s\n", Velero.MIN_MASTILES));
        consulta.append(String.format(" -> Número máximo de mástiles: %d\n", Velero.MAX_MASTILES));
        consulta.append(String.format(" -> Velocidad mínima de navegación: %d nudos\n", Velero.MIN_VELOCIDAD_VELERO));
        consulta.append(String.format(" -> Velocidad máxima de navegación: %d nudos\n", Velero.MAX_VELOCIDAD_VELERO));
        consulta.append(String.format(" -> Patrón por defecto durante la navegación: %s\n", Velero.PATRON_POR_DEFECTO));
        consulta.append(String.format(" -> Rumbo por defecto durante la navegación: %s\n", Velero.RUMBO_POR_DEFECTO));
        consulta.append(String.format(" -> Número mínimo de tripulantes: %d\n", Velero.MIN_TRIPULANTES));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Método de consulta de atributos públicos de la clase Lancha
     *
     * @return String Consulta de atributos públicos de la clase Lancha
     */
    public static String consultaAtributosPublicosLancha() {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();
        consulta.append(String.format(" -> Número mínimo de motores: %s\n", Lancha.MIN_MOTORES));
        consulta.append(String.format(" -> Número máximo de motores: %d\n", Lancha.MAX_MOTORES));
        //consulta.append(String.format(" -> Velocidad mínima de navegación: %d nudos\n", Lancha.MIN_VELOCIDAD_LANCHA));
        //consulta.append(String.format(" -> Velocidad máxima de navegación: %d nudos\n", Lancha.MAX_VELOCIDAD_LANCHA));
        consulta.append(String.format(" -> Patrón por defecto durante la navegación: %s\n", Lancha.PATRON_POR_DEFECTO));
        consulta.append(String.format(" -> Rumbo por defecto durante la navegación: %s\n", Lancha.RUMBO_POR_DEFECTO));
        consulta.append(String.format(" -> Número mínimo de tripulantes: %d\n", Lancha.MIN_TRIPULANTES));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Método de consulta de atributos privados del objeto de la clase Velero
     *
     * @param barco Velero sobre el que se consultan sus atributos
     * @return String Consulta de atributos privados del objeto de la clase
     * Velero
     */
    public static String consultarAtributosVelero(Velero barco) {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();

        // Ahora se lee el valor de cada getter() del dispositivo
        consulta.append("Leyendo los datos almacenados en el velero...\n");
        consulta.append(String.format(" -> Nombre del velero: %s\n", barco.getNombreBarco()));
        consulta.append(String.format(" -> Número de mástiles: %d\n", barco.getNumMastiles()));
        consulta.append(String.format(" -> Número máximo de tripulantes: %d\n", barco.getMaxTripulantes()));
        consulta.append(String.format(" -> Navegando: %s\n", (barco.isNavegando() ? "Sí" : "No")));
        consulta.append(String.format(" -> Tiempo total de navegacion del velero: %s minutos \n", barco.getTiempoTotalNavegacionBarco()));
        consulta.append(String.format(" -> Velocidad de navegación: %d nudos\n", barco.getVelocidad()));
        consulta.append(String.format(" -> Rumbo de navegación: %s\n", barco.getRumbo()));
        consulta.append(String.format(" -> Patrón durante la navegación: %s\n", barco.getPatron()));
        consulta.append(String.format(" -> Número de tripulantes durante la navegación: %d\n\n", barco.getTripulacion()));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Método de consulta de atributos privados del objeto de la clase Lancha
     *
     * @param barco Lancha sobre la que se consultan sus atributos
     * @return String Consulta de atributos privados del objeto de la clase
     * Lancha
     */
    public static String consultarAtributosLancha(Lancha barco) {
        // Se utiliza un objeto de tipo StringBuilder para formatear las pruebas
        StringBuilder consulta = new StringBuilder();

        // Ahora se lee el valor de cada getter() del dispositivo
        consulta.append("Leyendo los datos almacenados en la lancha...\n");
        consulta.append(String.format(" -> Nombre de la lancha: %s\n", barco.getNombreBarco()));
        consulta.append(String.format(" -> Número de motores: %d\n", barco.getNumMotores()));
        consulta.append(String.format(" -> Número máximo de tripulantes: %d\n", barco.getMaxTripulantes()));
        consulta.append(String.format(" -> Cantidad de combustible: %d\n", barco.getCantidadCombustible()));
        consulta.append(String.format(" -> Navegando: %s\n", (barco.isNavegando() ? "Sí" : "No")));
        consulta.append(String.format(" -> Tiempo total de navegacion de la lancha: %s minutos \n", barco.getTiempoTotalNavegacionBarco()));
        consulta.append(String.format(" -> Velocidad de navegación: %d nudos\n", barco.getVelocidad()));
        consulta.append(String.format(" -> Rumbo de navegación: %s\n", barco.getRumbo()));
        consulta.append(String.format(" -> Patrón durante la navegación: %s\n", barco.getPatron()));
        consulta.append(String.format(" -> Número de tripulantes durante la navegación: %d\n\n", barco.getTripulacion()));

        // Se devuelve la salida formateada
        return consulta.toString();
    }

    /**
     * Crea un Velero usando el constructor de tres parámetros
     *
     * @param nombre Nombre del barco
     * @param mastiles Numero de mastiles del barco
     * @param tripulantes Numero de tripulantes maximo del barco
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * @throws NullPointerException Si alguno de los parámetros es nulo
     *
     * @return String Consulta de atributos públicos de la clase
     */
    public static Velero crearVelero(String nombre, int mastiles, int tripulantes) throws IllegalArgumentException, NullPointerException {
        // Se crea un objeto de tipo barco
        Velero barco = null;
        try {
            System.out.printf("Creando un Velero con los parámetros [%s,%d,%d]...\n", nombre, mastiles, tripulantes);
            barco = new Velero(nombre, mastiles, tripulantes);
            System.out.println(" -> Objeto creado con éxito.\n");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }

        // Se retorna el barco creado
        return barco;
    }

    /**
     * Crea una Lancha usando el constructor de tres parámetros
     *
     * @param nombre Nombre del barco
     * @param motores Numero de motores del barco
     * @param combustible Nivel de combustible (en litros)
     * @param tripulantes Numero de tripulantes maximo del barco
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * @throws NullPointerException Si alguno de los parámetros es nulo
     *
     * @return String Consulta de atributos públicos de la clase
     */
    public static Lancha crearLancha(String nombre, int motores, int combustible, int tripulantes) throws IllegalArgumentException, NullPointerException {
        // Se crea un objeto de tipo barco
        Lancha barco = null;
        try {
            System.out.printf("Creando una Lancha con los parámetros [%s,%d,%d,%d]...\n", nombre, motores, combustible, tripulantes);
            barco = new Lancha(nombre, motores, combustible, tripulantes);
            System.out.println(" -> Objeto creado con éxito.\n");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }

        // Se retorna el barco creado
        return barco;
    }

    /**
     * Crea un Velero usando el constructor sin parámetros
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     *
     * @return String Consulta de atributos públicos de la clase
     */
    public static Velero crearVelero() throws IllegalArgumentException {
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
     * Crea una Lancha usando el constructor sin parámetros
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     *
     * @return String Consulta de atributos públicos de la clase
     */
    public static Lancha crearLancha() throws IllegalArgumentException {
        // Se crea un objeto de tipo Velero
        Lancha barco = null;
        try {
            System.out.printf("Creando una Lancha con los parámetros por defecto...\n");
            barco = new Lancha();
            System.out.println(" -> Objeto creado con éxito.\n");
        } catch (IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }

        // Se retorna el barco creado
        return barco;
    }

    /**
     * Realiza una prueba del método de iniciarNavegacion del Velero controlando
     * las excepciones
     *
     * @param barco Barco (Velero)
     * @param velocidad Velocidad de navegacion (en nudos)
     * @param rumbo Rumbo del barco
     * @param patron Patron del barco
     * @param tripulacion Numero de tripulantes (exceptuando al patron)
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * @throws NullPointerException Si alguno de los parámetros es nulo
     * @throws IllegalStateException Si alguno de los parámetros no es válido
     */
    public static void pruebaMetodoIniciarNavegacionVelero(Velero barco, int velocidad, String rumbo, String patron, int tripulacion) throws IllegalArgumentException, IllegalStateException, NullPointerException {
        // Testea el método de iniciar la navegacion y captura las excepciones
        try {
            barco.iniciarNavegacion(velocidad, rumbo, patron, tripulacion);
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }

    /**
     * Realiza una prueba del método de iniciarNavegacion de la Lancha
     * controlando las excepciones
     *
     * @param barco Barco (Lancha)
     * @param velocidad Velocidad de navegacion (en nudos)
     * @param rumbo Rumbo del barco
     * @param patron Patron del barco
     * @param tripulacion Numero de tripulantes (exceptuando al patron)
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * @throws NullPointerException Si alguno de los parámetros es nulo
     * @throws IllegalStateException Si alguno de los parámetros no es válido
     */
    public static void pruebaMetodoIniciarNavegacionLancha(Lancha barco, int velocidad, String rumbo, String patron, int tripulacion) throws IllegalArgumentException, IllegalStateException, NullPointerException {
        // Testea el método de iniciar la navegacion y captura las excepciones
        try {
            barco.iniciarNavegacion(velocidad, rumbo, patron, tripulacion);
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }

    /**
     * Realiza una prueba del método pararNavegacion del Velero controlando las
     * excepciones
     *
     * @param barco Barco (Velero)
     * @param tiempo Tiempo de navegación (en minutos)
     *
     * @throws IllegalStateException Si alguno de los parámetros no es válido
     */
    public static void pruebaMetodoPararNavegacionEmbarcacion(Embarcacion barco, int tiempo) throws IllegalStateException {
        // Genera una excepción: un barco que no está navegando no puede parar la navegacion
        try {
            barco.pararNavegacion(tiempo);
        } catch (IllegalStateException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }

    /**
     * Realiza una prueba del método setRumbo del Velero controlando las
     * excepciones
     *
     * @param barco Barco (Velero)
     * @param rumbo Rumbo que toma el velero: ceñida o empopada
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * @throws NullPointerException Si alguno de los parámetros es nulo
     * @throws IllegalStateException Si alguno de los parámetros no es válido
     */
    public static void pruebaMetodoSetRumboVelero(Velero barco, String rumbo) throws IllegalStateException, NullPointerException, IllegalArgumentException {
        // Genera una excepción: no se indica rumbo o es incorrecto
        try {
            barco.setRumbo(rumbo);
        } catch (IllegalStateException | NullPointerException | IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }

    /**
     * Realiza una prueba del método setRumbo del Velero controlando las
     * excepciones
     *
     * @param barco Barco (Lancha)
     * @param rumbo Rumbo que toma la lancha: norte, sur, este u oeste
     *
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * @throws NullPointerException Si alguno de los parámetros es nulo
     * @throws IllegalStateException Si alguno de los parámetros no es válido
     */
    public static void pruebaMetodoSetRumboLancha(Lancha barco, String rumbo) throws IllegalStateException, NullPointerException, IllegalArgumentException {
        // Genera una excepción: no se indica rumbo o es incorrecto
        try {
            barco.setRumbo(rumbo);
        } catch (IllegalStateException | NullPointerException | IllegalArgumentException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }

    /**
     * Realiza una prueba del método iniciarRegata del Velero controlando las
     * excepciones
     *
     * @param barco1 Barco1 (Velero)
     * @param barco2 Barco2 (Velero)
     *
     * @throws NullPointerException Si alguno de los parámetros es nulo
     * @throws IllegalStateException Si alguno de los parámetros no es válido
     */
    public static void pruebaMetodoIniciarRegata(Velero barco1, Velero barco2) throws IllegalStateException, NullPointerException {
        // Crea una cadena para almacenar el resultado de la regata
        String resultado;

        // Testea el método de iniciar una regata y captura las excepciones
        try {
            // Realiza la regata
            resultado = barco1.iniciarRegata(barco2);

            // Muestra el resultado de la regata
            System.out.println(resultado);
        } catch (IllegalStateException | NullPointerException e) {
            System.out.printf(" -> Se ha producido un error: %s\n", e.getMessage());
        }
    }

}
