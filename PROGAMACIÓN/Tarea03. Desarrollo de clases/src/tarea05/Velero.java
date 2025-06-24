package tarea05;

// ------------------------------------------------------------
//                   Clase Velero
// ------------------------------------------------------------
/**
 * <strong>La clase Velero:</strong>
 * Clase que nos aporta información sobre el objeto velero, así como sus dos tipos de contructores, sus getters, sus setters y 
 * sus métodos de acción. Por último acaba con un toString donde se sacan por pantalla todos los datos recogidos.
 *
 * @author María Palomares Gallo.
 */
public class Velero {
    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (inmutables)
    // Pueden ser accedidos desde cualquier caso
    // ------------------------------------------------------------------------
    /**
     * Atributo público estático final que nos aporta el <strong>mínimo de mástiles</strong> que puede tener un Velero.
     * {@value MIN_MASTILES} mástiles.
     */
    public static final int MIN_MASTILES = 1;
    /**
     * Atributo público estático final que nos aporta el <strong>máximo de mástiles</strong> que puede tener un Velero.
     * {@value MAX_MASTILES} mástiles.
     */
    public static final int MAX_MASTILES = 4;
    /**
     * Atributo público estático final que nos aporta la <strong>velocidad mínima</strong> permitido para un velero en nudos.
     * {@value MIN_VELOCIDAD} nudos.
     */
    public static final int MIN_VELOCIDAD = 2;
    /**
     * Atributo público estático final que nos aporta la <strong>velocidad máxima</strong> permitido para un velero en nudos.
     * {@value MAX_VELOCIDAD} nudos.
     */
    public static final int MAX_VELOCIDAD = 30;
    /**
     * Atributo público estático final que define el <strong>nombre del patrón por defecto</strong> durante la navegación.
     * {@value PATRON_POR_DEFECTO}.
     */
    public static final String PATRON_POR_DEFECTO = "Sin Patrón";
    /**
     * Atributo público estático final que define el <strong>rumbo por defecto</strong> durante la navegación.
     * {@value RUMBO_POR_DEFECTO} 
     */
    public static final String RUMBO_POR_DEFECTO = "Sin Rumbo";
    /**
     * Atributo público estático final que define el <strong>mínimo de tripulantes</strong> durante la navegación, excluyendo el patrón.
     * {@value MIN_TRIPULANTES} tripulantes.
     */
    public static final int MIN_TRIPULANTES = 0; 
    // ------------------------------------------------------------------------
    // Atributos estáticos privados (mutables)
    // No dependen de instancias de objetos particulares y sólo pueden 
    // modificarse desde la propia clase
    // ------------------------------------------------------------------------
    /**
     * Atributo privado estático que determina el <strong>número de barcos creados</strong> hasta el momento.
     */
    private static int numeroBarcos;
    /**
     * Atributo privado estático que determina el <strong>número de barcos que están navegando.</strong>
     */
    private static int barcosNavegando;
    /**
     * Atributo privado estático que determina el <strong>tiempo total de navegación</strong> que acumulan <strong>todos los barcos.</strong>
     */
    private static float tiempoNavegandoTodosBarcos;

    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // Representan el estado del objeto pero no pueden cambiar su valor
    // ------------------------------------------------------------------------
    /**
     * Atributo privado final que establece el <strong>nombre</strong> del velero.
     */
    private final String nombre;
    /**
     * Atributo privado final que establece el <strong>número de mástiles</strong> del velero.
     */
    private final int numeroMastiles;
    /**
     * Atributo privado final que establece el <strong>número máximo de tripulantes</strong> que admite el velero.
     */
    private final int maximoTripulantes;
    // ------------------------------------------------------------------------
    // Atributos de objeto variables (privados)
    // Representan el estado del objeto y pueden cambiar su valor
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // Atributos del estado del barco
    // ------------------------------------------------------------------------
    // Representan el estado básico del barco en un momento dado
    // ------------------------------------------------------------------------
    /**
     * Atributo privado que define si un velero <strong>está navegando</strong> en ese momento o no.
     */
    private boolean isNavegando;
    /**
     * Atributo privado que define el tiempo total de navegación de <strong>cada velero.</strong>
     */
    private int tiempoTotalNavegacion;

