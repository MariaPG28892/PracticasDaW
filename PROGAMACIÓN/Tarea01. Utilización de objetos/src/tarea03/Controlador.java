package tarea03;

/*
Importamos las dos clases que vamos a necesitar que se encuentran dentro de la libreria. En este caso ponemos import, indicamos
el nombre de la librería (lo miré en libraries) seguido de . y aeronave por un lado y aeropuerto por otro.
 */
import libtarea3.Aeronave;
import libtarea3.Aeropuerto;
/*
Importamos la clase localDateTime porque vamos a trabajar con fecha y hora, por lo tanto necesitamos ambas, por otro lado también
importarmos el DateTimeFormatter para poder formatear la fecha y ponerla a nuestro gusto.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// ------------------------------------------------------------
//                   Clase Controlador
// ------------------------------------------------------------

/**
 * <p>
 * Clase que representa al <strong>controlador</strong>, que será la clase que
 * utilizaremos y donde se escribirán las diferentes operaciones en aeronaves y
 * aeropuertos que se nos pide en el enunciado de la tarea.</p>
 *
 * @author María Palomares Gallo.
 */
public class Controlador {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        /*
        Declaramos las variables objeto de la clase aeropuerto y la inicializamos con null para no asignarle ningún objeto
        en específico y que no de error, si le quitamos el null darían error todos más adelante. Es de la única manera que 
        he conseguido que no me de error.
         */
        Aeropuerto elPrat = null;
        Aeropuerto barajas = null;
        Aeropuerto federicoGarciaLorca = null;
        /*
        Hacemos lo mismo con las variables de referencia de la clase aeronave. De la misma manera he tenido que inicializarlas
        en null porque si no me daba error.
         */
        Aeronave avion1 = null;
        Aeronave avion2 = null;
        Aeronave avion3 = null;
        /*
        Aquí lo primero que hacemos es recoger la fecha y hora de este momento en la variable fechaHora, una vez la tenemos, pasamos
        a crear otra variable de tipo dateTimeFomatter llamada formatoFecha y metemos dentro el formato o patrón que queremos 
        que tenga nuestra fecha. Luego creamos una variable String con el nombre fechaFormateada, donde vamos a guardar, la fecha y la hora
        que recogimos la primera vez y le vamos a aplicar el formato que hemos declarado en la variable fechaHoraFormateada.
         */
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/YYYY, hh:mm:ss");
        String fechaFormateada = fechaHora.format(formatoFecha);

        //----------------------------------------------
        //          Creación de objetos
        //----------------------------------------------
        // Instanciar los 3 aeropuertos: Barcelona, Madrid, Granada.
        //CABECERA DE LA INSTANCIACIÓN DE LOS 3 AEROPUERTOS.
        System.out.println("-------------------------");
        System.out.println("---Creando aeropuertos---");
        System.out.println("-------------------------\n");
        /*
        Abrimos try-catch para que en el caso de introducir algún parámetro erróneo nos capture el error y en lugar de romper
        el programa, nosotros saquemos por pantalla el error. 
         */
        try {
            /*
            He creado las tres variables arriba y aquí creamos los aeropuertos con new Aeropuerto y por parámetros le he 
            pasado el nombre del aeropuerto y la ciudad, ambos en tipo String. Si se realiza sacamos por pantalla que los 
            aeropuertos están creados, en el caso de que falle se lanzará el error del catch. 
             */
            elPrat = new Aeropuerto("El Prat", "Barcelona");
            barajas = new Aeropuerto("Barajas", "Madrid");
            federicoGarciaLorca = new Aeropuerto("Federico Garcia Lorca", "Granada");
            System.out.printf("**Aeropuertos creados**%n");

        } catch (IllegalArgumentException error) {
            System.out.printf("%nError al crear el aeropuerto: %s%n", error.getMessage());
        }

