<?php

namespace dwes04\modelo;

use dwes04\modelo\Libro;
use PDO;
use PDOException;

/**
 * Clase Libros
 *
 * Representa un conjunto de libros dentro de la base de datos.
 * Contiene el método listarMPG(), que devuelve una lista de libros.
 *
 * @author María Palomares Gallo
 */
class Libros {

    /**
     * Lista los libros de la base de datos, ordenados por fecha de creación o actualización.
     *
     * @param PDO $pdo Conexión a la base de datos.
     * @param bool $ordenFecha Si es `true`, ordena por fecha de creación; si es `false`, ordena por fecha de actualización.
     * @return Libro[]|int Retorna un array de objetos `Libro` si hay datos, un array vacío si no hay libros, o `-1` en caso de error.
     */
    public static function listarMPG(PDO $pdo, bool $ordenFecha) : array | Libros | int{
        try {
            // Selección de la columna para ordenar los libros
            $ordenFinal = $ordenFecha ? "fecha_creacion" : "fecha_actualizacion";

            $SQL = "SELECT * FROM libros ORDER BY $ordenFinal DESC";
            $stmt = $pdo->prepare($SQL);
            $stmt->execute();

            $datos = $stmt->fetchAll(PDO::FETCH_ASSOC);
            
            if (!$datos) {
                return [];
            }

            // Crear las instancias de Libro usando rescatar() y guardando cada libro.
            $libros = [];
            foreach ($datos as $fila) {
                if (isset($fila['id'])) {
                    $libro = Libro::rescatar($pdo, $fila['id']);
                    if ($libro !== null) {
                        $libros[] = $libro;
                    } else {
                        error_log("No se pudo rescatar el libro con id " . $fila['id']);
                    }
                } else {
                    error_log("Fila sin ID encontrada en la base de datos.");
                }
            }

            return $libros;

        } catch (PDOException $e) {
            return -1;
        }
    }
}
