package tarea06;

// ------------------------------------------------------------
//                   Clase Velero
// ------------------------------------------------------------
/**
 * Clase Velero, instanciable y no heredable por ninguna otra, esta clase hereda de la clase embarcación y usa la interfaz regateable.
 * Se utiliza para crear objetos velero y usar sus métodos implementados.
 * @author María Palomares Gallo.
 */
public final class Velero extends Embarcacion implements Regateable {

    // ------------------------------------------------------------------------
    // Atributos de la clase
    // ------------------------------------------------------------------------
    /**
     * Establece el <strong>número de mástiles</strong> de un velero.
     */
    private int numMastiles;
    /**
     * Establece el <strong>número de veleros creados</strong>.
     */
    private static int numVeleros;
    /**
     * Establece el <strong>mínimo de mástiles</strong> de un velero.
     */
    public static final int MIN_MASTILES = 1;
    /**
     * Establece el <strong>máximo de mástiles</strong> de un velero.
     */
    public static final int MAX_MASTILES = 4;
    /**
     * Establece el <strong>mínimo de velocidad</strong> de un velero.
     */
    public static final int MIN_VELOCIDAD_VELERO = 2;
    /**
     * Establece el <strong>máximo de velocidad</strong> de un velero.
     */
    public static final int MAX_VELOCIDAD_VELERO = 30;
    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor de la clase velero, donde se le pasan los pámetros para crearlo, hereda de la clase padre y lanza excepciones
     * si no se cumplen las condiciones.
     * @param nombre Parámetro que indica el nombre del velero.
     * @param numMastiles Parámetro que indica el número de mástiles que tiene el velero.
     * @param tripulantes Parámetro que indica el número de tripulantes que tiene el velero.
     * @throws IllegalArgumentException  Lanza una excepción si el número de mástiles no se encuentra entre los límites establecidos.
     */
    public Velero(String nombre, int numMastiles, int tripulantes) throws IllegalArgumentException{
        //Se invoca la clase padre.
        super(nombre, tripulantes);
        
        if(numMastiles > MAX_MASTILES || numMastiles < MIN_MASTILES){
            throw new IllegalArgumentException ("El número de mástiles debe estar entre "+MIN_MASTILES+" y "+MAX_MASTILES);
        }
        
        this.numMastiles = numMastiles;
        //Aumenta el número de veleros creados.
        numVeleros++;
    }
    /**
     * Constructor sin parámetros, hereda del constructor anterior y establece el nombre de la lancha como algo genérico, se le 
     * asigna el nombre con el número de velero que le tocaría, el mínimo de mástiles y el mínimo de tripulantes.
     */
    public Velero(){
        this("Velero"+ (numVeleros + 1), MIN_MASTILES, Embarcacion.MIN_TRIPULANTES);
    }
    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve el número de mástiles que tiene la embarcación.
     * @return Devuelve el número de mástiles que tiene la embarcación.
     */
    public int getNumMastiles(){
        return numMastiles;
    }
    