    // ------------------------------------------------------------------------
    // Atributos de la información de navegación
    // ------------------------------------------------------------------------
    // Almacenan información sobre los parámetros de navegación
    // ------------------------------------------------------------------------
    /**
     * Atributo privado que define la <strong>velocidad</strong> a la que va el velero.
     */
    private int velocidad;
    /**
     * Atributo privado que define el <strong>nombre del patrón</strong> del velero.
     */
    private String nombrePatron;
    /**
     * Atributo privado que define el <strong>rumbo</strong> que toma el velero durante la navegación.Puede ser ceñida o empopada.
     */
    private String rumbo;
    /**
     * Atributo privado que define el <strong>número de tripulantes</strong> del velero durante la navegación.
     */
    private int numeroTripulantes = 0;

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    // Constructor con tres parámetros
    /**
     * Este constructor crea un objeto Velero con tres parámetros, se llama igual que la clase por ser el constructor.
     * @param nombre: Nombre del velero.
     * @param numeroMastiles: Número de mátiles que tiene el velero.
     * @param maximoTripulantes: Máximo de tripulantes que admite el velero.
     * @throws IllegalArgumentException  Se lanza una excepción cuando el nombre es nulo o es una cadena vacía, cuando el número de mástiles
     * es inferior al mínimo o superior al máximo permitido. Cuando el máximo de tripulantes es menor que 0.
     */
    public Velero (String nombre, int numeroMastiles, int maximoTripulantes) throws IllegalArgumentException
    {
        if(nombre == null){
            throw new IllegalArgumentException("El nombre del velero no puede ser nulo.");
        }else if(nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre del velero no puede estar vacío.");
        }
        
        if(numeroMastiles < Velero.MIN_MASTILES || numeroMastiles > Velero.MAX_MASTILES){
            throw new IllegalArgumentException("El número de mástiles debe de estar entre 1 y 4.");
        }
        
        if(maximoTripulantes < Velero.MIN_TRIPULANTES){
            throw new IllegalArgumentException ("El número de tripulantes debe ser mínimo 1.");
        }
        
        //Asignamos los atributos a los parámetros utilizando this.
        this.nombre = nombre;
        this.numeroMastiles = numeroMastiles;
        this.maximoTripulantes = maximoTripulantes;
        /*
        Aunque estos no se han pasado por parámetro, hay que inicializarlos, decir que no están navegando, cuando se crean, cual es el 
        tiempo de navegación y la velocidad es 0, que no tienen aún un patrón asignado, ni un rumbo y que el número de tripulantes es 
        el mínimo establecido.
        */
        this.isNavegando = false;  
        this.tiempoTotalNavegacion = 0;  
        this.velocidad= 0;  
        this.nombrePatron = PATRON_POR_DEFECTO;  
        this.rumbo = RUMBO_POR_DEFECTO;  
        this.numeroTripulantes = MIN_TRIPULANTES; 
        
        //Numéro barcos irá contando los barcos que se van creando cada vez que se utilice este constructor o el sin parámetros.
        numeroBarcos++;
    }
    // Constructor por defecto
    /**
     * Constructor por defecto, es un contructor sin parámetros, el cual, va a coger los parámetros por omisión del constructor con más
     * parámetros para lograr crear un barco. Tenemos que utilizar el this(), para que utilice del constructor anterior los parámetros.
     */
    public Velero()
    {
        this("Velero " + (numeroBarcos+1) , MIN_MASTILES, MIN_TRIPULANTES);
    }     
    // Método fábrica
    /**
     * Un método de fábrica para crear un array donde se acumularán las referencias a los objetos velero creados, se almacenará
     * cada objeto creado por parámetros por omisión.
     * @param numeroBarcos: Número de barcos para crear el array, debe de estar entre 1 y 10.
     * @return los veleros creados con sus parámetros por omisión. 
     */
    public static Velero[] crearArrayVelero (int numeroBarcos)
    {
        if(numeroBarcos < 1 || numeroBarcos > 10){
            throw new IllegalArgumentException ("Número de barcos incorrecto " + numeroBarcos+", debe ser mayor o igual que 1 y menor o igual que 10");
        }
        
        Velero[] veleros = new Velero [numeroBarcos];
        for(int i = 0; i<numeroBarcos; i++){
            veleros[i] = new Velero();
        }
        
        return veleros;
    }
    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Informa del <strong>nombre</strong> del velero.
     * @return Devuelve el nombre del barco. 
     */
    public String getNombreBarco(){
        return this.nombre;
    }
    /**
     * Informa del <strong>número de barcos</strong> que componen el velero.
     * @return Devuelve el número de mástiles que tiene el velero.
     */
    public int getNumMastiles(){
        return this.numeroMastiles;
    }
    /**
     * Informa del <strong>máximo de tripulantes</strong> que admite el velero.
     * @return Delvuelve el número máximo de tripulantes que admite el velero.
     */
    public int getMaxTripulantes(){
        return this.maximoTripulantes;
    }
    