        // Instanciar las 3 aeronaves: Avion1, Avion2, Avion3.
        //CABECERA PARA CREAR LAS AERONAVES.
        System.out.println("\n-------------------------");
        System.out.println("----Creando aeronaves----");
        System.out.println("-------------------------\n");
        /*
        De nuevo abrimos un try-catch, aquí vemos 3 tipos de constructores, al primero no le pasamos párametros, por los que se les
        asignarán por defecto, en el segundo le pasamos por parámetros la matrícula y el modelo y en tercer lugar, le pasamos por parámetros
        la matrícula, el modelo y en este caso el aeropuerto de barajas (hay que poner el nombre de la variable para que funcione).
        Si funciona lanzará el mensaje de que las aeronaves están creadas, si se produce cualquier fallo saltará el error del catch.
         */
        try {
            avion1 = new Aeronave();
            avion2 = new Aeronave("EC-123", "Boing747");
            avion3 = new Aeronave("EC-456", "Boing787", barajas);
            System.out.println("**Aeronaves creadas**");
        } catch (IllegalArgumentException error) {
            System.out.printf("%nError al crear la aeronave %s %n", error.getMessage());
        }

        //----------------------------------------------
        //   Inicio de la secuencia de instrucciones
        //----------------------------------------------
        //CABECERA DE LA SECUENCIA DE INSTRUCCIONES.
        System.out.println("\n-------------------------");
        System.out.println("-Secuencia instrucciones-");
        System.out.println("-------------------------\n");

        //Avion1 despega con velocidad 1500, altitud 1750, rumbo 50 y fechaHora actual
        /*
        PARA DESPEGAR:
        Abrimos un try-catch y dentro de este lo que hacemos es escribir el avion que deseemos que despegue y ponemos avion.despegar
        y le metemos los parámetros de velocidad, altitud, rumbo y la fechaFormateada, en el orden que se pide, sino 
        estaremos metiendo los valores en otros parámetros que no son. La fechaFormateada la coge porque la hemos convertido
        anteriormente en un String. Si no hay fallos en los parámetros como superar la altitud máxima por ejemplo, saltará
        el error del catch.
         */
        try {
            avion1.despegar(1500, 1750, 50, fechaFormateada);
            System.out.printf("Avion1 ha despegado%n");
        } catch (IllegalArgumentException error) {
            System.out.printf("%nError al despegar Avion1: %s%n", error.getMessage());
        }
        //Avion2 despega con velocidad 1500, altitud 1850 y rumbo 75
        try {
            avion2.despegar(1500, 1850, 75, fechaFormateada);
            System.out.printf("%nAvion2 ha despegado%n");
        } catch (IllegalArgumentException error) {
            System.out.printf("%nError al despegar Avion2: %s%n", error.getMessage());
        }

        //Avion3 despega con velocidad 1500, altitud 1000 y rumbo 180
        try {
            avion3.despegar(1500, 1000, 180, fechaFormateada);
            System.out.printf("%nAvion3 ha despegado%n");
        } catch (IllegalArgumentException error) {
            System.out.printf("%nError al despegar Avion3: %s%n", error.getMessage());
        }

        //Comprobar si Avion1 está volando
        /*
        Para comprobar si está volando usamos el system.out.print, como es un booleano lo sacamos con %b, y luego ponemos
        avion1.isVolando, que es un método getter, esto devolverá true si está volando y false si no está volando.
         */
        System.out.printf("%n¿El avión 1 está volando?: %b %n", avion1.isVolando());

        //Mostrar la matrícula del Avion2
        /*
        Para mostrar la matrícula, usamos printf de nuevo y como tenemos que sacar por pantalla un String usamos %s, seleccionamos
        el avión que deseemos, en este caso el 2 y utilizamos el método getter .getMatricula
         */
        System.out.printf("La matrícula del avión 2: %s%n", avion2.getMatricula());

        //Mostrar modelo del Avion3
        /*
        Para mostrar el modelo, lo que hacemos es sacarlo por printf, es un String por lo que usamos %s, seleccionamos el 
        avión y aplicamos el getter .getModelo().
         */
        System.out.printf("El modelo del avión 3: %s %n", avion3.getModelo());

        //Modificar rumbo del Avion2 a 90º y mostrarlo
        /*
        Para cambiar el rumbo, tenemos que usar un método setter, para ello primero escogemos el avión que deseemos, usamos
        .setRumbo() y dentro del paréntesis ponemos el entero que queremos cambiar, luego con printf lo sacamos por pantalla 
        con el getter. Es importante que el printf esté después del setter para que salga el rumbo modificado, si no saldrá
        sin modificar.
         */
        avion2.setRumbo(90);
        System.out.printf("El nuevo rumbo del avión 2: %d %n", avion2.getRumbo());

