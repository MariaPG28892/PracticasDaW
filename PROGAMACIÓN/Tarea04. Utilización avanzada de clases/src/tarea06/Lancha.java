package tarea06;

// ------------------------------------------------------------
//                   Clase Lancha
// ------------------------------------------------------------
/**
 * Clase instanciable, no heredable que a su vez hereda de la clase Embarcación. Esta clase crea objetos de tipo lancha a la vez
 * que puede devolver información sobre la misma.
 * @author María Palomares Gallo
 */
public final class Lancha extends Embarcacion {

    // ------------------------------------------------------------------------
    // Atributos de la clase
    // ------------------------------------------------------------------------
    /**
     * Establece el <strong>mínimo de motores</strong> de una lancha.
     */
    public static final int MIN_MOTORES = 1;
    /**
     * Establece el <strong>máximo de motores</strong> de una lancha.
     */
    public static final int MAX_MOTORES = 2;
    /**
     * Establece el <strong>mínimo de combustible</strong> de una lancha.
     */
    public static final int MIN_COMBUSTIBLE = 8;
    /**
     * Establece el <strong>máximo de combustible</strong> de una lancha.
     */
    public static final int MAX_COMBUSTIBLE = 50;
    /**
     * Establece el <strong>factor de combustible</strong> de una lancha.
     */
    public static final double FACTOR_COMBUSTIBLE = 0.026;
    /**
     * Establece el <strong>mínimo de velocidad</strong> de una lancha.
     */
    public static final int MIN_VELOCIDAD_LANCHA = 1;
    /**
     * Establece el <strong>máximo de velocidad</strong> de una lancha.
     */
    public static final int MAX_VELOCIDAD_LANCHA = 50;
    /**
     * Establece el <strong>número motores</strong> de una lancha.
     */
    private int numMotores;
    /**
     * Establece el <strong>nivel de combustible</strong> de una lancha.
     */
    private int nivelCombustible;
    /**
     * Establece el <strong>número de lanchas</strong> creadas.
     */
    private static int numLanchas;
    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor de 4 parámetros que hereda de la clase pricipal y que lanza excepciones si los parámetros introducidos no cumplen
     * con sus condiciones
     * @param nombre Parámetro que indica el nombre de la lancha.
     * @param numMotores Parámetro que indica el número de motores de la lancha.
     * @param nivelCombustible Parámetro que indica el nivel de combustible que tiene la lancha.
     * @param tripulantes Parámetro que indica el número de tripulantes de la lancha.
     * @throws IllegalArgumentException Lanza excepciones si el número de motores no alcanza o supera los mínimos y máximos establecidos
     * o si el nivel de combustible no se encuentra entre los límites.
     */
    public Lancha(String nombre, int numMotores, int nivelCombustible, int tripulantes)throws IllegalArgumentException{
        super(nombre, tripulantes);
        
        if(numMotores < MIN_MOTORES || numMotores > MAX_MOTORES){
            throw new IllegalArgumentException("El número de motores debe estar entre "+ MIN_MOTORES+" y "+MAX_MOTORES);
        }
        
        if(nivelCombustible < MIN_COMBUSTIBLE || nivelCombustible > MAX_COMBUSTIBLE){
            throw new IllegalArgumentException("El nivel de combustible debe estar entre "+ MIN_COMBUSTIBLE +" y "+MAX_COMBUSTIBLE);
        }
        
        this.numMotores = numMotores;
        this.nivelCombustible = nivelCombustible;
        //aumentamos el número de lanchas creadas.
        numLanchas++;
    }
    
