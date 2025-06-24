<?php
namespace dwes04\modelo;

use dwes04\modelo\IGuardableMPG;

/**
 * 
 * Clase Libro
 *
 * La clase libro representa la creación de un libro, estableciendo sus atributos como son el id, el isbn, titulo, autor, año de publicación,
 * páginas, ejemplares disponibles, fecha de creación y fecha de actualización. Esta clase además contendrá métodos implementados por la interfaz
 * IGuardable, que serán guardar(), para guardar los libros en la base de datos, rescatar para consultar la base de datos y borrar para borrar
 * algún registro de la base de datos. También tiene sus propios getters y sus setters.
 *
 * @author María Palomares Gallo.
 */
class Libro implements IGuardableMPG
{
    private ?int $id=null;
    private ?string $isbn;
    private ?string $titulo;
    private ?string $autor;
    private ?int $anio_publicacion;
    private ?int $paginas;
    private ?int $ejemplares_disponibles;
    private ?string $fecha_creacion;
    private ?string $fecha_actualizacion;


    /**
    * SETTERS. Se utilizan para introducir la información sobre el libro ya que en este caso no tenemos un constructor y hay que
    * introducirlos uno a uno.
    */

    public function setIsbn(string $isbn){
        $this->isbn = $isbn;
    }

    public function setTitulo($titulo){
        $this->titulo = $titulo;
    }

    public function setAutor($autor){
        $this->autor = $autor;
    }

    public function setAnio_publicacion($anio_publicacion){
        $this->anio_publicacion = $anio_publicacion;
    }

    public function setPaginas($paginas){
        $this->paginas = $paginas;
    }

    public function setEjemplaresDisponibles($ejemplares_disponibles){
        $this->ejemplares_disponibles = $ejemplares_disponibles;
    }

    /**
    * GETTERS. Se utilizan para extraer información sobre el objeto de forma pública, es decir, se puede hacer desde 
    * diferentes archivos.
    */

    public function getId():?int{
        return $this->id ?? null;
    }

    public function getFechaCreacion():string{
        return $this->fecha_creacion;
    }

    public function getFechaActualizacion():string{
        return $this->fecha_actualizacion;
    }

    public function getIsbn():string{
        return $this->isbn;
    }

    public function getTitulo():string{
        return $this->titulo;
    }

    public function getAutor():string{
        return $this->autor;
    }

    public function getAnioPublicacion():int{
        return $this->anio_publicacion;
    }

    public function getPaginas():int{
        return $this->paginas;
    }

    public function getEjemplaresDisponibles():int{
        return $this->ejemplares_disponibles;
    }

    /**
    * Realiza el guardado de un libro dentro de la base de datos con sus respectivos atributos.
    *
    * @param PDO $pdo Aporta el parámetro que contiene la conexión para poder enlazarse con las base de datos y poder 
    * guardar los libros que estemos insertando por los setters. 
    * @return bool True si la operación fue exitosa, false de lo contrario y -2 si no se establecce conexión con la base de datos.
    */
    public function guardar(\PDO $pdo):int | bool {

        $SQL = 'INSERT INTO libros (isbn, titulo, autor, anio_publicacion, paginas, ejemplares_disponibles)
                VALUES (:isbn, :titulo, :autor, :anio_publicacion, :paginas, :ejemplares_disponibles)';
        try{

            $stmt=$pdo->prepare($SQL);
            $stmt->bindValue(':isbn', $this->isbn);
            $stmt->bindValue(':titulo', $this->titulo);
            $stmt->bindValue(':autor', $this->autor);
            $stmt->bindValue(':anio_publicacion', $this->anio_publicacion);
            $stmt->bindValue(':paginas', $this->paginas);
            $stmt->bindValue(':ejemplares_disponibles', $this->ejemplares_disponibles);

            $stmt->execute();

            if($stmt->rowCount()>0){
                $this->id =$pdo->lastInsertId();

                try {
                    $SQL2 = 'SELECT fecha_creacion, fecha_actualizacion FROM libros WHERE id = :id';
                    $stmt2 = $pdo->prepare($SQL2);
                    $stmt2->bindValue(':id', $this->id);
                    $stmt2->execute();
                
                        $datos = $stmt2->fetch(\PDO::FETCH_ASSOC);
                        
                        if ($datos) {
                            $this->fecha_creacion = $datos['fecha_creacion'];
                            $this->fecha_actualizacion = $datos['fecha_actualizacion'];
                        }
                    
                
                } catch (\PDOException $e) {
                    
                    error_log("Error al obtener las fechas de actualización: " . $e->getMessage());
                }

                return true;
            }
        }catch(\PDOException $e){
            error_log("Error en guardar: " . $e->getMessage()); 
            echo "Error en guardar: " . $e->getMessage();
            return -2;
        }

        return false;
    }

    /**
    * Realiza el rescatar un libro dentro de la base de datos con sus respectivos atributos.
    *
    * @param PDO $pdo Aporta el parámetro que contiene la conexión para poder enlazarse con las base de datos y poder 
    * guardar los libros que estemos insertando por los setters. 
    * @param int $id Para introducir el id del libro que queremos rescatar, ya que será único para cada uno de los datos.
    * @return Libro devuelve una instacia libro ,null si no se ha podido encontrar el id, -2 si no se establecce conexión con la base de datos.
    */
    public static function rescatar(\PDO $pdo, int $id): Libro| null |int{

        $SQL = 'SELECT * FROM libros WHERE id = :id';
        try{
            $stmt=$pdo->prepare($SQL);
            $stmt->bindValue(':id', $id);
            $stmt->execute();

            $datos = $stmt->fetch(\PDO::FETCH_ASSOC);

            if($datos){
                $libro = new Libro();
                $libro->id=$datos['id'];
                $libro->isbn=$datos['isbn'];
                $libro->titulo=$datos['titulo'];
                $libro->autor=$datos['autor'];
                $libro->anio_publicacion=$datos['anio_publicacion'];
                $libro->paginas=$datos['paginas'];
                $libro->ejemplares_disponibles=$datos['ejemplares_disponibles'];
                $libro->fecha_creacion=$datos['fecha_creacion'];
                $libro->fecha_actualizacion=$datos['fecha_actualizacion'];

                return $libro;
            }

            return null;


        }catch(\PDOException $e){
            return -2;
        }
    }
    
    /**
    * Realiza el borrado de un libro dentro de la base de datos con sus respectivos atributos.
    *
    * @param PDO $pdo Aporta el parámetro que contiene la conexión para poder enlazarse con las base de datos y poder 
    * guardar los libros que estemos insertando por los setters. 
    * @param int $id Para introducir el id del libro que queremos rescatar, ya que será único para cada uno de los datos.
    * @return bool true devuelve true si se ha borrado el libro correspondiente ,false si no se ha podido encontrar el id, 
    * -2 si no se establecce conexión con la base de datos.
    */
    public static function borrar(\PDO $pdo, int $id): bool|int{
        $SQL='DELETE FROM libros WHERE id = :id';
        try{
            $stmt = $pdo->prepare($SQL);
            $stmt->bindValue(':id', $id);
            $stmt->execute();
            if($stmt->rowCount()>0){
                return true;
            }
        }catch(\PDOException $e){
            return -2;
        }

        return false;
    }

}