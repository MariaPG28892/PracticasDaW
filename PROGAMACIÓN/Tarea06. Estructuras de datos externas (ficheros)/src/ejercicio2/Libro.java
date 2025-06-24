package ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Clase que representa un <strong>libro</strong> para la gestion de una
 * biblioteca.
 * <p>
 * Los objetos de esta clase permiten almacenar y gestionar informacion sobre el
 * propio libro: ti≠tulo, autor, fecha creacion, genero y capi≠tulos.</p> 
 *
 * @author profe
 */
public final class Libro {

    // ------------------------------------------------------------------------
    // ATRIBUTOS DE OBJETO
    // ------------------------------------------------------------------------
    // Atributos de objeto variables
    private String titulo; // Ti≠tulo del libro
    private String autor; // Autor del libro.
    private List<String> capitulos; // Lista de capi≠tulos
    private String genero; // Genero del libro. 
    private LocalDate fechaCreacion; // Fecha de creacion del libro

    // ------------------------------------------------------------------------
    //                        CONSTRUCTOR
    // ------------------------------------------------------------------------
    /**
     * Crea un objeto Libro con un ti≠tulo, autor, fecha de cracion, genero
     * y lista de capi≠tulos.
     *
     * @param titulo Ti≠tulo del libro
     * @param autor Autor del libro
     * @param capitulos Lista de capitulos del libro
     * @param genero Genero del libro
     * @param fechaCreacion Fecha de creacion del libro
     */
    public Libro(String titulo, String autor, LocalDate fechaCreacion, List<String> capitulos, String genero)
            throws NullPointerException {
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setCapitulos(capitulos);
        this.setGenero(genero);
        this.setFechaCreacion(fechaCreacion);
    }

    // ------------------------------------------------------------------------
    //                        GETTERS Y SETTERS
    // ------------------------------------------------------------------------
    /**
     * Devuelve el titulo del libro.
     *
     * @return titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Devuelve el autor del libro
     *
     * @return autor
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Devuelve la lista de capitulos del libro
     *
     * @return lista de capitulos
     */
    public List<String> getCapitulos() {
        // Devolvemos una copia de la lista para evitar modificaciones externas
        return new ArrayList<>(this.capitulos);
    }

    /**
     * Devuelve el genero del libro
     *
     * @return genero
     */
    public String getGenero() {
        return this.genero;
    }

    /**
     * Devuelve la fecha de creaci√≥n del libro
     *
     * @return fecha de creacion
     */
    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Asigna un titulo al libro.
     *
     * @param titulo Ti≠tulo que se va a asignar al libro
     * @throws NullPointerException si el parametro titulo es nulo
     */
    public void setTitulo(String titulo) throws NullPointerException {
        if (titulo == null) {
            throw new NullPointerException("El ti≠tulo del libro no puede ser nulo.");
        }
        this.titulo = titulo;
    }

    /**
     * Asigna un autor al libro.
     *
     * @param autor Autor que se va a asignar al libro
     * @throws NullPointerException si el parametro autor es nulo
     */
    public void setAutor(String autor) throws NullPointerException {
        if (autor == null) {
            throw new NullPointerException("El autor del libro no puede ser nulo.");
        }
        this.autor = autor;
    }

    /**
     * Asigna una lista de capitulos al libro.
     *
     * @param capitulos Lista de capitulos que se va a asignar al libro
     * @throws NullPointerException si el parametro capitulos es nulo
     */
    public void setCapitulos(List<String> capitulos) throws NullPointerException {
        if (capitulos == null) {
            throw new NullPointerException("La lista de capi≠tulos del libro no puede ser nula.");
        }
        this.capitulos = new ArrayList<>(capitulos);
    }

    /**
     * Asigna el genero del libro.
     *
     * @param genero Genero que se va a asignar al libro
     * @throws NullPointerException si el parametro genero es nulo
     */
    public void setGenero(String genero) throws NullPointerException {
        if (genero == null) {
            throw new NullPointerException("El genero del libro no puede ser nulo.");
        }
        this.genero = genero;
    }

    /**
     * Asigna una fecha de creacion al libro.
     *
     * @param fechaCreacion Fecha de creacion que se va a asignar al libro
     * @throws NullPointerException si el parametro fechaCreacion es nulo
     */
    public void setFechaCreacion(LocalDate fechaCreacion) throws NullPointerException {
        if (fechaCreacion == null) {
            throw new NullPointerException("La fecha de creacion del libro no puede ser nula.");
        }
        this.fechaCreacion = fechaCreacion;
    }

    //-------------------------------------------------------------------------
    //                         METODO TOSTRING
    //-------------------------------------------------------------------------
    /**
     * Devuelve una cadena que representa los datos del libro de forma textual.
     *
     * @return cadena que representa los datos del libro  de forma textual
     */
    @Override
    public String toString() {
        return String.format("#%s;%s;%s;%s;%s;",
                this.getTitulo(), this.getAutor(), this.getFechaCreacion(),this.getGenero(), this.getCapitulos());
    }
}

