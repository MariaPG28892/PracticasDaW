package tarea07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/** Ejercicio 2. Trabajando con listas de módulos
 * @author María Palomares Gallo
 */
public class Ejercicio02 {

    public static void main(String[] args) {
        
        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        
        // Constantes
        
        // Variables de entrada
        String modulosDaw[];
        String modulosDam[];
        
        // Variables auxiliares
        List<String> listaDaw;
        List<String> listaDam;
        List<String> listaMatriculadas = new ArrayList<String>();
        Set<String> conjuntoMatriculadas = new HashSet<String>();
        
        // Variables de salida
        int contador = 1;
        int contador2 = 1;
        int contador3 = 1;
        int contador4 = 1;
        int contador6 = 1;
        int contador7 = 1;

        //----------------------------------------------
        //               Entrada de datos 
        //----------------------------------------------
        
        // No hay entrada de datos, pues se usa un número fijo de elementos
        
        System.out.println("LISTAS DE MÓDULOS");
        System.out.println("-----------------------------");

        //Vuelco en estos dos arrays los contenidos de la clase utilidades para después poder crear las listas.
        modulosDaw = Utilidades.getArrayModulosDAW();
        modulosDam = Utilidades.getArrayModulosDAM();

        // Instanciamos las listas.
        listaDaw = new ArrayList<String>();
        listaDam = new ArrayList<String>();
        
        //Rellenamos las listas, lo he hecho con addAll y usando la clase arrays.asLoist que convierte el array en una lista.
        /*
        En primer lugar lo hice con for, pero al darle a la bombilla me lo cambió a esta manera y me pareció que el código estaba
        más optimizado. Con for lo había hecho de la siguiente manera.
        for(int i = 0; i<modulosDaw.lenght; i++){listaDaw.add(modulosDaw[i])}
        for(int i = 0; i<modulosDam.lenght; i++){listaDam.add(modulosDam[i])}
        */
        listaDaw.addAll(Arrays.asList(modulosDaw)); 
        listaDam.addAll(Arrays.asList(modulosDam));

        //----------------------------------------------
        //               Procesamiento
        //----------------------------------------------

        /*
        Primero sacamos por pantalla el contenido de la listaDaw sin ningún cambio, he usado el contador para el indice y un
        for-each para poder sacar modulo por modulo de la lista y sacarlo por pantalla.
        */
        System.out.println();
        System.out.printf("Contenido inicial de la lista de módulos de DAW:  \n");
        
        for(String modulo : listaDaw){
            System.out.println(contador+". "+modulo);
            contador++;
        }
        
        //Lo mismo con la listaDam sin ningún cambio.
        System.out.println();   
        System.out.printf("Contenido inicial de la lista de módulos de DAM:  \n");

        for(String modulo : listaDam){
            System.out.println(contador2+". "+modulo);
            contador2++;
        }        
        
        /*
        Ordenación de listaDam por nombre. Lo que hacemos es usar la listaDam y usar sort, esto es lo que nos permite ordenar la lista
        el parámetro que le metemos es la clase comparadorModulosPorNombre que hemos realizado fuera de esta clase.
        */

        System.out.println();  
        System.out.printf ("Ordenación de la lista de módulos de DAM por nombre (alfabético):\n");
        
        listaDam.sort(new ComparadorModulosPorNombre());
        
        for(String modulo : listaDam){
            System.out.println(contador6+". "+modulo);
            contador6++;
        }
               
        //Para ordenar la listaDam por código repetimos la operación anterior, pero usando el comparadorModuloPorCodigo.
        System.out.println();  
        System.out.printf ("Ordenación de la lista de módulos de DAM por código:\n");

        listaDam.sort(new ComparadorModuloPorCodigo()); 
        
        for(String modulo : listaDam){
            System.out.println(contador7+". "+modulo);
            contador7++;
        }

        //----------------------------------------------
        //            Salida de resultados
        //----------------------------------------------
        /*
        Para almacenar los modulos almacenados he creado en primer lugar un iterador para recorrer la lista, luego he usado un 
        bucle while que se seguira ejecutando mientras haya un siguiente módulo dentro de este. Una vez entra en el bucle, he almacenado
        los módulos en una variable, esta variable se convertirá en minúscula y comprobará si contiene una "i", si la contiene pasará
        al siguiente módulo, de lo contrario se almacenará en la lista y conjunto correspondiente.
        Para añadir los asteriscos de los módulos que se queden guardados he usador set, que lo que hace es renombrar el módulo dentro
        de la lista.
        */
        ListIterator<String> iteradorListaDaw = listaDaw.listIterator();
        
        while(iteradorListaDaw.hasNext()){
            String modulo = iteradorListaDaw.next();
            //System.out.println("Módulo actual: " + modulo);
            if(!modulo.toLowerCase().contains("i")){
                listaMatriculadas.add(modulo);
                conjuntoMatriculadas.add(modulo);
                
                iteradorListaDaw.set("**"+modulo+"**");
            }
        }
        
        //Sacamos el contenido final de los módulos, donde comprobaremos que salen los asteríscos de aquellos matriculados.
        System.out.println();     
        System.out.printf("Contenido final de la lista de módulos de DAW:  \n");
        
        for(String modulo : listaDaw){
            System.out.println(contador3+". "+modulo);
            contador3++;
        }
        
        //Sacamos por pantalla el contenido de la lista de los modulos matriculados,esta saldrá ordenada por valor.
        System.out.println();   
        System.out.printf("Contenido final de la lista de módulos matriculados (DAW): \n");
        
        for(String modulo : listaMatriculadas){
            System.out.println(contador4+". "+modulo);
            contador4++;
        }
        
        //Sacamos por pantalla el contenido del conjunto de matriculados, donde saldrán ordenadas por orden de insercción.
        System.out.println();   
        System.out.printf("Contenido final del conjunto de módulos matriculados (DAW): \n");
        int contador5 = 1;
        for(String modulo : conjuntoMatriculadas){
            System.out.println(contador5+". "+modulo);
            contador5++;
        }
    }
}

/**
 * Clase que permite comparar dos módulos usando como criterio
 * de comparación su nombre. Se trata de una comparación alfabética.
 * @author María Palomares Gallo
 */
class ComparadorModulosPorNombre implements Comparator <String>{
    @Override
    /*
    Lo que hacemos con split es indicar que empieza a ordenar despues del guión en la primera posicion. En el return
    usamos que compare uno con otro y las ordenará por orden alafabético.
    */
    public int compare(String modulo1, String modulo2) {
        String nombreModulo1 = modulo1.split("-")[1];  
        String nombreModulo2 = modulo2.split("-")[1];

        return nombreModulo1.compareTo(nombreModulo2); 
    }
}


/**
 * Clase que permite comparar dos módulos usando como criterio
 * de comparación su código.
 * @author María Palomares Gallo
 */
class ComparadorModuloPorCodigo implements Comparator <String>{
    @Override
    /*
    Aquí usamos el compareTo directamente, ya que empezará a ordenar por los códigos.
    */
    public int compare(String modulo1, String modulo2) {
        return modulo1.compareTo(modulo2);
    }
}