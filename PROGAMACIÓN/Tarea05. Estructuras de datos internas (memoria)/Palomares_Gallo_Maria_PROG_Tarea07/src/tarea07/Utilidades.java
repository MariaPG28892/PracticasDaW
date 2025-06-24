package tarea07;

import java.util.Iterator;

/**
 * Clase Utilidades
 *
 * @author Profesor
 */
public class Utilidades {

    private static final String[] arrayModulosDAW = {
        "0373-Lenguajes de Marcas y Sistemas de Gestión de Información",
        "0483-Sistemas Informáticos",
        "0484-Bases de Datos",
        "0485-Programación",
        "0487-Entornos de Desarrollo",
        "0612-Desarrollo Web en Entorno Cliente",
        "0613-Desarrollo Web en Entorno Servidor",
        "0614-Despliegue de Aplicaciones Web",
        "0615-Diseño de Interfaces Web",
        "0616-Proyecto Intermodular de Desarrollo de Aplicaciones Web",
        "0179-Inglés Profesional",
        "1665-Digitalización Aplicada al Sistema Productivo",
        "1708-Sostenibilidad Aplicada al Sistema Productivo",
        "1709-Itinerario Personal para la Empleabilidad I",
        "1710-Itinerario Personal para la Empleabilidad II"
    };

    private static final String[] arrayModulosDAM = {
        "0373-Lenguajes de Marcas y Sistemas de Gestión de Información",
        "0483-Sistemas Informáticos",
        "0484-Bases de Datos",
        "0485-Programación",
        "0486-Acceso a Datos",
        "0487-Entornos de Desarrollo",
        "0488-Desarrollo de Interfaces",
        "0489-Programación Multimedia y Dispositivos Móviles",
        "0490-Programación de Servicios y Procesos",
        "0491-Sistemas de Gestión Empresarial",
        "0492-Proyecto Intermodular de Desarrollo de Aplicaciones Multiplataforma",
        "0179-Inglés Profesional",
        "1665-Digitalización Aplicada al Sistema Productivo",
        "1708-Sostenibilidad Aplicada al Sistema Productivo",
        "1709-Itinerario Personal para la Empleabilidad I",
        "1710-Itinerario Personal para la Empleabilidad II"
    };
    
    private static final String[] arrayCodigosModulosDAW = {
        "1-0373",
        "1-0483",
        "1-0484",
        "1-0485",
        "1-0487",
        "2-0612",
        "2-0613",
        "2-0614",
        "2-0615",
        "2-0616",
        "1-0179",
        "2-1665",
        "1-1708",
        "1-1709",
        "2-1710"
    };
    
    

    /**
     * Devuelve el arrayModulosDAW
     *
     * @return arrayModulosDAW
     */
    public static String[] getArrayModulosDAW() {

        return Utilidades.arrayModulosDAW;
    }

    /**
     * Devuelve el arrayModulosDAM
     *
     * @return arrayModulosDAM
     */
    public static String[] getArrayModulosDAM() {

        return Utilidades.arrayModulosDAM;
    }
    
    /**
     * Devuelve el arrayModulosDAW
     *
     * @return arrayModulosDAW
     */
    public static String[] getArrayCodigosModulosDAW() {

        return Utilidades.arrayCodigosModulosDAW;
    }

    /**
     * Devuelve un modulo aleatorio del ciclo de DAW
     *
     * @return un modulo aleatorio del ciclo de DAW
     */
    public static String moduloAleatorioCodDAW() {

        int aleatorio = (int) (Math.random() * arrayModulosDAW.length);
        return Utilidades.arrayModulosDAW[aleatorio];
    }

    /**
     * Devuelve un modulo aleatorio del ciclo de DAM
     *
     * @return un modulo aleatorio del ciclo de DAM
     */
    public static String moduloAleatorioCodDAM() {

        int aleatorio = (int) (Math.random() * arrayModulosDAM.length);
        return Utilidades.arrayModulosDAM[aleatorio];
    }

    /** Recorre cualquier colección que implemente Iterable y muestra
     *  cada elemento uno a uno por pantalla con su formato correspondiente
     *
     * @param modulos colección a mostrar por pantalla
     */
    public static void mostrarModulos(Iterable<String> modulos) {
        
        int cont = 0;
        Iterator<String> it = modulos.iterator();

        while (it.hasNext()) {
            System.out.printf("%d. %s\n", ++cont, it.next());
        }
    }

}

