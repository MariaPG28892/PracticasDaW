<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//enlazamos el archivo php donde hemos creados las constantes para la conexión.
require_once __DIR__ . '/../etc/conf.php';

function conectarBaseDatos(): PDO | false
{

try{

    $conexion= new PDO(DB_DSN, DB_USER, DB_PASSWORD);
    //utilizamos setatribute para manejar los errores.
    $conexion ->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    //si se establece conexión la retorna.
    return $conexion;

}catch(PDOException $ex){
    //Si no retorna false, luego podremos manejar el error posteriormente. 
    return false;
}

}
