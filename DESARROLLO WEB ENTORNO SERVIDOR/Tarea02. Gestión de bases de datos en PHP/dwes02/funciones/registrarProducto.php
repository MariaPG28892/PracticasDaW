<?php

/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

require_once 'conectarBaseDatos.php';

function registrarProducto(PDO $conexion, array $productos): int | false
{
    /*
    Aquí ponemos la consulta con insert into y values, las values están con :, porque vamos a pasarlas todas por bindValue para que no use
    las literales para evitar que puedan introducir etiquetas o cualquier vulneabilidad de seguridad.
    */
    $SQL = "INSERT INTO productos (nombre, codigo_ean, categoria, propiedades, unidades, precio)
            VALUES (:nombre, :codigo_ean, :categoria, :propiedades, :unidades, :precio)";

    try {

        $stmt = $conexion->prepare($SQL);
        $stmt->bindValue('nombre', $productos['nombre']);
        $stmt->bindValue('codigo_ean', $productos['codigo_ean']);
        $stmt->bindValue('categoria', $productos['categoria']);
        $stmt->bindValue('propiedades', $productos['propiedades']);
        $stmt->bindValue('unidades', $productos['unidades']);
        $stmt->bindValue('precio', $productos['precio']);
        
        if ($stmt->execute()) {
            //con rowCount lo que hacemos es contar si se ha insertado algún resgistro, si es más de 1 sería que si y retornamos true.
            $registrosInsertados = $stmt->rowCount();

            if ($registrosInsertados > 0) {
                $idNuevoRegistro = $conexion->lastInsertId();
                return $idNuevoRegistro;
            }
        }
    } catch (PDOException $ex) {
        return false;
    }

    return false;
}