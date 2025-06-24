package tarea07;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


/** Ejercicio 3. Mapa de Ciclos y sus módulos
 * @author María Palomares Gallo
 */
public class Ejercicio03 {

    public static void main(String[] args) {
        
        //----------------------------------------------
        //    Declaración de variables y constantes
        //----------------------------------------------
        
        // Constantes
        
        // Variables de entrada
        String[] arrayCodigosModulosDAW;
        Map<Integer, List<Integer>> mapaCodigo;
        // Variables auxiliares
     
        // Variables de salida


        //----------------------------------------------
        //               Entrada de datos 
        //----------------------------------------------
        
        // No se piden datos al usuario, ya que se usa un array de elementos fijos
        
        System.out.println("CÓDIGOS DE LOS MÓDULOS DE DAW POR CURSOS");
        System.out.println("----------------------------------------");
        
        //----------------------------------------------
        //                  Procesamiento
        //----------------------------------------------
        
        // Primero metemos los código dentro del array para poder operar con ellos.
        arrayCodigosModulosDAW = Utilidades.getArrayCodigosModulosDAW();
        //Inicializamos el mapa, he usado TreeMap porque así me ordenaría directamente los valores.
        mapaCodigo = new TreeMap<>();
        
        //Convertimos el array de los codigos en un lista y la añadimos a lista codigos.
        List<String> listaCodigos = Arrays.asList(arrayCodigosModulosDAW);

        /*
        Hacemos un bucle for-each para recorrer la lista e ir operando con un codigo en cada vuelta del bucle. Cogemos el codigo
        y lo dividimos en partes antes del guion, y despues del guión. La parte de antes se almacenara en el indice del array 0, mientras
        que la parte de detras se almacena en el indice 1.
        */
        for(String codigo : listaCodigos){
            String[] partes = codigo.split("-");
            
            //Como tenemos string, lo que hacemos es convertir los números en enteros para poder añadirlos al mapa
            int curso = Integer.parseInt(partes[0]);
            int codigoModulo = Integer.parseInt(partes[1]);
            
            /*
            Hacemos un condicional donde si el mapa no contiene el curso que lo añada como nueva lista y cree una nueva clave valor
            dentro del mapa y que añada curso.
            */
            if (!mapaCodigo.containsKey(curso)) {
                mapaCodigo.put(curso, new ArrayList<Integer>());
            }
            
            // Añadir el código del módulo a la lista correspondiente al curso.
            mapaCodigo.get(curso).add(codigoModulo);
        }
        
        
        //----------------------------------------------
        //           Salida de resultados
        //----------------------------------------------
        
        System.out.printf("Contenido del mapa de códigos de módulos organizados por cursos:\n\n");
        
        //Luego hacemos un for-each para sacarlo por pantalla, donde iremos guardando el curso y los modulos en las variables para sacarlas por pantalla
        //Usamos entry para extraer cada parte.
            for (Map.Entry<Integer, List<Integer>> entry : mapaCodigo.entrySet()) {
            Integer curso = entry.getKey();
            List<Integer> modulos = entry.getValue();
            
            System.out.println("Módulos de " + curso + "º curso: " + modulos);
        }

 
    }
}