    /**
     * Devuelve el número de veleros creados.
     * @return Devuelve el número de veleros creados.
     */
    public static int getNumVeleros(){
        return numVeleros;
    }
  
    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método setter para cambiar el rumbo que tiene el velero, este introduce el nuevo parámetro y lanza excepciones si no se cumplen
     * las condiciones asignadas.
     * @param nuevoRumbo Parámetro que indica el nuevo rumbo que va a tener el velero.
     * @throws NullPointerException Lanza una excepción si el nuevo rumbo es nulo.
     * @throws IllegalArgumentException Lanza una excepcion si el nuevo rumbo no cumple con las condiciones establecidas.
     */
    @Override
    public void setRumbo(String nuevoRumbo) throws NullPointerException, IllegalArgumentException{
       
        if(nuevoRumbo == null){
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }
        
        if(!nuevoRumbo.equalsIgnoreCase("Ceñida") && !nuevoRumbo.equalsIgnoreCase("Empopada")){
            throw new IllegalArgumentException ("El rumbo no es correcto, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        } 
     
         super.setRumbo(nuevoRumbo);
    }
    
    
    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método de acción que hace que el velero inicie la navegación, donde le pasaremos los parámetros necesarios y lanzará excepciones si no
     * se cumplen las condiciones establecidas.
     * @param velocidad Parámetro que indica la velocidad que va a tomar el velero.
     * @param rumbo Parámetro que indica el rumbo que va a tomar el velero.
     * @param patron Parámetro que indica el nombre del patrón del velero.
     * @param tripulantes Parámetro que indica el número de tripulantes que forman el velero.
     * @throws IllegalArgumentException Lanza una excepción si la velocidad no se encuentra entre los límites establecidos.
     */
    @Override
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes) throws IllegalArgumentException {
        if(velocidad > MAX_VELOCIDAD_VELERO || velocidad < MIN_VELOCIDAD_VELERO){
            throw new IllegalArgumentException("La velocidad de navegación de "+velocidad+" nudos es incorrecta.");
        }
        
        super.iniciarNavegacion(velocidad, rumbo, patron, tripulantes);
    }
    
    /**
     * Método de la interfaz regateable, el cual, inicia una regata entre dos velero introduciendo otro velero competencia.Lanza
     * excepciones si no se cumple las condiciones establecidas y devuelve el barco ganador.
     * @param veleroCompetencia Parámetro que introduce un nuevo objeto velero.
     * @throws NullPointerException Lanza una excepción si el velero introducido es nulo.
     * @throws IllegalStateException Lanza una excepción si el velero no está navegando, si el velero competencia no esta navegando, 
     * si el rumbo de ambos veleros no es el mismo y si no tienen los veleros el mismo número de mástiles.
     * @return devuelve el barco ganador.
     */
    @Override
    public String iniciarRegata(Velero veleroCompetencia)throws NullPointerException,IllegalStateException {
         
        String resultado;
        
        if(veleroCompetencia == null){
            throw new NullPointerException ("El barco con el que se intenta regatear no existe.");
        }
         
        if(!this.isNavegando){
            throw new IllegalStateException ("No se puede iniciar la regata, el barco "+this.nombreBarco+" no está navegando");
        }
        
        if(!veleroCompetencia.isNavegando){
            throw new IllegalStateException ("No se puede iniciar la regata, el barco "+veleroCompetencia.nombreBarco+" no está navegando");
        }
        
        if(!this.rumbo.equalsIgnoreCase(veleroCompetencia.rumbo)){
            throw new IllegalStateException ("No se puede iniciar la regata, los barcos "+this.nombreBarco+" y "+veleroCompetencia.nombreBarco+" deben navegar con el mismo rumbo");
        }
        
        if(this.numMastiles != veleroCompetencia.numMastiles){
            throw new IllegalStateException ("No se puede iniciar la regata, los barcos "+this.nombreBarco+" y "+veleroCompetencia.nombreBarco+" no tienen el mismo número de mástiles.");
        }
        
        //Comprobamos cual de los dos veleros ha ganado la regata y lo devolvemos en la cadena resultado.
        if(this.velocidad > veleroCompetencia.velocidad){
            resultado = "\nEl barco "+this.nombreBarco+" ha llegado antes a la línea de llegada.\n";
        }else if(this.velocidad< veleroCompetencia.velocidad){
            resultado = "\nEl barco "+veleroCompetencia.nombreBarco+" ha llegado antes a la línea de llegada.\n";
        }else{
            resultado ="\nLos barcos "+this.nombreBarco+" y "+veleroCompetencia.nombreBarco+" han llegado a la vez a la línea de llegada.\n";
        }
        
        return resultado;
     }
    // ------------------------------------------------------------------------
    // Método Abstracto sobreecrito
    // ------------------------------------------------------------------------
    /**
     * Método abstracto heredado de la clase padre, el cual, devuelve la señalización que tienen los veleros.
     */
    @Override
    public void señalizar() {
        System.out.println("AVISO de señalización de la lancha " + this.nombreBarco + " con banderas de señalización marítima.");
    }
    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    
    /**
     * Método to string que devuelve una cadena con la información heredada de la clase padre, junto a la información de la clase
     * velero.
     * @return devuelve una cadena con la información heredada de la clase padre, junto a la información de la clase
     * velero.
     */
    @Override
    public String toString(){
        
        String resultado;
        resultado = super.toString()+ String.format(", Número de mástiles: %d ",
                this.numMastiles);
        return resultado;
    }
    
}
