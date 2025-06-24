<?php

require __DIR__ .'/vendor/autoload.php';

use dwes04\modelo\Libro;
use dwes04\modelo\Libros;

//Establecemos conexión con la base de datos.
try {
    $pdo = new \PDO('mysql:host=localhost;dbname=dwes04;port=3306', 'root', '');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Error de conexión: " . $e->getMessage());
}

//Creamos el objeto libro, como no hay constructor se añade manualmente.
$libroPrueba = new Libro();
$libroPrueba->setIsbn('9780140441563');
$libroPrueba->setTitulo('El señor de los anillos');
$libroPrueba->setAutor('J.R.R.Tolkien');
$libroPrueba->setAnio_publicacion('1929');
$libroPrueba->setPaginas('600');
$libroPrueba->setEjemplaresDisponibles('7');

// Guardar el libro en la base de datos
var_dump($libroPrueba->guardar($pdo));
var_dump($libroPrueba->getId());

// Rescatar el libro guardado
$libroPrueba_rescatado = Libro::rescatar($pdo, 9);
var_dump($libroPrueba_rescatado);

//borrar libro con id
$libroBorrado = Libro :: borrar($pdo, $libroPrueba->getId());

//Listar libros por fecha de creación
$listadoLibros = Libros ::listarMPG($pdo, true);
var_dump($listadoLibros);

//listar libros por fecha de actualización.
$listadoLibros2 = Libros ::listarMPG($pdo, false);
var_dump($listadoLibros2);