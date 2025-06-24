<?php
require __DIR__ .'/vendor/autoload.php';

use dwes04\controladores\ControladorMPG;

//Establecemos la conexión con la base de datos, tuve que añadir root y la contraseña porque sino no se me conectaba.
try {
    $pdo = new \PDO('mysql:host=localhost;dbname=dwes04;port=3306', 'root', '');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Error de conexión: " . $e->getMessage());
}

//Creamos smarty para las plantillas y lo que necesitan.
$smarty = new Smarty;
$smarty->template_dir = __DIR__ . '/template';
$smarty->compile_dir = __DIR__ . '/tmp/compiladas';
$smarty->cache_dir = __DIR__ . '/tmp/cache';


/**
 * He creado un switch, donde por defecto se mostrará el controlador que lista los libros, y según la url que contenga get
 * utilizará unos controladores u otros. A todos los controladores le añado la $pdo y $smarty.
 */
switch ($_GET['accion'] ?? '') {
    case 'nuevo_libro_form_MPG':
        ControladorMPG::nuevoLibro($pdo, $smarty);
        break;

    case 'crear_libro_MPG':
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            ControladorMPG::crearLibro($pdo, $smarty);
        }
        break;

    case 'borrar_libro_MPG':
            ControladorMPG::borrarLibro($pdo, $smarty);
        break;

    default:
        ControladorMPG::listarLibros($pdo, $smarty); 
        break;
}