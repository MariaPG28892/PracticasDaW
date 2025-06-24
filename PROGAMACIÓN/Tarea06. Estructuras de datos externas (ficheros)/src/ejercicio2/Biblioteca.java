package ejercicio2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa una <strong>biblioteca</strong> para la gestion de libros. 
 * <p>
 * Los objetos de esta clase permiten almacenar y gestionar una lista de objetos de tipo
 * <strong>Libro</strong>. 
 * @author profe
 */
public class Biblioteca {
    
    // -------------------------------------------------------------------------
    // ATRIBUTOS DE OBJETO
    // ------------------------------------------------------------------------
    private List<Libro> libros; // Lista de objetos Libro 
    
    // ------------------------------------------------------------------------
    //                        CONSTRUCTOR
    // ------------------------------------------------------------------------
    /**
     * Crea un objeto Biblioteca vaci≠o.
     */
    public Biblioteca(){
        libros = new ArrayList<>();
    }

    /**
     * Asigna a la biblioteca un listado de libros que se pasa por parametro.
     * @param libros Lista de objetos de tipo Libro.
     */
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    
    /**
     * AÒade un nuevo libro a la biblioteca.
     * @param nuevo Objeto de tipo Libro que se aÒade
     * @throws NullPointerException si el parametro es nulo.
     */
    public void add(Libro nuevo) throws NullPointerException {
        if (nuevo == null)
             throw new NullPointerException ("El objeto Libro no puede ser nulo.");        
        libros.add(nuevo); 
    }
     
   /**
    * Busca un libro en la biblioteca a partir de su titulo.
    * @param titulo Ti≠tulo del libro que estamos buscando
    * @return si el libro se encuentra o no en la biblioteca
    * @throws NullPointerException si el parametro es nulo    
    */
    public boolean buscarPorTitulo(String titulo) throws NullPointerException {
        if (titulo == null)
            throw new NullPointerException ("El ti≠tulo del libro no puede ser nulo.");
        
        boolean encontrado = false;
        Iterator<Libro> iterator = libros.iterator();
        while (iterator.hasNext() && !encontrado) {
            Libro libro = iterator.next();
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
               encontrado = true;
            }
        }
        return encontrado;
    }
    
    /**
     * Obtiene el n√∫mero de libros de la biblioteca.
     * @return numero de libros
     */
    public int numLibros() {
        return libros.size();
    }
    
    /**
     * Permite borrar un libro de la biblioteca.
     * @param borrado Objeto de tipo Libro que queremos borrar
     * @return si el libro ha sido eliminado
     * @throws NullPointerException si el parametro es nulo
     */
    public boolean borrarLibro(Libro borrado) throws NullPointerException {
        if (borrado == null)
            throw new NullPointerException ("El objeto Libro no puede ser nulo.");
        
        return libros.remove(borrado);
    }
    
    /**
     * Permite vaciar la biblioteca de libros.
     */
    public void vaciarBiblioteca() {
        libros.clear();
    }
    
    /**
     * Devuelve una cadena que representa los libros de la biblioteca de 
     * forma textual.
     * @return cadena que representa los libros de la biblioteca de forma textual
     */
    @Override
    public String toString() {
        String listado = "";

        if (libros.isEmpty()) {
            listado = "La biblioteca est√° vac√≠a";
        } else {
            for (Libro l : libros) {
                listado = listado + l.toString()+"\n";
            }
        }
        return listado;
    }
}
