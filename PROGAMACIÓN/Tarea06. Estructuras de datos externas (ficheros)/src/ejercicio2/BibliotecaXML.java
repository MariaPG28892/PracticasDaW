package ejercicio2;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que permite serializar un objeto Biblioteca al formato XML y 
 viceversa.
 *   
 * @author profe
 */
public class BibliotecaXML {
    
     // Ruta del archivo donde se lee y escribe el objeto Biblioteca
    private String rutaArchivo;
    
    
    // Objeto Xstream que permite la L/E con archivos XML
    private XStream xstream;

    /**
     * Metodo constructor
     * @param nombreArchivo Ruta del archivo donde se lee y escribe el objeto Biblioteca
     */
    public BibliotecaXML(String nombreArchivo) {
        this.rutaArchivo = nombreArchivo;
        this.xstream = new XStream();
        //Permite asignar privilegios para poder operar con los archivos XML
        this.xstream.allowTypesByWildcard(new String[] { 
            "ejercicio2.**"
        });
    }

    
    // -----------------------------------------------------
    // Ejercicio 2: Metodos que debe implementar el alumnado
    // -----------------------------------------------------
    
    // 3.1. Metodo escribir()
    /**
     * Metodo que escribe, en un archivo de texto, un objeto Biblioteca serializable.
     * @param biblioteca Objeto Biblioteca serializable para almacenar en el archivo de texto.
     */    
    public void escribir(Biblioteca biblioteca) {
        /*
        Abrimos un try-catch donde vamos a decir donde queremos escribir e indicamos la ruta del fichero, luego con la variable
        xstream usamos .toXML que viene de una libreria importada que nos permite convertir los archivos a XML.
        */
        try (BufferedWriter escritorObjetos = new BufferedWriter(new FileWriter(rutaArchivo))) {
           xstream.toXML(biblioteca, escritorObjetos);
        }catch (IOException e) {
            System.err.println("Error al escribir en el archivo XML: " + e.getMessage());
        }
    }
    
    // 3.2. Metodo leer()
     /**
     * Metodo que lee, desde un archivo de texto, un objeto Biblioteca serializado.
     * @return Objecto Biblioteca que estaba almacenado en el archivo de texto.
     */
    public Biblioteca leer() {
        //Primero creamos un objeto biblioteca, el cual, usaremos para poder convertir en xml
        Biblioteca biblioteca;
        
        //Hacemos un try catch donde por medio del lector de objetos e indicandole la ruta vamos a leer los objetos de biblioteca
        try (BufferedReader lectorObjetos = new BufferedReader(new FileReader(rutaArchivo))) {
            biblioteca = (Biblioteca) xstream.fromXML(lectorObjetos);
        }catch (IOException e) {
            System.err.println("Error al leer el archivo XML: " + e.getMessage());
            biblioteca = null;
        }
        return biblioteca; // Sustituir este return por el codigo que resuelve el ejercicio
    }
}
