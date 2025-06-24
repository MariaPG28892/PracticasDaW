<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//enlazamos la conexión a la base de datos para poder pasarla por parámetro.
require_once __DIR__.'/conectarBaseDatos.php';

function eliminarProducto(PDO $conexion, $id): bool
{
    //En la variable sql metemos la consulta para eliminar en este caso donde el id corresponda por el pasado por parámetro.
    $SQL = "DELETE FROM productos WHERE id = :id";

    try{
        $stmt= $conexion->prepare($SQL);
        $stmt->bindValue('id', $id, PDO::PARAM_INT);
        if($stmt->execute()){
            //Aquí lo que recogemos es el número de resgistros que se ha eliminado.
            $registrosEliminados = $stmt->rowCount();
            //Si se ha eliminado algún registro, será que la función funciona por lo que retornamos true.
            if($registrosEliminados === 1){
                return true;
            }
        }

    }catch(PDOException $ex){
        return false;
    }

    return false;
}