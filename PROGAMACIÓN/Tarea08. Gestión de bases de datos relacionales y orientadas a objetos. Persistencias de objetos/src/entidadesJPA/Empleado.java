
package entidadesJPA;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entidad JPA que representa una sección dentro de la tienda o supermercado.
 * 
 * <p>Una sección puede contener empleados. Cada sección tiene un
 * identificador único {@code idEmpleado} y una descripción asociada.</p>
 * @author María Palomares Gallo
 */
@Entity
public class Empleado implements Serializable {
    /**
     * Identificador único del empleado.
     * Longitud máxima: 4 caracteres.
     */
    @Id
    @Column(name = "id_empleado", length = 4, nullable = false)
    private String idEmpleado;
    
    /**
     * Nombre del empleado.
     * Longitud máxima: 30 caracteres.
     */
    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;
    
    /**
     * Cantidad de salario anual.
     */
    @Column(name = "salario_anual", nullable = false)
    private int salarioAnual;
    
    /**
     * Sección a la que pertenece el producto.
     * Asociación muchos-a-uno con la entidad {@link Seccion}.
     */
    @ManyToOne
    @JoinColumn(name = "id_seccion", nullable = false)
    private Seccion seccion;
    
    /**
     * Constructor vacío requerido por JPA.
     */
    public Empleado(){
    }
    
    /**
     * Constructor para crear un empleado con todos sus atributos.
     * @param idEmpleado Indica el código único del empleado.
     * @param nombre Indica el nombre del empleado.
     * @param salarioAnual Indica el salario anual del empleado.
     */
    public Empleado (String idEmpleado, String nombre, int salarioAnual){
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salarioAnual = salarioAnual;
    }
    
    /**
     * Devuelve el código de identificación del empleado.
     * @return ID del empleado
     */
    public String getIdEmpleado(){
        return this.idEmpleado;
    }
    
    /**
     * Devuelve el nombre del empleado.
     * @return nombre del empleado.
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Devuelve el salario anual del empleado.
     * @return salario anual del empleado.
     */
    public int getSalarioAnual(){
        return this.salarioAnual;
    }
    
    /**
     * Devuelve la sección a la que pertenece el empleado.
     * @return sección del empleado.
     */
    public Seccion getSeccion() {
        return seccion;
    }
    
    /**
     * Establece nuevo identificador del empleado.
     * @param idEmpleado nuevo identificador.
     */
    public void setIdEmpleado (String idEmpleado){
        this.idEmpleado = idEmpleado;
    }
    
    /**
     * Establece un nuevo nombre al empleado.
     * @param nombre nuevo nombre del empleado.
     */
    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    
    /**
     * Establece un nuevo salario anual al empleado.
     * @param salarioAnual nuevo salario anual.
     */
    public void setSalarioAnual (int salarioAnual){
        this.salarioAnual = salarioAnual;
    }

    /**
     * Establece una nueva sección al empleado.
     * @param seccion nueva sección.
     */
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    
    /**
     * Devuelve una representación en texto del empleado.
     * @return Cadena con los datos del empleado.
     */
    @Override
    public String toString() {
        return this.getIdEmpleado() + " "
               + this.getNombre() + " "
               + this.getSalarioAnual() + " "
               + this.getSeccion().toString();
    }
}

