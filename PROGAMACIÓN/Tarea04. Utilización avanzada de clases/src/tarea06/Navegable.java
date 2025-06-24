package tarea06;

/**
 * Interfaz que integra los métodos iniciar navegación y parar navegación, que va a ser usado por las demás clases.
 * @author María Palomares Gallo
 */
public interface Navegable {
    
    void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes);
    
    void pararNavegacion(float tiempoTotal);

}
