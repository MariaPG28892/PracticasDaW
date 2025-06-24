<?php

namespace dwes04\controladores;
use dwes04\modelo\Libro;
use dwes04\modelo\Libros;

class ControladorMPG{

    /**
    * Función del controlador, donde tenemos un formulario que elegirá el tipo de orden que queremos para nuestro listado de libros,
    * dentro de este comprobaremos lo elegido por el usuario, por defecto lo ordenaremos por fecha de actualización. Utilizaremos la 
    * clase libros para obtener el listado y luego le asignaremos la plantilla de smarty donde hemos creado la tabla con los libros.
    *
    * @param PDO $pdo Aporta el parámetro que contiene la conexión para poder enlazarse con las base de datos y mostrar los libros almacenados.
    * @param SMARTY $smarty donde le mandaremos la información para crear las plantillas y sacarlas desde el index.
    */
    public static function listarLibros(\PDO $pdo, \Smarty $smarty) {
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $seleccion = filter_input(INPUT_POST, 'seleccion', FILTER_SANITIZE_SPECIAL_CHARS);
            $ordenFecha = ($seleccion === 'creacion');
        } else {
            $ordenFecha = false;
        }
    
        // Obtener listado de libros
        $listado = Libros::listarMPG($pdo, $ordenFecha);
    
        // Asignar a Smarty
        $smarty->assign('libros', $listado);
        //Sacar la pantilla de Smarty para el index.php
        $smarty->display('formulario.html'); 
    }

    /**
     * Función del controlador para mostrar el formulario para crear un nuevo libro, el cual, tiene dos parámetros uno con la conexión y otro con las 
     * propiedades que tiene que usar smarty.
     */
    public static function nuevoLibro(\PDO $pdo, \Smarty $smarty){

        $smarty->display('formularioLibro.html'); 
    }

    /**
    * Función del controlador, donde vamos a crear un libro utilizando los datos recogidos del formulario anterior.
    *
    * @param PDO $pdo Aporta el parámetro que contiene la conexión para poder enlazarse con las base de datos y mostrar los libros almacenados.
    * @param SMARTY $smarty donde le mandaremos la información para crear las plantillas y sacarlas desde el index.
    */
    public static function crearLibro(\PDO $pdo, \Smarty $smarty) {
        $errores = [];
        $mensaje = "";
    
        //Comprobamos que haya un post, si lo hay comenzamos a validar.
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
            //No he usado la clase petición me parecía más sencillo validar y sanitizar por mi misma con lo aprendido en los temas anteriores.
            //Usamos filter input con los datos que hemos recogido del formulario.
            $isbn = filter_input(INPUT_POST, 'isbn', FILTER_SANITIZE_SPECIAL_CHARS);
            $titulo = filter_input(INPUT_POST, 'titulo', FILTER_SANITIZE_SPECIAL_CHARS);
            $autor = filter_input(INPUT_POST, 'autor', FILTER_SANITIZE_SPECIAL_CHARS);
            $anio_publicacion = filter_input(INPUT_POST, 'anio_publicacion', FILTER_VALIDATE_INT);
            $paginas = filter_input(INPUT_POST, 'paginas', FILTER_VALIDATE_INT);
            $ejemplares_disponibles = filter_input(INPUT_POST, 'ejemplares_disponibles', FILTER_VALIDATE_INT);
    
            // Validación del ISNB. He usado cype_digit porque no permite números decimales, si lo pongo con is_integer sí.
            if ($isbn === null || empty(trim($isbn)) || strlen($isbn) !== 13 || !ctype_digit($isbn)) {
                $errores[] = "El ISBN debe tener 13 dígitos numéricos.";
            }
    
            // Validación del título
            if ($titulo === null || empty(trim($titulo))) {
                $errores[] = "El título no puede estar vacío ni ser nulo.";
            }
    
            // Validación del autor
            if ($autor === null || empty(trim($autor))) {
                $errores[] = "El autor no puede estar vacío ni ser nulo.";
            }
    
            // Validación del año de publicación. date('Y') contiene el dato del año actual.
            if ($anio_publicacion === false || $anio_publicacion < 0 || $anio_publicacion > date('Y')) {
                $errores[] = "El año de publicación debe ser un número válido entre 0 y el año actual.";
            }
    
            // Validación del número de páginas. Aquí he usado false, porque el filter input devolverá false si no es un numero entero.
            if ($paginas === false || $paginas < 1) {
                $errores[] = "El número de páginas debe ser un entero mayor que 0.";
            }
    
            // Validación del número de ejemplares disponibles. Igual que en el anterior con false.
            if ($ejemplares_disponibles === false || $ejemplares_disponibles < 0) {
                $errores[] = "El número de ejemplares disponibles debe ser un entero mayor o igual a 0.";
            }
    
            // Si no hay errores, creamos el libro, como no hay constructor, lo hacemos paso a paso.
            if (empty($errores)) {
                $libro = new Libro();
                $libro->setIsbn($isbn);
                $libro->setTitulo($titulo);
                $libro->setAutor($autor);
                $libro->setAnio_publicacion($anio_publicacion);
                $libro->setPaginas($paginas);
                $libro->setEjemplaresDisponibles($ejemplares_disponibles);
    
                //Usamos el método guardar y obtenemos el id para mostrarlo en el mensaje.
                $libro->guardar($pdo);
                $idLibro = $libro->getId(); 
                $mensaje = "Libro guardado con éxito. ID del libro: ".$idLibro;
            }
        }
    
        // Pasar mensaje y errores a la vista
        $smarty->assign('mensaje', $mensaje);
        $smarty->assign('errores', $errores);
        //añadimos la plantilla
        $smarty->display('guardadoPlantilla.html');  
    }

     /**
    * Función del controlador, donde vamos a borrar el libro seleccionado por el botón de borrar.
    *
    * @param PDO $pdo Aporta el parámetro que contiene la conexión para poder enlazarse con las base de datos y mostrar los libros almacenados.
    * @param SMARTY $smarty donde le mandaremos la información para crear las plantillas y sacarlas desde el index.
    */
    public static function borrarLibro(\PDO $pdo, \Smarty $smarty) {
        // Obtener el ID del libro enviado por POST y lo validamos.
        $id = filter_input(INPUT_POST, 'id', FILTER_VALIDATE_INT);
        // Intentamos obtener el valor del checkbox de confirmación y lo validamos.
        $confirmacion = filter_input(INPUT_POST, 'confirmacion', FILTER_SANITIZE_STRING);
        
        //Si el id esta vacío se mandará un mensaje con un error.
        if (empty($id)) {
            $smarty->assign('mensaje', "No se ha proporcionado un ID válido.");
            $smarty->display('mensajesBorrado.html');
        } else {
            // Si el ID es válido, continuamos con el codigo, donde verificamos si la confirmacion es nula, mostramos el formulario para confirmarlo.
            if ($confirmacion === null) {
                $smarty->assign('id', $id);
                $smarty->display('confirmacionBorrado.html');
            } else {
                // Si se ha enviado la confirmación, procedemos a borrar el libro
                $resultado = Libro::borrar($pdo, $id);
                if ($resultado === true) {
                    $mensaje = "El libro con ID $id se ha eliminado correctamente.";
                } else {
                    $mensaje = "No se ha podido eliminar el libro con ID $id (posiblemente ya no existe).";
                }
                $smarty->assign('mensaje', $mensaje);
                $smarty->display('mensajesBorrado.html');
            }
        }
    }
    
}