        //Avion3 aterriza en el aeropuerto de Barcelona despues de 75 minutos
        /*
        PARA ATERRIZAR. Abrimos un try-catch, dentro de try, escogemos el avión que deseemos y usamos el método que simula
        el aterrizaje, en este caso .aterrizar() y dentro le metemos los parámetros que sería el aeropuerto, que metemos la 
        variable que hemos creado(no se mete en String)y los minutos que ha tardado. Si no hay errores sacará por pantalla 
        que el avión ha aterrizado, si hay algún error saltará el catch mostrando el error de aterrizaje del avión.
         */
        try {
            avion3.aterrizar(elPrat, 75);
            System.out.printf("%nAvion3 ha aterrizado%n");
        } catch (IllegalArgumentException | IllegalStateException error) {
            /*
            IMPORTANTE: Haciendo pruebas, cambié la altura máxima en despegar, y en lugar de manejarme el error y continuar
            el programa, se me rompía, leyendo me di cuenta que el error me decía que era IlegalStateException, por lo que 
            lo añadí para controlarlo a partir de aquí y que no se rompiera, no se si es correcto, porque no lo pide en los
            apuntes, pero me parecía eficiente.
            */
            System.out.printf("%nError al aterrizar Avion3: %s%n", error.getMessage());
        }
        //Avion2 aterriza en el aeropuerto de Madrid despues de 80 minutos
        try {
            avion2.aterrizar(barajas, 80);
            System.out.printf("%nAvion2 ha aterrizado%n");
        } catch (IllegalArgumentException | IllegalStateException error) {
            System.out.printf("%nError al aterrizar Avion2: %s%n", error.getMessage());
        }

        //Comprobar si Avion2 está volando
        /*
        Para comprobar si está volando, por medio de printf, como es booleano usamos %b y luego usamos el avion2 y por medio
        del método getter isVolando(), nos devolverá true o false.
        */
        System.out.printf("%n¿El avion 2 está volando?: %b%n", avion2.isVolando());

        //Modificar altitud del Avion1 a 1250 metros y mostrarlo
        /*
        Para modificar la altitud, debemos de seleccionar el avión que deseemos y aplicarle el método setter .setAltitud(), donde
        por parámetro le introduciremos la cifra que queramos cambiar y la sacaremos por pantalla por printf, en este caso
        es un entero por lo que usamos %d y el método getter, getAltitud().
        */
        avion1.setAltitud(1250);
        System.out.printf("La nueva altitud del avión 1 es: %d %n", avion1.getAltitud());

        //Mostrar el tiempo total de vuelo del Avion2
        /*
        Para consultar el tiempo total de vuelo, con printf, miramos el tipo de dato que tenemos que sacar por pantalla en el 
        javaDoc, como es un entero, utilizamos %d, luego seleccionamos el avión y con el método getter .getTiempoTotalVuelo()
        sacamos el tiempo por pantalla.
        */
        System.out.printf("El tiempo total de vuelo del avion2 es: %d %n", avion2.getTiempoTotalVuelo());

        //Mostrar el aeropuerto del Avion3
        /*
        Para mostrar el aeropuerto, de nuevo usamos printf, luego %s porque es un String, luego seleccionamos el avión que
        deseemos y con el método getter, .getAeropuerto() sacamos por pantalla el aeropuerto en el que está.
        */
        System.out.printf("El aeropuerto del avion 3 es: %s %n", avion3.getAeropuerto());

        //Mostrar la fecha y hora de despegue del Avion3
        /*
        Para mostrar la fecha y la hora de despegue, volvemos a usar printf, %s porque vamos a sacar por pantalla un String, 
        luego seleccionamos el avion y el método getter, getFechaHoraDespegue() y nos lo mostrará por pantalla.
        */
        System.out.printf("El despegue del avion 3 se realizó con fecha: %s %n", avion3.getFechaHoraDespegue());

        //Avion3 despega con velocidad 860, altitud 1420 y rumbo 270
        /*
        DESPEGAR. Lo hacemos igual que he explicado antes, aquí de nuevo en el catch tengo IllegalArgumentException e IllegalState
        Exception para que capture ambos y no crashee el programa. 
        */
        try {
            avion3.despegar(860, 1420, 270, fechaFormateada);
            System.out.printf("%nAvion3 ha despegado %n");
        } catch (IllegalArgumentException | IllegalStateException  error) {
            System.out.printf("%nError al despegar el Avion3: %s%n", error.getMessage());
        }