    /**
     * Informa de la información de si el velero <strong>se encuentra o no navegando</strong> en ese momento.
     * @return informa de si el velero se encuentra navegando o no.
     */
    public boolean isNavegando(){
        return this.isNavegando;
    }
    /**
     * Informa de la información del <strong>tiempo total</strong> que se encuentra el velero navegando en minutos.
     * @return devuelve el tiempo total de la navegación del velero.
     */
    public int getTiempoTotalNavegacionBarco(){
        return this.tiempoTotalNavegacion;
    }
    /**
     * Informa de la <strong>velocidad</strong> del velero durante la navegación.
     * @return Devuelve la velocidad del velero. 
     */
    public int getVelocidad(){
        return this.velocidad;
    }
    /**
     * Informa del <strong>rumbo</strong> que tiene el velero durante la navegación. 
     * @return Devuelve el rumbo del velero.
     */
    public String getRumbo(){
       
        return this.rumbo;
    }
    /**
     * Informa del <strong>nombre del patrón</strong> del velero durante la navegación.
     * @return Devuelve el nombre del patrón del velero.
     */
    public String getPatron(){
       
        return this.nombrePatron;
    }
    /**
     * Informa del <strong>número de tripulantes</strong> que tiene el velero durante la navegación.
     * @return Devuelve el número de tripulantes que tiene el velero.
     */
    public int getTripulacion(){
        return this.numeroTripulantes;
    }
    // ------------------------------------------------------------------------
    // Métodos getter estáticos (acceden a los atributos estáticos de la clase)
    // ------------------------------------------------------------------------
    /**
     * Informa del <strong>número de veleros que se han creado.</strong>
     * @return Devuelve el número de veleros que se han creado.
     */
    public static int getNumBarcos(){
        return numeroBarcos;
    }
    /**
     * Informa del<strong> número de veleros que se encuentran navegando.</strong>
     * @return Devuelve el número de veleros que están navegando.
     */
    public static int getNumBarcosNavegando(){
        return barcosNavegando;
    }
    /**
     * Informa del <strong>tiempo de navegación total de todos los veleros</strong> en minutos.
     * @return Devuelve el tiempo de navegación total de todos los veleros en minutos.
     */
    public static float getTiempoTotalNavegacion(){
        return tiempoNavegandoTodosBarcos;
    }
      
    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método para <strong>cambiar el rumbo del barco</strong> durante la navegación. Este método no devuelve nada, solo cambia el rumbo, a no ser que tenga
     * que mostrar excepciones.
     * @param nuevoRumbo Es el nuevo rumbo al que queremos cambiar el velero, solo puede ser ceñida o empopada.
     * @throws NullPointerException Lanza una excepción si el nuevo rumbo introducido es nulo.
     * @throws IllegalArgumentException Lanza una excepción si el nuevo rumbo es una cadena vacía o no es igual a ceñida o empopada.
     * @throws IllegalStateException Laznza una excepción si el velero no está navegando o si el rumbo introducido es el mismo que el nuevo.
     */
    public void setRumbo(String nuevoRumbo)throws NullPointerException, IllegalArgumentException, IllegalStateException{
        if(!isNavegando){
            throw new IllegalStateException("El velero "+nombre+" no está navegando, no se puede cambiar el rumbo.");
        }
        
        if(nuevoRumbo == null){
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }
        
        //Utilizo el ignoreCase para que no tenga en cuenta las mayúsculas y las minúsculas.
        if(nuevoRumbo.isEmpty() || (!nuevoRumbo.equalsIgnoreCase("ceñida") && !nuevoRumbo.equalsIgnoreCase("empopada"))){
            throw new IllegalArgumentException ("El rumbo no es correcto, debes indicar el rumbo (ceñida o empopada) para poder modificarlo."); 
        }
        
        if(nuevoRumbo.equalsIgnoreCase(rumbo)){
            throw new IllegalStateException("El velero " + nombre + " ya está navegando con ese rumbo (" + rumbo + "), debes indicar un rumbo distinto para poder modificarlo.");
        }
        //Establecemos el nuevo rumbo como el rumbo del velero.
        this.rumbo = nuevoRumbo;
    }
    
    
    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método de acción que <strong>inicia la navegación</strong> de los veleros. Es un método que no devuelve nada, cambia el estado de isNavegando a 
     * <code>true</code> si el velero inicia la navegación. También lanza excepciones en el caso de que sea necesario.
     * @param velocidad La velocidad que tiene el velero.
     * @param rumbo Rumbo que tiene asignado el velero.
     * @param nombrePatron Nombre del patrón del velero.
     * @param numeroTripulantes Número de tripulantes que tiene el velero.
     * @throws IllegalStateException Lanza una excepcion cuando la velocidad es incorrecta o cuando el velero ya está navegando.
     * @throws NullPointerException Lanza una excepción cuando el rumbo, el nombre del patrón es nulo o este último es una cadena vacía.
     * @throws IllegalArgumentException Lanza una excepción cuando el número de tripulantes no se encuentra en los parámetros establecidos.
     */
    public void iniciarNavegacion(int velocidad, String rumbo, String nombrePatron, int numeroTripulantes) throws IllegalStateException, NullPointerException, IllegalArgumentException
    {
        if(velocidad< Velero.MIN_VELOCIDAD || velocidad > Velero.MAX_VELOCIDAD){
            throw new IllegalStateException ("La velocidad de navegación de "+velocidad+" nudos es incorrecta.");
        }
        
        if(isNavegando){
            throw new IllegalStateException ("El velero "+nombre+" ya está navegando y se encuentra fuera del puerto");
        }
        
        if(rumbo == null){
            throw new NullPointerException ("El rumbo no puede ser nulo, debes indicar el rumbo para iniciar la navegación.");
        }
        
        if(nombrePatron == null){
            throw new NullPointerException ("El patrón del barco no puede ser nulo, se necesita un patrón para iniciar la navegación.");
        }else if(nombrePatron.isEmpty()){
             throw new NullPointerException("El patrón del barco no puede estar vacío, se necesita un patrón para iniciar la navegación.");
        }
        
        if(numeroTripulantes < Velero.MIN_TRIPULANTES || numeroTripulantes > maximoTripulantes){
            throw new IllegalArgumentException("El número de tripulantes debe estar entre "+Velero.MIN_TRIPULANTES+" y "+maximoTripulantes);
        }
        
        this.velocidad = velocidad;
        this.rumbo = rumbo;
        this.nombrePatron = nombrePatron;
        this.numeroTripulantes = numeroTripulantes;
        //Cambiamos el estado a true si comienza a navegar. 
        this.isNavegando = true;
       
        //Vamos acumulando en esta variable los barcos que están navegando.
        barcosNavegando++;
    }
    
