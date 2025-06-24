package tarea06;

// ------------------------------------------------------------
//                   Clase Embarcacion
// ------------------------------------------------------------
/**
 *Clase abstracta que representa un <Strong>Embarcación</Strong> para la gestón de lanchas y veleros.
 * <p>Al tratarse de una clase abstracta quiere decir que es una clase que no puede crear un objeto por si sola, pero si 
 * que puede ser una base, para otras clases que lo crean, creando unas pautas.</p>
 * @author María Palomares Gallo
 */
public abstract class Embarcacion implements Navegable {

    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (inmutables)
    // Pueden ser accedidos desde fuera de la clase
    // ------------------------------------------------------------------------
 
        /**
         * Establece el <strong>patrón por defecto</strong> que sería "Sin patrón".
         */
        public static final String PATRON_POR_DEFECTO = "Sin patrón";
        /**
         * Establece el <strong>rumbo por defecto</strong> que sería "Sin rumbo".
         */
        public static final String RUMBO_POR_DEFECTO = "Sin rumbo";
        /**
         * Establece el <strong>mínimo de tripulantes</strong> que tendría cada embarcación sin contar el patrón.
         */
        public static final int  MIN_TRIPULANTES = 0;
    // ------------------------------------------------------------------------
    // Atributos estáticos privados (mutables)
    // No dependen de instancias de objetos particulares y sólo pueden 
    // modificarse desde la propia clase
    // ------------------------------------------------------------------------
        /**
         * Atributo privado estático que va contando el número de embarcaciones que se van creando.
         */
        private static int numBarcos = 0;
        /**
         * Atributo privado estático que va contando el número de embarcaciones navegando que existen.
         */
        private static int numBarcosNavegando = 0;
        /**
         * Atributo privado estático que va contando el tiempo total de todos los barcos.
         */
        private static float tiempoTotalTodosBarcos= 0;
    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // Representan el estado del objeto pero no pueden cambiar su valor
    // ------------------------------------------------------------------------
        /**
         * Atributo de objeto inmutable privado donde se almacena <strong>el número máximo de tripulantes</strong> que tiene una embarcación.
         */
        private int numMaxTripulantes;
    // ------------------------------------------------------------------------
    // Atributos de objeto variables (privados)
    // Representan el estado del objeto y pueden cambiar su valor
    // ------------------------------------------------------------------------
        /**
         * Atributo de objeto variable que almacena <strong>el nombre del barco</strong> como se hereda, se usa protected.
         */
        protected String nombreBarco; //Como tiene que heredarse para usarse en algunas partes de las excepciones lo he puesto proteted aunque ponía private
        /**
         * Atributo de objeto variable que almacena <strong>si la embarcación esta navegando</strong> como se hereda, se usa protected.
         */
        protected boolean isNavegando = false;
        /**
         * Atributo de objeto variable que almacena <strong>la velocidad de la embarcación</strong> como se hereda, se usa protected.
         */
        protected int velocidad;
        /**
         * Atributo de objeto variable que almacena <strong>el nombre del patrón</strong> como se hereda, se usa protected.
         */
        protected String patron;
        /**
         * Atributo de objeto variable que almacena <strong>el rumbo del barco</strong> como se hereda, se usa protected.
         */
        protected String rumbo;
        /**
         * Atributo de objeto variable que almacena <strong>el número de tripulantes del barco</strong> como se hereda, se usa protected.
         */
        protected int tripulantes;
        /**
         * Atributo de objeto variable que almacena <strong>el tiempo total del barco navegando</strong> como se hereda, se usa protected.
         */
        protected float tiempoTotal;
        
    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
        /**
         * Constructor de la clase Embarcación donde comprobaremos los parámetros introducidos y los asignaremos a los atributos corresponidientes.
         * @param nombre nombre de la embarcación, la almacenamos en this.nombreBarco
         * @param numMaxTripulantes el número máximo de tripulantes que componen la embarcación, la almacenamos en this.numMaxTripulantes
         * @throws NullPointerException Se lanza una excepción si el nombre de la embarcación es nulo
         * @throws IllegalArgumentException Se lanza esta excepción si el nombre de la embarcación está vacío o el numero máximo de triplantes es negativo.
         */
        public Embarcacion(String nombre, int numMaxTripulantes) throws NullPointerException, IllegalArgumentException {
            if(nombre == null){
                throw new NullPointerException ("El nombre de la embarcación no puede ser nulo");
            }
            if(nombre.isEmpty()){
                throw new IllegalArgumentException ("El nombre de la embarcación no puede estar vacío");
            }
            if(numMaxTripulantes < 0){
                throw new IllegalArgumentException("El número mínimo de tripulantes debe ser de al menos " + MIN_TRIPULANTES);
            }
            
            this.nombreBarco = nombre;
            this.numMaxTripulantes = numMaxTripulantes;
            //También actualizamos los demás parametros con lo que corresponda.
            this.isNavegando = false;
            this.velocidad = 0;
            this.patron = PATRON_POR_DEFECTO;
            this.rumbo = RUMBO_POR_DEFECTO;
            this.tripulantes = 0;
            this.tiempoTotal = 0;
            //Aumentamos el número de barcos que se van creando.
            numBarcos++;
        }
    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
        /**
         * Devuelve el <Strong>nombre</Strong> del barco.
         * @return devuelve el nombre del barco
         */
        public String getNombreBarco(){
            return this.nombreBarco;
        }
        /**
         * Devuelve el <Strong>el número máximo de tripulantes</Strong> del barco.
         * @return devuelve el máximo de tripulantes del barco
         */
        public int getMaxTripulantes(){
            return this.numMaxTripulantes;
        }
        /**
         * Devuelve <Strong>si está navegando</Strong> el barco.
         * @return devuelve si el barco está navegando
         */
        public boolean isNavegando(){
            return this.isNavegando;
        }
        /**
         * Devuelve la <Strong>velocidad</Strong> del barco.
         * @return devuelve la velocidad del barco
         */
        public int getVelocidad(){
            return this.velocidad;
        }
        /**
         * Devuelve el <Strong>rumbo</Strong> del barco.
         * @return devuelve el rumbo del barco
         */
        public String getRumbo(){
            return this.rumbo;
        }
        /**
         * Devuelve el <Strong>nombre del patrón</Strong> del barco.
         * @return devuelve el nombre del patrón del barco
         */
        public String getPatron(){
            return this.patron;
        }
        /**
         * Devuelve el <Strong>número de tripulantes</Strong> del barco.
         * @return 
         */
        public int getTripulacion(){
            return this.tripulantes;
        }
        /**
         * Devuelve el <Strong>tiempo total de navegación</Strong> del barco.
         * @return 
         */
        public float getTiempoTotalNavegacionBarco(){
            return this.tiempoTotal;
        }
        /**
         * Devuelve el <Strong>número de barcos</Strong> creados.
         * @return 
         */
        public static int getNumBarcos(){
            return numBarcos;
        }
        /**
         * Devuelve el <Strong>número de barcos navegando</Strong>.
         * @return 
         */
        public static int getNumBarcosNavegando(){
            return numBarcosNavegando;
        }
        /**
         * Devuelve el <Strong>tiempo total del navegación de todos los barcos</Strong>.
         * @return 
         */
        public static float getTiempoTotalNavegacion(){
            return tiempoTotalTodosBarcos;
        }
    // ------------------------------------------------------------------------
    // Métodos estáticos (acceden a los atributos estáticos de la clase)
    // ------------------------------------------------------------------------
        /**
         * Método setter para cambiar el rumbo de la embarcación, el cual, lanzará excepciones si el parámetro de nuevo rumbo no es correcto.
         * @param nuevoRumbo parámetro que indica el nuevo rumbo que quiere tomar la embarcación.
         * @throws IllegalStateException lanzará excepciones cuando el barco no este navegando y se quiera cambiar el rumbo y si el rumbo introducido es igual
         * que el rumbo que ya tenía el barco.
         */
        public void setRumbo(String nuevoRumbo)throws IllegalStateException {
            if(!isNavegando){
                throw new IllegalStateException("La embarcación "+this.nombreBarco+" no está navegando, no se puede cambiar el rumbo");
            }
            
            if(nuevoRumbo.equalsIgnoreCase(this.rumbo)){
                throw new IllegalStateException ("La embarcación "+this.nombreBarco+ " ya está navegando con ese rumbo "+nuevoRumbo + ", debes indicar un rumbo distinto para poder modificarlo.");
            }
            
            this.rumbo = nuevoRumbo;
        }
    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
        /**
         * Método de acción para iniciar la navegación, está sobreescrito porque se hereda de la interfaz navegable, por lo tanto usamos override.
         * @param velocidad parámetro que indica la velocidad a la que se inicia la navegación del embarcación.
         * @param rumbo parámetro que indica el rumbo que tomará la embarcación.
         * @param patron parámetro que indica el nombre del patrón de la embarcación.
         * @param tripulantes parámetro que indice el número de tripulantes que tiene la embarcación.
         * @throws IllegalStateException Lanza una excepción si el barco quiere iniciar navegación, pero ya está navegando.
         * @throws NullPointerException Lanza excepción si el rumbo es nulo o esta vacío o si el patrón está vacío.
         * @throws IllegalArgumentException Lanza excepción si los tripulantes son menos que el minimo establecido.
         */
        @Override
        public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes)throws IllegalStateException, NullPointerException, IllegalArgumentException{
            if(isNavegando){
                throw new IllegalStateException ("La embarcación " +this.nombreBarco+" está navegando y se encuentra fuera del puerto");
            }
            
            if(rumbo == null){
                throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo para iniciar la navegación.");
            }
            
            if(rumbo.isEmpty()){
                throw new NullPointerException("El rumbo no puede estar vacío, debes indicar el rumbo para iniciar la navegación.");
            }
            
            if(patron == null){
                throw new NullPointerException("El patrón no puede ser nulo, se necesita un patrón para iniciar la navegación.");
            }
            
            if(patron.isEmpty()){
                throw new NullPointerException ("El patrón de la embarcación no puede estar vacío, se necesita un patrón para iniciar la navegación.");
            }
            
            if(tripulantes < MIN_TRIPULANTES){
                throw new IllegalArgumentException("El número de tripulantes debe estar entre "+MIN_TRIPULANTES+" y "+ this.numMaxTripulantes);
            }
            
            this.isNavegando = true;
            this.velocidad = velocidad;
            this.rumbo = rumbo;
            this.patron = patron;
            this.tripulantes = tripulantes;
            //Aumentamos los barcos que están navegando.
            numBarcosNavegando++;
            
        }
        
        /**
         * Método de acción para parar la navegación, se hereda de la interfaz navegable, por lo que la sobreescribimos.
         * @param tiempoTotal parámetro que indica el tiempo total navegando del barco.
         * @throws IllegalStateException Lanza una excepción si el barco quiere detenerse y no está navegando o si el tiempo introducido es negativo.
         */
        @Override
        public void pararNavegacion(float tiempoTotal) throws IllegalStateException {
            if(!isNavegando){
                 throw new IllegalStateException ("La embarcación " +this.nombreBarco+" no está navegando");
            }
            
            if(tiempoTotal < 0){
                throw new IllegalStateException ("Tiempo navegando incorrecto, debe ser mayor que cero");
            }
            
            this.isNavegando = false;
            this.tiempoTotal += tiempoTotal;
            this.velocidad = 0;
            this.patron = PATRON_POR_DEFECTO;
            this.rumbo = RUMBO_POR_DEFECTO;
            this.tripulantes = 0;
            tiempoTotalTodosBarcos += tiempoTotal;
            //Restamos barcos navegando
            numBarcosNavegando--;
            
        }
        
        
    // ------------------------------------------------------------------------
    // Método Abstracto (sin implementación)
    // ------------------------------------------------------------------------
        /**
         * Método abstracto para heredar las demás clases.
         */
        public abstract void señalizar();
    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método toString, devuelve una cadena con la información de la embarcación.
     * @return devuelve una cadena con la información de la embarcación.
     */
    @Override
    public String toString(){
        String estadoNavegacion = isNavegando ? "Sí" : "No";
        String resultado;
        String infoNavegacion = "";

        if (isNavegando) {
            infoNavegacion = String.format("con el patrón %s en %s a %d nudos,", 
                                        this.patron,
                                        this.rumbo,
                                        this.velocidad);
                                       
        }
        
        resultado = String.format("Nombre de la embarcación: %s, Tripulación: %d, Navegando: %s, %s Tiempo total de navegación del barco: %.2f horas", 
                            this.nombreBarco, 
                            this.tripulantes, 
                            estadoNavegacion, 
                            infoNavegacion, 
                            this.tiempoTotal/60);
        
        return resultado;
    }
}
