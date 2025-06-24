package tarea04;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ejercicio 1. Comprimir cadenas.
 * @author María Palomares Gallo.
 */

public class Ejercicio01 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        
        // Constantes
        // Variables de entrada
        String cadenaUsuario;
        // Variables de salida
        String resultado= "";     
        // Variables auxiliares
        char caracterActual;  
        int contador = 0;
        // Clase Scanner para petición de datos de entrada
       
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("Ejercicio 1. Comprimir cadenas.");
        System.out.println("-------------------------------");

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        System.out.println("Escribe una cadena de texto: ");
        //Metemos lo que escriba el usuario por teclado en el cadena, cadenaUsuario.
        cadenaUsuario = teclado.nextLine();
        
        //Le quitamos los espacios que pueda haber delante o dentrás de la cadenaUsuario.
        cadenaUsuario= cadenaUsuario.trim();
        
        /*
        Usamos pattern para indicar el patron, en este caso he indicado ^ y $, para indicar que se aplique esta 
        expresión regular desde que empieza (^) hasta que termina la cadena ($). Solo se podría usar minúsculas y máyusculas
        he prohibido la ñ usando ^, tanto mayúscula como minúscula, y cuando cierro el corchete que engloba toda la expresión
        indico +, que significa que pueden aparecer varias veces. 
        */
        Pattern patron = Pattern.compile("^[a-zA-Z&&[^ñÑ]]+$");
        //Matcher sirve para pasar el patrón que hemos creado a la cadena y guardarla en otra.
        Matcher coincidencia = patron.matcher(cadenaUsuario);
        
        /*
        En las clases vi que se podía hacer desde aquí directamente, pero quería practicar los métodos. Aquí abro un 
        bucle if-else, donde digo que si coincide siga procesando el programa, sino coincide muestre que hay un error.
        */
        if(coincidencia.matches()){
            
            //Con charAt(0) indicamos que caracterActual, se coloque en el primer carácter de la CadenaUsuario.
            caracterActual = cadenaUsuario.charAt(0);
            
            //Usamos un bucle for, que empiece desde el índice 0 de cadena usuari, hasta que recorra toda la cadena. 
            for(int i = 0; i <cadenaUsuario.length(); i++){
                /*
                Luego, en cada iteración del bucle, comparamos el carácter actual en la cadena (`cadenaUsuario.charAt(i)`) con el `caracterActual`.
                Si el carácter en la posición actual es igual a `caracterActual`, incrementamos el contador para contar cuántas veces se repite 
                ese carácter consecutivo. Si el carácter es diferente al `caracterActual`, significa que hemos llegado al final de una secuencia 
                de caracteres iguales, por lo que agregamos el `caracterActual` al resultado junto con su contador.
                */
                if(cadenaUsuario.charAt(i)== caracterActual){
                    contador++;
                }else{
                    /*
                    Si los caracteres consecutivos no son iguales, pasan a esta parte del bucle, donde decimos que si el 
                    contador es mayor a 1, guarde en resultado el carácter actual y para añadir el contador, lo convertimos 
                    en String usando String.valueOf().Porque no podemos concatenar directamente un string con un char. Si el
                    contador es menor o igual que uno, solo se guardará el caracter sin el contador.
                    */
                    if(contador > 1){
                        
                        resultado += caracterActual + String.valueOf(contador);
                        
                    } else {
                        resultado += caracterActual;
                    }
                    //Devolvemos el contador a 1 y le decimos a la variable char que tome el mismo valor de i en ese momento para volver a contar.
                    caracterActual = cadenaUsuario.charAt(i);
                    contador = 1;
                }
            }
            
            /*
            Intenté hacerlo de varias maneras porque el bucle se detiene cuando llega al último caracter y no lo procesa,
            pero se queda guardado, por lo que puse de nuevo el código de dentro para que al continuar el código lo procesara y 
            funcionó. Intente hacerlo como vi en las clases almacenando cadenaUsuario.lenght en una variable y eso aplicarlo al 
            bucle, pero seguía sin contarme el for. De esta manera si que me funciona.
            */
            if(contador > 1){
                resultado += caracterActual + String.valueOf(contador);
            }else{
                resultado += caracterActual;
            }
            
        }else{
            //Si la cadena no cumple la expresión he dicho que la variable resultado sea una cadena de texto con ese contenido.
            resultado = "Error. Solo se permiten letras, menos la ñ.";
        }
         
        
        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        
        /*
        Para sacarlo por pantalla he usado un bucle if-else anidado, donde si el resultado es igual que a la cadena de
        texto que hemos puesto para el error, usamos equal porque es para cadenas no podemos usar == porque daría error, 
        imprima esa cadena como resultado. Si resultado es mayor o igual que la cadena usuario imprima solamente la cadena
        que ha introducido el usuario, si no sacará por pantalla la cadena comprimida.
        Pensé en hacerlo con arrays, pero me resultó más lioso y tendría que sacarlo con for-each.
        */
        if(resultado.equals("Error. Solo se permiten letras, menos la ñ.")){
            System.out.println(resultado);
        }else if(resultado.length()>= cadenaUsuario.length()){
            System.out.println(cadenaUsuario);
        }else{
            System.out.println(resultado);
        }
    }
}
