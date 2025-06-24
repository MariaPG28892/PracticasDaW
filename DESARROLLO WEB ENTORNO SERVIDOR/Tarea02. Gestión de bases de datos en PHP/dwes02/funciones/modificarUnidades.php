<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//enlazamos la conexión a la base de datos para poder pasarlo por parámetro y que no cree una nueva conexión.
require_once __DIR__.'/conectarBaseDatos.php';

function modificarUnidades(PDO $conexion, $id, $unidadesmodificar): bool | int
{
    /*
    Consulta con UPDATE, aquí he añadido a la unidades que ya hay "+ :unidades a modificar".
    Estas unidades se sumaran si son positivas o si son negativas se restaran a las que ya hay, cuando coincida el mismo id.
    */
    $SQL='UPDATE productos SET unidades = unidades + :unidadesmodificar WHERE id = :id';
    try{
        $stmt = $conexion->prepare($SQL);
        $stmt->bindValue(':unidadesmodificar', $unidadesmodificar);
        $stmt->bindValue(':id', $id, PDO::PARAM_INT);

        if($stmt->execute()){
            //aquí lo que comprobamos es cuantos resgistros han sido modificados. Si han sido más de 0 devolvemos true, en otro caso false.
            $registrosAfectados = $stmt->rowCount();
            if($registrosAfectados > 0){
                return true;
            }
        }
    }catch(PDOException $ex){
        if ($ex->getCode() === '23000') { // Busqué el código SQLSTATE genérico para violaciones de integridad.
            return -1; 
            /*
            Cuando el error que se genere sea 23000, lo que hará es retornar -1, esto quiere decir que la cantidad resultante será negativa
            y no se realizará la modificación.
            */
        }
        // Otras excepciones
        return false;
    }
    return false;

}