    /**
     * Constructor sin parámetros, hereda del constructor anterior y establece el nombre de la lancha como algo genérico, se le 
     * asigna el mínimo de motores, el máximo combustile, y el min de tripulantes heredado de embarcación
     */
    public Lancha(){
        this("Lancha"+ (numLanchas + 1), MIN_MOTORES, MAX_COMBUSTIBLE, Embarcacion.MIN_TRIPULANTES);
    }
    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve el número de motores de la lancha.
     * @return Devuelve el número de motores de la lancha.
     */
    public int getNumMotores(){
        return this.numMotores;
    }
    /**
     * Devuelve la cantidad de combustible que tiene la lancha.
     * @return Devuelve la cantidad de combustible que tiene la lancha. 
     */
    public int getCantidadCombustible(){
        return this.nivelCombustible;
    }
    /**
     * Devuelve el número de lanchas creadas.
     * @return Devuelve el número de lanchas creadas. 
     */
    public static int getNumLanchas(){
        return numLanchas;
    }
    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Setter para cambiar el rumbo de la lancha, este lanzará excepciones si los parámetros no cumplen las condiciones que establece 
     * y se heredará de la clase padre.
     * @param nuevoRumbo Párametro que establece el nuevo rumbo que tomará la lancha.
     * @throws NullPointerException Lanzará una excepción si el nuevo rumbo introducido es nulo.
     * @throws IllegalArgumentException Lanzará una excepción si el nuevo rumbo no es ni norte, ni surm ni este ni oeste.
     */
    @Override
    public void setRumbo(String nuevoRumbo)throws NullPointerException, IllegalArgumentException {
        
        
        if(nuevoRumbo == null){
            throw new NullPointerException ("El rumbo no puede ser nulo, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
        }
        
        if (!nuevoRumbo.equalsIgnoreCase("Norte") && !nuevoRumbo.equalsIgnoreCase("Sur") &&
            !nuevoRumbo.equalsIgnoreCase("Este") && !nuevoRumbo.equalsIgnoreCase("Oeste")){
             throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
        }
        //LLamamos a la clase padre.
        super.setRumbo(nuevoRumbo);
    }
    
    
    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método de acción para iniciar la navegación, se le introducen parámetros y lanza excepciones si no cumple con las condiciones.
     * @param velocidad Parámetro que indica la velocidad que lleva la lancha.
     * @param rumbo Parámetro que indica el rumbo que lleva la lancha.
     * @param patron Parámetro que indica el nombre del patrón de la lancha.
     * @param tripulantes Parámetro que inidica el número de tripulantes que tiene la lancha.
     * @throws IllegalStateException Lanza una excepción cuando el combustible no se encuentra entre los límites establecidos.
     * @throws IllegalArgumentException Lanza una excepción cuando la velocidad de la lancha no se encuentra entre los límites establecidos.
     */
    @Override
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes) throws  IllegalStateException, IllegalArgumentException {
        if(this.nivelCombustible > MAX_COMBUSTIBLE || this.nivelCombustible < MIN_COMBUSTIBLE){
            throw new IllegalStateException("La lancha "+this.nombreBarco+" no tiene un nivel de combustible válido para iniciar la navegación (solo "+this.nivelCombustible+ "litros).");
        }
        
        if(velocidad > MAX_VELOCIDAD_LANCHA || velocidad < MIN_VELOCIDAD_LANCHA){
            throw new IllegalArgumentException("La velocidad de navegación de "+velocidad+" nudos asignada a "+this.nombreBarco+" es incorrecta.");
        }
        //Invocamos a la clase padre.
        super.iniciarNavegacion(velocidad, rumbo, patron, tripulantes);
            
    }
    /**
     * Método de acción para parar la lancha, se le introduce un parámetro float con el tiempo total de navegación. No lanza ninguna
     * excepción ni muestra ningún resultado.
     * @param tiempoTotal Parámetro que indica el tiempo toral de navegación de la lancha.
     */
    @Override
    public void pararNavegacion(float tiempoTotal){
        
        
        double combustibleUsadoDouble = (double) this.velocidad * (double) tiempoTotal * FACTOR_COMBUSTIBLE;
        int combustibleUsado = (int) Math.round(combustibleUsadoDouble);
         
        this.nivelCombustible -= combustibleUsado;

        // Evitar valores negativos
        if (this.nivelCombustible < 0) {
            this.nivelCombustible = 0;
        }
        
        //Invocamos a la clase padre.
        super.pararNavegacion(tiempoTotal);
    }
    
    // ------------------------------------------------------------------------
    // Método Abstracto sobreecrito
    // ------------------------------------------------------------------------
    /**
     * Método abstracto heredado de la clase padre que indica lo que señaliza la lancha.
     */
    @Override
    public void señalizar() {
        System.out.println("AVISO de señalización de la lancha " + this.nombreBarco + " con bocinas y luces intermitentes.");
    }
    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    
    /**
     * Método to string que devuelve una cadena con los datos de la lancha, invoca al to String de la clase padre y añade la información
     * propia del objeto lancha.
     * @return devuelve una cadena con los datos de la lancha, invoca al to String de la clase padre y añade la información
     * propia del objeto lancha.
     */
    @Override
    public String toString(){
        
        String resultado;
        resultado = super.toString()+ String.format(", Número de motores: %d, Nivel de combustible: %d",
                this.numMotores,
                this.nivelCombustible);
        return resultado;
    }
    

}
