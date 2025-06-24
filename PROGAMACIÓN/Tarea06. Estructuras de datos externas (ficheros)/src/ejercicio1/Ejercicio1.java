package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Ejercicio 1: Lectura/escritura de una biblioteca de libros en ficheros de texto.
 *
 * @author Palomares Gallo, María
 */
public class Ejercicio1 {

    /**
     * Método principal.
     * Método para leer archivos y escribirlos en el formato deseado. 
     * @param args argumentos que recibe el mÃ©todo
     * @throws java.io.FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException, IOException {

        //----------------------------------------------
        //          Declaracion de variables 
        //----------------------------------------------
        // Constantes
        // Variables de entrada
        String rutaLibros;
        String rutaBiblioteca;
        String libro;
        // Variables de salida
        // Variables auxiliares
        String titulo;
        String autor;
        LocalDate fecha;
        String genero;
        List<String> capitulos;
        String[] partesLibro;
        String librosBiblioteca;
        String[] librosJuntos;
        //----------------------------------------------
        //       Entrada de datos + Procesamiento
        //----------------------------------------------
        //Creamos las dos rutas, una para extraer y otra para guardar, y le asignamos una variable para que luego sea más sencillo.
        rutaLibros = System.getProperty("user.dir") + "/recursos/ListadoLibros.txt";
        rutaBiblioteca = System.getProperty("user.dir") + "/recursos/Biblioteca.txt";
        
        System.out.println("Abriendo archivo de libros...");

        //Creamos el objeto biblioteca para después almacenar los libros.
        Biblioteca biblioteca = new Biblioteca();
        
        /*
        Abrimos un try catch, donde vamos a usar un bufferedReader para almacenar el texto que hay en la ruta establecida, dento de
        archivo. Dentro de archivo encontramos el texto del archivo y vamos a ir separando los libros y las secciones dentro de
        cada uno. Si no se puede abrir el archivo mandaremos un error. 
        */
        try (BufferedReader archivo = new BufferedReader(new FileReader(rutaLibros))) {
            
            /*
            Lo que hacemos es que readLine lee cada línea del archivo, esta línea la guardamos en libro y si esta no es nula, pasaremaos
            a dividir cada línea en los diferentes partes de libro y lo almacenaremos en el array. Si el array contiene 5 partes 
            cada parte la asignaremos a lo que corresponda para luego poder crear un objeto libro y pasarlo a la biblioteca para 
            añadirlo con el método add.
            */
            while((libro = archivo.readLine()) != null){
                
                partesLibro = libro.split(";");
                
                 if (partesLibro.length == 5) {
                    titulo = partesLibro[0];
                    autor = partesLibro[1];
                    /*
                    Aquí lo puse al principio con String y me daba error, lo que hice fue hacer una variable LocalDate y parsear
                    la parte que corresponde al array con la fecha para pasarla de String a Localdate y así funcionó, ya que no
                    podemos trabajar con String porque el constructor pide un localDate.
                    */
                    fecha = LocalDate.parse(partesLibro[2]);
                    genero = partesLibro[3];
                    capitulos = Arrays.asList(partesLibro[4].split(",")); 
                    
                    Libro libroNuevo = new Libro(titulo, autor, fecha, capitulos, genero);
                    biblioteca.add(libroNuevo);  
                 }
            }
        }catch (IOException e) {
            System.out.println("Error al abrir el archivo: "+e.getMessage());
        }

        System.out.println("Cerrando archivo de libros...");

        System.out.println();

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        
            System.out.println("Abriendo archivo de la biblioteca...");
            /*
            A continuación, lo que vamos a hacer es usar BufferedReader para escribir el contenido que hemos extraido anteriormente,
            para ello debemos de crear un nuevo archivo, con FileWriter donde especificamos la ruta donde lo queremos crear. 
            */
            try (BufferedWriter archivoEscribir = new BufferedWriter(new FileWriter(rutaBiblioteca))) {
                archivoEscribir.write("**********************************************************************************************************************************\n");
                archivoEscribir.write("LIBRO DE LIBROS\n");
                archivoEscribir.write("**********************************************************************************************************************************\n");
                
                //Primero almacenamos los libros de la biblioteca según el toString, pero lo obtenemos juntos.
                librosBiblioteca = biblioteca.toString();
                //Luego separamos cada libro con un salto de línea.
                librosJuntos = librosBiblioteca.split("\n");
                
                /*
                Hacemos un bucle for-each para poder separar cada libro individualmente y una vez hecho esto poder separar
                sus elementos. Luego hacemos un if, donde le quitamos los espacios y decimos que si no está vacio, dividimos
                el libro en varias partes que se separan en ";" Luego hacemos el mismo if que hemos hecho arriba donde decimis que si 
                las partes de libro son 5 vamos a ir escribiendo cada parte que necesitamos.
                */
                for (String libroSeparado : librosJuntos) {
                    if (!libroSeparado.trim().isEmpty()) {
                        // Reemplazamos los # con los títulos y los datos del libro
                        partesLibro = libroSeparado.split(";");
                        if (partesLibro.length >= 5) {
                            //Para eliminar la # he usados replaceFirst y lo sustituimos por un espacio en blanco, usamos ^ para indicar que es al principio de la cadena
                            String tituloLimpio = partesLibro[0].replaceFirst("^#", "");
                            archivoEscribir.write("TITULO DEL LIBRO:" + tituloLimpio + "\n");
                            archivoEscribir.write("AUTOR:" + partesLibro[1] + "\n");
                            archivoEscribir.write("FECHA DE CREACIÓN:" + partesLibro[2] + "\n");
                            archivoEscribir.write("GENERO:" + partesLibro[3] + "\n");
                            archivoEscribir.write("CAPITULOS:" + partesLibro[4] + "\n");
                            archivoEscribir.write("**********************************************************************************************************************************\n");
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al guardar la biblioteca: " + e.getMessage());
            }
 
            System.out.println("Cerrando archivo de la biblioteca...");

            System.out.println();
            System.out.println("Archivos cerrados y procesamiento finalizado");
            System.out.println("---------");
            System.out.println();
            System.out.println("Fin del programa.");
    }
}
