package tarea04;

import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Ejercicio 2. Rotar matrices cuadradas.
 *
 * @author María Palomares Gallo.
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        // Variables de entrada
        String fila0;

        // Variables de salida
        String[][] matriz;
        String[][] matrizRotada;
        // Variables auxiliares
        StringTokenizer filaToken;
        int totalElementos;
        String filasRestantes;
        StringTokenizer filasRestantesToken;
        int totalElementosFilasRestantes;
        boolean finalizar = false; //Bandera que vamos a usar para salir del programa.
        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //      Entrada de datos y procesamiento
        //----------------------------------------------
        System.out.println("Ejercicio 2. Rotar matrices cuadradas.");
        System.out.println("--------------------------------------");

        System.out.println("Introduce la primera fila de valores separados por comas: ");
        //metemos dentro de fila0 lo que el usuario ha metido por teclado y aplicamos trim para quitar los espacios anteriores y posteriores.
        fila0 = teclado.nextLine();
        fila0 = fila0.trim(); //quita los espacios antes y después de toda la cadena, no de cada token, por eso da error si lo haces entre la cadena.

        /*
        Aquí lo he hecho solo con matches como se explicó en clase para practicar de las dos maneras. En la expresión 
        regular he metido letras mayúsculas y minúsculas, número y la ",", muy importante añadirla porque sino nunca
        aceptaría lo que el usuario mete por pantalla, ya que le pedimos que este separada por comas.
        */
        if (fila0.matches("^[A-Za-z0-9ñÑ,]+$")) {

            /*
            Aquí usamos StringTokenizer, donde en la variable filaToken, metemos los token separados. Luego contamos
            los tokens que contiene con countTokens, para poder usar esta variable para formar la matriz con el número
            que contiene.
            */
            filaToken = new StringTokenizer(fila0, ",");
            totalElementos = filaToken.countTokens();
            
            /*
            En primer lugar, decimos que si totalElementos es mayor que 1, que continue con el código, de lo contrario 
            mandará un mensaje con un error que dirá que la matriz no puede ser de 0x0 o 1x1 y finalizaría el programa.
            */
            if (totalElementos > 1) {

                //A continuación informamos al usuario del tamaño de la matriz y la formamos, utilizando la variable totalElementos.
                System.out.println("Info: Vamos a trabajar con una matriz de " + totalElementos + " x " + totalElementos);
                matriz = new String[totalElementos][totalElementos];

                /*
                Abrimos un bucle for, donde vamos a trabajar con la primera fila, donde vamos a recorrer en el for las
                columnas, decimos que vaya avanzando columnas, hasta que llegue a la última, indicado por totalElementos.
                */
                for (int columna = 0; columna < totalElementos; columna++) {
                    /*
                    Nos colocamos en la fila 0, por eso el primer corchete contiene el 0 y el segundo columna, donde vamos 
                    a ir introduciendo los tokens, con filaTokens, añadirá mientras siga habiendo tokens por eso .nextTokens.
                    */
                    matriz[0][columna] = filaToken.nextToken();
                }
                
                /*
                Una vez tenemos la primera fila completa, seguimos. Ahora vamos a operar con las filas, porque no sabemos
                cuantas podría haber, pero debemos empezar por la 1, porque la 0 ya está completa. Vamos a pedir tantas
                filas como el totalElementos contenga.
                */
                for(int fila=1; fila<totalElementos; fila++){
                    
                    /*
                    Aquí he utilizado una bandera, que la primera fila siempre va a ser falsa y continuará el código, pero
                    si esta no cumple los requisitos o alguna de las filas siguientes tampoco, cambiará a true y acabará 
                    el programa indicando el error. (No sé si lo he explicado bien).
                    */
                    if(!finalizar){
                        
                        System.out.println("Introduce la fila"+fila+" de valores separados por comas:");
                        //Cada vez que pidamos una fila se irá guardando en esta variable.
                        filasRestantes = teclado.nextLine();
                        filasRestantes = filasRestantes.trim(); //Le quitamos espacios delante y detrás de la cadena.

                        /*
                        Luego hará el mismo proceso que hemos hecho antes, si cumple la expresión regular, seguirá el programa,
                        si no lo cumple, irá a else y cambiará la bandera a true, lo que hará que no pueda entrar de nuevo 
                        al bucle y continuar con el programa y lanzará el error de que la cadena no es correcta.
                        */
                        if(filasRestantes.matches("^[A-Za-z0-9,]+$")){
                            
                            //Con unas nuevas variables separamos las cadenas en tokens y los contamos de nuevo.
                            filasRestantesToken = new StringTokenizer(filasRestantes, ",");
                            totalElementosFilasRestantes = filasRestantesToken.countTokens();

                            /*
                            Es importante contarlos, porque si no es el mismo número que totalElementos, en lugar de continuar
                            el programa, pasará a else, el cual, cambiará la bandera a true de nuevo y el programa no continuará
                            y lanzará el mensaje de error correspondiente.
                            */
                            if(totalElementosFilasRestantes == totalElementos){

                                /*
                                En el caso de cumplir la condición, entramos en un bucle for, donde de nuevo operamos con las
                                columnas, donde iremos introduciendo tokens en las columnas mientras haya. La fila va cambiando
                                con forme vayamos cambiando de fila en el primer for.
                                */
                                for (int columna = 0; columna < totalElementos; columna++) {
                                    matriz[fila][columna] = filasRestantesToken.nextToken();
                                }

                            }else{
                                //Cambia la bandera y mensaje de error.
                                finalizar = true;
                                System.out.println("La cadena introducida no es correcta.");
                            }
                        }else{
                            //Cambia la bandera y mensaje de error.
                            finalizar = true;
                            System.out.println("La cadena introducida no cumple los requisitos.");
                        }
                    }
                }
                
                /*
                Para imprimir la matriz de nuevo indicamos que si la bandera es falsa, lo imprima, de ser verdadera no lo haría porque
                daría error. Para imprimirla lo que hacemos es hacer dos bucles for donde recorremos las filas (i) y las columnas
                (j) y luego lo sacamos por pantalla, indicando estas dentro de los corchetes.
                */
                if(!finalizar){
                    System.out.println("\nMatriz original:");
                    for (int i = 0; i < totalElementos; i++) {
                        for (int j = 0; j < totalElementos; j++) {
                            System.out.print(matriz[i][j] + " ");  //añado un espacio para que quede más estético
                        }
                        System.out.println();  // Añadimos un salto de línea después de cada filam por eso está fuera del segundo for.
                    }
                }
                
                /*
                Para crear la matriz rotada 90 grados, creamos una nueva matriz y le añadimos entre los corchetes el mismo tamaño usado
                anteriormente. Recorremos las filas(i) y las columnas(j), en el segundo for, introducimos la matrizRotada
                donde vamos a poner j en primer lugar porque las filas, pasarán a ser columnas y en las filas lo que le indicamos
                es que cada indice(i) vaya desplazandose a la izquierda (-1) las veces que indique el totalElementos y esto se 
                aplica a la matriz original.
                */
                matrizRotada = new String[totalElementos][totalElementos];
                for(int i= 0; i < totalElementos; i++){
                    for(int j= 0; j<totalElementos; j++){
                        matrizRotada[j][totalElementos - 1- i] = matriz[i][j] ;
                    }
                }
                
                //para sacar por pantalla la matriz rotada lo hacemos de la misma manera que lo hemos hecho con la anterior.
                System.out.println("\nMatriz rotada:");
                    for (int i = 0; i < totalElementos; i++) {
                        for (int j = 0; j < totalElementos; j++) {
                            System.out.print(matrizRotada[i][j] + " "); 
                        }
                        System.out.println();  // Salto de línea después de cada fila
                    }
            } else {
                System.out.println("Error: La matriz no puede ser de tamaño 0x0 ni 1x1.");
            }
        }else{
            System.out.println("Error al introducir los valores de la cadena. Introduce valores letras y números, separados por comas.");
        }
        
    } 
}

