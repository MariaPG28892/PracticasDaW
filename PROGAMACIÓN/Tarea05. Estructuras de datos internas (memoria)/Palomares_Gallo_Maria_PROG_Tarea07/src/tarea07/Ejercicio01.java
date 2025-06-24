package tarea07;

import java.util.Set;
import java.util.HashSet;

/**
 * Ejercicio 1. Creando conjuntos de ciclos DAW y DAM con sus módulos
 * @author María Palomares Gallo 
 */
public class Ejercicio01 {
    
    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        
        // Constantes

        // Variables de entrada
        String modulosDaw[];
        String modulosDam [];
        
        // Variables auxiliares
        Set <String> conjuntoDaw;
        Set <String> conjuntoDam;
        Set <String> conjuntosUnidos;
        Set <String> conjuntosInterseccion;
        Set <String> conjuntosDiferencia;
                
        // Variables de salida
        int contador = 1;
        int contador2= 1;
        int contador3 = 1;
        int contador4 = 1;
        int contador5 = 1;
     
        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        
        // No hay entrada de datos, pues se usa un número fijo de elementos
        
        System.out.println("CONJUNTOS DE MÓDULOS DE DAW Y DAM");
        System.out.println("--------------------------------");

        //----------------------------------------------
        //                  Procesamiento
        //----------------------------------------------
        
        //Declaramos el array arriba y aquí los vamos rellenando con el array de utilidades.

        modulosDaw = Utilidades.getArrayModulosDAW();
        modulosDam = Utilidades.getArrayModulosDAM();
        
        // Inicialimando las colecciones que vamos autilizar, he decidido usar hashSet, pero podría haber utilizado cualquiera.
        conjuntoDaw =new HashSet<String>();
        conjuntoDam =new HashSet<String>();
        
        /*
        Para rellenar los conjuntos he usado el bucle while, he utilizado una variable i con valor 0 y he recorrido el bucle hasta
        que i tenga un valor mayor que todos los elementos que contiene el array de los modulos. Cada vez que se recorre el array vamos
        añadiendo al conjunto correspondiente utilizando a, cada valor de modulos usando [i] y vamos actualizando el contador.
        */
        int i = 0;
        while(i < modulosDaw.length){
           conjuntoDaw.add(modulosDaw[i]);
           i++;
        }
        int j = 0;
        while(j < modulosDam.length){
            conjuntoDam.add(modulosDam[j]);
            j++;
        }

        /*
        Para unir conjuntos, creo el conjunto, conjuntosUnidos y a su vez meto dentro el conjuntoDaw, una vez que lo tenemos metido
        lo que hacemos es usar conjuntosUnidos con addAll y dentro del paréntesis metemos el conjuntoDam, esto lo que hará es unir
        ambos conjuntos, pero sin repetir los módulos que estén repetidos.
        */
        conjuntosUnidos = new HashSet<String>(conjuntoDaw);
        conjuntosUnidos.addAll(conjuntoDam);

        /*
        Para la intersección he usado lo mismo que anteriormente, creo un conjunto donde le meto conjuntoDaw, y luego a con este
        conjunto usamos retainAll y en el paréntesis le pasamos conjuntoDam, esto lo que hará será dejar dentro del conjunto solamente
        los módulos que son iguales.
        */
        conjuntosInterseccion = new HashSet<String>(conjuntoDaw);
        conjuntosInterseccion.retainAll(conjuntoDam);
  
        /*
        Para la diferencia, repetimos el mismo proceso al crear el conjunto nuevo, pero esta vez en lugar de meter conjuntoDaw, metemos
        conjuntoDam, esto lo hacemos porque queremos conservar los modulos diferentes de ese modulo. Luego usamos removeAll y añadimos
        conjuntoDaw y solo conservará los módulos diferentes del conjuntoDam.
        */
        conjuntosDiferencia = new HashSet<String>(conjuntoDam);
        conjuntosDiferencia.removeAll(conjuntoDaw);

        
        //----------------------------------------------
        //              Salida de Resultados 
        //----------------------------------------------
        
        /*
        Para mostrar por pantalla, he usado un bucle for-each, donde he ido separando cada modulo del conjunto para mostrarlo por pantalla
        a su vez he añadido un contados para que vaya numerandolos.
        */
        System.out.printf ("Conjunto C1 (módulos DAW):\n");
        
        for(String modulo : conjuntoDaw){
            System.out.println(contador+". " + modulo);
            contador++;
        }

        System.out.printf ("\nConjunto C2 (módulos DAM):\n");
 
        for(String modulo : conjuntoDam){
            System.out.println(contador2+". " + modulo);
            contador2++;
        }
        
        System.out.printf ("\nUnión C1 y C2:\n");
        
        for (String modulo : conjuntosUnidos){
            System.out.println(contador3+". "+modulo);
            contador3++;
        }
        
        System.out.printf ("\nInterseccion C1 y C2:\n");

        for(String modulo: conjuntosInterseccion){
            System.out.println(contador4+". "+modulo);
            contador4++;
        }
        System.out.printf ("\nDiferencia C1 y C2:\n");

        for(String modulo: conjuntosDiferencia){
            System.out.println(contador5+". "+modulo);
            contador5++;
        }
    }
}