        //Avion1 aterriza en el aeropuerto de Granada despues de 260 minutos
        /*
        Lo mismo que expliqué en aterrizar y controlando los dos errores como he hecho anteriormente también.
        */
        try {
            avion1.aterrizar(federicoGarciaLorca, 260);
            System.out.printf("%nAvion1 ha aterrizado%n");
        } catch (IllegalArgumentException | IllegalStateException error) {
            System.out.printf("%nError al aterrizar el Avion1: %s%n", error.getMessage());
        }

        //Modificar velocidad del Avion3 a 950km/h y mostrarlo
        /*
        Para modificar la velocidad utilizamos un método setter(), en este caso setVelocidad y dentro del paréntesis cambiamos
        el parámetro por el número deseado y lo sacamos por pantalla por medio de printf %d porque es un número entero y luego
        un getter, getVelocidad.
        */
        avion3.setVelocidad(950);
        System.out.printf("%nLa nueva velocidad del avion3 es: %d %n", avion3.getVelocidad());

        //Mostrar el nombre del aeropuerto de Madrid
        /*
        Aquí trabajamos directamente con el aeropuerto, usamos printf, usamos %s porque es un String y luego usamos la 
        variable de referencia barajas con el getter .getNombre() para que saque por pantalla el nombre del aeropuerto de 
        Madrid.
        */
        System.out.printf("El nombre del aeropuerto de Madrid es: %s %n", barajas.getNombre());

        //Mostrar el número de aeronaves en este momento en el aeropuerto de Granada
        /*
        De nuevo, trabajamos directamente con una variable de referencia, en este caso federicoGarciaLorca, el cual, sacamos por pantalla
        por medio de printf, %d porque es un entero y luego usaríamos el getter .getNumeroAeronaves() para que nos de el número total.
        */
        System.out.printf("El número de aeronaves volando en este momento en el aeropuerto de Granada: %d %n", federicoGarciaLorca.getNumeroAeronaves());

        //Mostrar toda la información del Avion1
        /*
        Para mostrar toda la información, dentro del javaDoc vemos que en el objeto aeronave, hay un toString que devuelve los objetos
        en un formato mensaje formateado tipo string y esto es lo que tenemos que sacar por pantalla, para ello ponemos printf, usamos
        %s porque lo que vamos a sacar es un tipo String y luego seleccionamos el avión que queramos sacar por pantalla.
        */
        System.out.printf("Avión 1: %s %n", avion1);
        //Mostrar toda la información del Avion2
        System.out.printf("Avión 2: %s%n", avion2);
        //Mostrar toda la información del Avion3
        System.out.printf("Avión 3: %s %n", avion3);

        //----------------------------------------------
        //          Llamadas a métodos estáticos
        //----------------------------------------------
        //CABECERA MÉTODOS ESTÁTICOS
        System.out.println("\n-------------------------");
        System.out.println("----Métodos estáticos----");
        System.out.println("-------------------------\n");
        
        //Mostrar el número total de aeronaves volando ahora mismo
        /*
        Para sacar el número total de aeronaves, vamos a trabajar directamente con la clase Aeronave, usamos printf, usamos%d porque
        vamos a devolver un entero y luego usamos Aeronave con un método getter para ver las aeronaves que hay volando.
        */
        System.out.printf("Número total de aeronaves volando ahora mismo: %d %n", Aeronave.getNumAeronavesVolando());

        //Mostrar el tiempo total de aeronaves volando en horas
        /*
        De nuevo trabajamos directamente con la clase, usamos printf, según javaDoc ahora vamos a sacar por pantalla un flotante
        por lo tanto usaremos %f, luego usaremos la clase y el getter que nos muestra el número de horas de vuelo.
        */
        System.out.printf("Tiempo total de aeronaves en vuelo: %f %n", Aeronave.getNumHorasVuelo());

        //Mostrar el número total de aeronaves
        /*
        Nuevamente volvemos a trabajar directamente con la clase, usamos printf, usamos %d porque según javaDoc vamos a sacar
        por pantalla un entero y luego usamos el método getter para sacar el número de aeronaves que hay creadas.
        */
        System.out.printf("Número total de aeronaves: %d %n", Aeronave.getNumAeronaves());

    }
}
