<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//Enlazamos las funciones y productos que es lo que vamos a usar en esta parte. 
require_once 'productos.php';
require_once 'funciones/conectarBaseDatos.php';
require_once 'funciones/modificarUnidades.php'; 

// Conectar a la base de datos y lanzar un error si no se conecta.
$conexion = conectarBaseDatos();
if (!$conexion) {
    die("Error al conectar con la base de datos.");
}

// Inicializamos un array de errores.
$errores = [];

//Si el formulario no está vacio comenzamos a desarrollar.
if (!empty($_POST)) {

    //FILTRAR Y VALIDAR EL PRODUCTO.
    /*
    Usamos un filter_input donde le pasamos filter validate int y lo convertimos y sanitizamos como un entero. Aquí solo tenemos que
    controlar que sea no sea null ni false y si es el caso mandar los errores.
    */
    $id = filter_input(INPUT_POST, 'idmodificar', FILTER_VALIDATE_INT);
    if($id === null){
        $errores[]= "El ID no se ha enviado por POST";
    } else if ($id === false) {
        $errores[] = "El ID del producto no es válido.";
    }

    // Filtrar y validar unidades.
    /*
    Aquí filtramos con filter input y usamos filter validate regexp con una expresión regular para controlar que sea de tipo numérico.
    Luego controlamos si es nulo o si es false, enviando los errores correspondientes y en el else, controlamos que el número introducido 
    sea mayor a 0.
    */
    $unidadesmodificar = filter_input(INPUT_POST, 'unidadesmodificar', FILTER_VALIDATE_REGEXP, [
        'options' => ['regexp' => '/^[-+]?\d+(\.\d+)?$/',]]);
    if($unidadesmodificar === null){
        $errores[] = "Las unidades a modificar no se enviaron por POST";
    } else if($unidadesmodificar == false){
        $errores[] = "Las unidades a modificar no son válidas";
    } else{
        if ($unidadesmodificar == 0) {
            $errores[] = "El valor para incrementar o decrementar las unidades debe ser un número distinto de cero.";
        }
    }

    

    // Si hay errores, mostramos los mensajes y detenemos el proceso.
    if (!empty($errores)) {
        foreach ($errores as $error) {
            echo $error . "<br>";
        }
        exit;
    }

    /*
    En el caso de no haber errores, usamos la funcion modificar, pasandole como parámetros la conexión, el id y las unidades a modificar
    ya filtradas.
    */
    $resultado = modificarUnidades($conexion, $id, $unidadesmodificar);

    /*
    Para mostrar los resultados, como puede estar el caso de -1, he puesto un array donde informe que si devuelve true, se ha completado
    correctamente, si devuelve menos uno, significaría que si lo decrementas y el resultado es negativo no se produce y si devuelve false
    el producto no existe.
    */
    if ($resultado === true) {
        echo "Incremento o decremento de unidades realizado correctamente.";
    } elseif ($resultado === -1) {
        echo "Incremento o decremento de unidades no realizado porque el número de unidades resultante es negativo o cero.";
    } else {
        echo "Incremento o decremento de unidades no realizado porque el producto no existe.";
    }
}