    /**
     * Método de acción que se encarga de <strong>parar la navegación.</strong> Este método no devuelve nada, solo cambia el estado de la navegación
     * a <code>false</code>. Aunque lanza excepciones cuando es necesario.
     * @param tiempoNavegando El tiempo navegando del velero, para luego calcular el total de navegación del velero y sumarlo al total de todos
     * los veleros.
     * @throws IllegalStateException Lanza una excepción cuando el velero no está navegando.
     * @throws IllegalArgumentException Lanza una excepción cuando el tiempo de navegación es menor de 0.
     */
    public void pararNavegacion(float tiempoNavegando)throws IllegalStateException,IllegalArgumentException{
        if(!isNavegando){
            throw new IllegalStateException("El velero "+nombre+" no está navegando");
        }
        
        if(tiempoNavegando < 0){
            throw new IllegalArgumentException("Tiempo navegando incorrecto, debe ser mayor que cero.");
        }
        
        // Sumar el tiempo total de navegación del barco.
        this.tiempoTotalNavegacion += tiempoNavegando;

        // Sumar al tiempo total de navegación global de todos los barcos.
        Velero.tiempoNavegandoTodosBarcos += tiempoNavegando;  

        //Cambiamos a false, para que el velero no este navegando.
        this.isNavegando = false;
        //Restablecemos los valores a 0 o por defecto para resetearlos.
        this.velocidad = 0;   
        this.rumbo = Velero.RUMBO_POR_DEFECTO;       
        this.nombrePatron = Velero.PATRON_POR_DEFECTO;  
        this.numeroTripulantes = 0; 
        
        //Restamos a los barcos navegando cuando establecemos el false.
        barcosNavegando--;
    }
    /**
     * Método de acción que devolverá cual de los barcos que está compitiendo en la regata es el <strong>ganador o si es empate</strong> o lanzará una excepción si es el caso.
     * @param veleroCompetencia Es un objeto velero con todas sus atributos dentro que iremos comparando con el actual para hacer la regata.
     * @return Devolverá cuál de los veleros es el ganador o si hay empate entre ambos.
     * @throws NullPointerException Lanza una excepción si el velero introducido por parámetro es nulo.
     * @throws IllegalStateException Lanza una excepción si el velero que tenemos o el introducido por parámetros no está navegando, o si el rumbo
     * de ambos no es el mismo o no tienen el mismo número de mástiles.
     */
    public String iniciarRegata(Velero veleroCompetencia) throws NullPointerException,IllegalStateException{
        
        //creamos una variable para almacenar el resultado.
        String resultado;
        
        if(veleroCompetencia == null){
            throw new NullPointerException ("El barco con el que se intenta regatear no existe.");
        }
        
        if(!this.isNavegando){
            throw new IllegalStateException ("No se puede iniciar la regata, el barco "+nombre+" no está navegando");
        }
        
        if(!veleroCompetencia.isNavegando){
            throw new IllegalStateException ("No se puede iniciar la regata, el barco "+veleroCompetencia.nombre+" no está navegando");
        }
        //uso ignoreCase para que no haya diferenciación de mayúsculas o minúsculas.
        if(!this.rumbo.equalsIgnoreCase(veleroCompetencia.rumbo)){
            throw new IllegalStateException ("No se puede iniciar la regata, los barcos "+nombre+" y "+veleroCompetencia.nombre+" deben navegar con el mismo rumbo");
        }
        
        if(this.numeroMastiles != veleroCompetencia.numeroMastiles){
            throw new IllegalStateException ("No se puede iniciar la regata, los barcos "+nombre+" y "+veleroCompetencia.nombre+" no tienen el mismo número de mástiles.");
        }
        
        /*
        Aquí es donde elegimos el ganador, hacemos un if-else anidado, donde si el velero que tenemos para el cual siempre vamos a usar this
        y para el otro veleroCompetencia, según el que tenga más velocidad gana, en el caso de que la velocidad sea la misma sería un empate.
        Todo lo almacenamos como una cadena de texto en la variable resultado, que será lo que luego saquemos por return.
        */
        if(this.velocidad > veleroCompetencia.velocidad){
            resultado = "\nEl barco "+nombre+" ha llegado antes a la línea de llegada.\n";
        }else if(this.velocidad< veleroCompetencia.velocidad){
            resultado = "\nEl barco "+veleroCompetencia.nombre+" ha llegado antes a la línea de llegada.\n";
        }else{
            resultado ="\nLos barcos "+nombre+" y "+veleroCompetencia.nombre+" han llegado a la vez a la línea de llegada.\n";
        }
        
        return resultado;
    }
    
    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método toString que <strong>imprime el estado del velero</strong>. Dentro se cambiará para que en lugar de verdadero o falso, indique si o no en 
     * el caso de si está navegando, y en el caso de estar navegando mostrará la información de la navegación.
     * @return Devuelve el estado del velero.
     */
    @Override
    public String toString() {
        // Se obtiene el estado de navegación y se cambia a si o no. Lo he hecho con operador terciario que me parecía menos código.
        String estadoNavegacion = isNavegando ? "Sí" : "No";
        //Variable para almacenar la información de la navegación en el caso de estar navegando.
        String infoNavegacion = "";

        //Lo añadimos al toString final solamente si el velero se encuentra navegando.
        if (isNavegando) {
            infoNavegacion = String.format("con el patrón %s en %s a %d nudos", 
                                        nombrePatron,
                                        rumbo,
                                        velocidad);
        }

        // Se calcula el tiempo de navegación en horas.
        float tiempoEnHoras = tiempoTotalNavegacion / 60.0f;

        // Usamos String.format para generar la cadena final y devolverla por return.
        return String.format("{Nombre del barco: %s, Número de mástiles: %d, Tripulación: %d, Navegando: %s, %s, Tiempo total de navegación del barco: %.2f horas}", 
                            nombre, 
                            numeroMastiles, 
                            numeroTripulantes, 
                            estadoNavegacion, 
                            infoNavegacion, 
                            tiempoEnHoras);
   }
}