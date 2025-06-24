<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//Enlazamos la conexión con la base de datos para poder pasarla por parámetro y que no la cree. 
require_once __DIR__.'/../funciones/conectarBaseDatos.php';

function obtenerListaProductos(PDO $conexion, $categoria = null): array | false
{
        //He hecho un array con las categorías permitidas, para que si se marca la errónea, lo de como error.
        $categoriasProductos = ['lacteos','conservas','bebidas','snacks','dulces','otros'];

        if ($categoria !== null){
            //Luego digo que si la categoría no se encuentra en el array de categorias productos, que devuelva un array vacío.
            if(!in_array($categoria, $categoriasProductos)){
                return [];
            } else {
                /*
                Si esta categoría coincide con alguna del array de categoriaProductos hacemos una consulta donde devuelve todos los 
                productos de la tabla que tengan la misma categoría que se ha introducido por parámetro.
                */
                try{
                    $SQL= 'SELECT * FROM productos WHERE categoria = :categoria';
                    $stmt = $conexion ->prepare($SQL);
                    $stmt ->bindValue('categoria', $categoria);

                    if($stmt->execute()){
                        //Aquí especificamos que la salida sea un array asociativo y devuelve este array por la función.
                        $datos = $stmt ->fetchAll(PDO::FETCH_ASSOC);
                        return $datos;
                    }
                }catch(PDOException $ex){
                    return false;
                }
  
            }
        //En caso de que las propiedades esten vacías devolvemos un array todos los productos. 
        } else {
            try{
                $SQL = 'SELECT * FROM productos';
                $stmt = $conexion ->prepare($SQL);
                
                if($stmt->execute()){
                    $todosDatos = $stmt->fetchAll(PDO::FETCH_ASSOC);
                    return $todosDatos;
                }
            }catch(PDOException $ex){
                return false;
            }
        }
        return false;
}

