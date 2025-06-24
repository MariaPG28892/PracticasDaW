<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//Añadimos la ruta de las dos funciones que vamos a utilizar dentro de este script.
require_once __DIR__.'/funciones/conectarBaseDatos.php';
require_once __DIR__.'/funciones/eliminarProducto.php';

//creamos un array de errores para ir almacenando todos los errores que vayan sucediendo en nuestro programa y podamos sacarlos al final.
$errores = [];

/* Primero creamos una conexión con nuestra base de datos por medio de la función. Hacemos un condicional para que si no se establece conexión
finalice el programa. */
$conexion = conectarBaseDatos();
if(!$conexion){
    die("Error al conectar con la base de datos.");
}

//Nos aseguramos de que POST no está vacío y que recoge los datos del formulario. 
if(!empty($_POST)){

    /*
    Filtramos con filter_input el $id que se recibe por el formulario hidden. Vemos si se encuentra dentro del post, el nombre
    con el que se registra y lo validamos como un número entero, aunque estemos recibiendo una cadena, aquí lo convertimos en un 
    entero.
    */
    $id = filter_input(INPUT_POST, 'ideliminar', FILTER_VALIDATE_INT);
    /*
    Utilizamos las tres condiciones, donde decimos que si esta vacío muestre el error correspondiente, al igual que si devuelve falso,
    solo seguirá procesando si no esta vacío y si no es falso.
    */
    if($id === null){
        $errores[] = "El ID no se envió por POST";
    }else if($id === false){
        $errores[] = "El ID del producto no es válido.";
    }else{

        /*
        En el caso de que haya pasado las dos condiciones, llamamos a la función eliminar producto, donde pasamos por parámetro
        la conexión que hemos creado al principio del script y el $id, que devuelve el campo hidden del formulario y que hemos validado
        por filter_input.
        */
        $resultado = eliminarProducto($conexion, $id);

     //Aqui controlamos si hay errores, decimos que si el array no está vació que devuelva uno a uno los errores, como un error y acabe el programa.
    if (!empty($errores)) {
        foreach ($errores as $error) {
            echo $error . "<br>";
        }
        exit;
    }

    /* 
    Si no hay errores y se ha creado la variable resultado, que devuelve true si se ha eliminado algún registro, decimos que si devuelve
    true que indique que el producto se ha borrado correctamente, sino que no existe.
    */
    if ($resultado === true) {
        echo "<br>El producto se ha borrado correctamente.";
    } else {
        echo "El producto no existe.";
    }

}


}