package ejercicio2;

import java.time.LocalDate;
import java.util.List;

/**
 * Ejercicio 2: Lectura y escritura de objetos. XML.
 *
 * @author Palomares Gallo, María
 */

public class Ejercicio2 {

    /**
     * MÃ©todo principal.
     * @param args argumentos que recibe el metodo
     */
    public static void main(String args[]) {

        //----------------------------------------------
        //          Declaracion de variables 
        //----------------------------------------------
        // Constantes
        // Variables de entrada  
        String ruta = System.getProperty("user.dir") + "/recursos/Biblioteca.xml";
        Biblioteca miBiblioteca;
        Libro libro1, libro2, libro3;
        BibliotecaXML bd;
        
        // Variables de salida  
        
        // Variables auxiliares
        //----------------------------------------------------------------------
        //                 Entrada de datos + Procesamiento + Salida 
        //---------------------------------------------------------------------- 
        
        try {
            // Creo un objeto Biblioteca y tres objetos Libro. 
            miBiblioteca = new Biblioteca();
            libro1 = new Libro("El enigma de la montaña", "Carlos Gonzalez",LocalDate.parse("2015-07-15"), 
                    List.of("La llamada del destino", "El ascenso peligroso", "Enfrentando la tormenta","El descubrimiento final"),
                    "Aventura");
                    
            libro2 = new Libro("Caminos de la mente", "Elena Rodri­guez", LocalDate.parse("2019-09-30"),
                    List.of("El despertar de la consciencia", "La busqueda del yo", "Pensamientos y realidades","Trascender el ego"),
                    "Filosofi­a");
             
            // AÃ±ado loslibros creados a la biliboteca.
            miBiblioteca.add(libro1);
            miBiblioteca.add(libro2);

            // Muestro por pantalla el listado de libros que tiene la biblioteca.
            System.out.printf("He creado y almacenado en el archivo %d libros.\n",
                    miBiblioteca.numLibros());
            System.out.println("LISTADO DE LIBROS GUARDADOS EN LA BIBLIOTECA:");
            System.out.println(miBiblioteca.toString());

            // Escribo en el archivo XML a partir de la clase BibliotecaXML.
            System.out.println("Escribiendo en el archivo Biblioteca.xml...");
            bd = new BibliotecaXML(ruta);
            bd.escribir(miBiblioteca);

            libro3 = new Libro("El codigo del hacker", "David Fernandez",  LocalDate.parse("2021-04-12"),
                    List.of("La historia de los hackers", "Criptografi­a basica", "Redes y seguridad","Etica del hacking"),
                    "Tecnologi­a");
                    
            // Añado el nuevo libro a la biblioteca.
            miBiblioteca.add(libro3);

            // Muestro por pantalla los tres libros que contiene la biblioteca.
            System.out.printf("\nAhora inserto 1 libro más. Tengo %d libros.\n",
                    miBiblioteca.numLibros());
            System.out.println("LISTADO DE LIBROS GUARDADOS EN LA BIBLIOTECA:");
            System.out.println(miBiblioteca);

            // Leo del archivo XML, almacenando su contenido (los dos libros creadas originalmente) 
            // en el objeto de tipo Biblioteca.
            miBiblioteca = bd.leer();

            // Muestro por pantalla los dos libros de la biblioteca (los que se han leído del archivo XML).
            System.out.printf("Leo del archivo %d libros.\n", miBiblioteca.numLibros());
            System.out.println("LISTADO DE LIBROS GUARDADOS EN EL FICHERO:");
            System.out.println(miBiblioteca);
            
        } catch (NullPointerException e) {
            System.out.printf(e.getMessage());
        }
        
    }
}
