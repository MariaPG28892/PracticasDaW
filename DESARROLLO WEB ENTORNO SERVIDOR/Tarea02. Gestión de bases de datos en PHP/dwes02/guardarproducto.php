<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//Enlazamos las dos funciones que vamos a utilizar por medio de la ruta. No he puesto dir porque me daba error y así me funcionaba.
require_once 'funciones/registrarproducto.php';
require_once 'funciones/conectarBaseDatos.php';

//Hacemos un array para almacenar los errores que se generen y luego podamos mostrarlos todos.
$errores = [];

//LLamamos a la función para conectarnos a la base de datos y hacemos un condicional en caso de que no haya conexión que acabe el programa.
$conexion = conectarBaseDatos();

if (!$conexion) {
    die("Error al conectar con la base de datos.");
}

// Solo procesamos los datos si se ha enviado el formulario y no está vacío.
if (!empty($_POST)) {

    //VALIDACIÓN Y SANITIZACIÓN DEL NOMBRE 
    /*
    Primero pasamos la variable nombre por input filter para saber que está en POST, el nombre que tiene en el POST y sanitizamos con especial
    chars, que quita caracteres especiales, como elimina "<>", no he puesto strip_tag para eliminar las etiquetas html más tarde. Luego comprobamos
    que no es null, ni false y en else desarrollamos lo demás.
    */
    $nombre = filter_input(INPUT_POST, 'nombre', FILTER_SANITIZE_FULL_SPECIAL_CHARS);
    if($nombre === null){
        $errores[] = "El nombre no se envió por POST";
    }else if($nombre === false){
        $errores[] = "El nombre del producto no es válido";
    }else{
        //Eliminamos los espacios y lo ponemos todo en minúscula.
        $nombre = strtolower($nombre);
        $nombre = trim($nombre);

        /*
        Luego hacemos un condicional donde si el nombre esta vacío de un error y diga que no puede estar vacío. Y si el nombre
        contiene más de 30 caracteres no sería valido. He puesto 30 caracteres fijándome en VARCHAR de la base de datos.
        */
        if (empty($nombre)) {
            $errores[] = "Debe introducir un nombre del producto, no puede estar vacío.";
        } elseif (strlen($nombre) > 30) {
            $errores[] = "El nombre supera los 30 caracteres posibles.";
        }
    }
    

    // VALIDACIÓN Y SANITIZACIÓN DEL CÓDIGO EAN
    /* 
    Saneamos la variable codigo_ean con filter input, en este caso usamos sanitize_number_int que lo sanitiza y lo convierte en número entero
    luego comprobamos que no sea nulo, ni falso y si pasa esto, seguimos en el false.
    */
    $codigo_ean = filter_input(INPUT_POST, 'codigo_ean', FILTER_SANITIZE_NUMBER_INT); // Sanitiza como número entero
    if($codigo_ean === null){
        $errores[] = "El código ean no se envió por POST";
    }else if($codigo_ean === false){
        $errores[] ="El código ean del producto no es válido";
    } else {
        $codigo_ean = trim($codigo_ean); // Elimina espacios en blanco

        //Aquí cubrimos que no se pueda dejar el campo vacío y que el código sea numérico de 8 a 13 dígitos.
        if (empty($codigo_ean)) {
            $errores[] = "Debe introducir un código EAN, no puede estar vacío.";
        } elseif (!filter_var($codigo_ean, FILTER_VALIDATE_REGEXP, [
            "options" => [ "regexp" => "/^\d{8,13}$/" ]])) { 
            $errores[] = "El código EAN debe tener entre 8 y 13 dígitos numéricos.";
        }

        /*
        Una vez hemos validado todo, decimos que si errores esta vacío, busque en nuestra base de datos si existe ya el código ean
        que el usuario ha compartido, para ello hacemos una consulta, que cuente todos los productos que tengan el código introducido,
        si es más de 0, dará un error, sino seguirá el código.
        */
        if (empty($errores)) {
            try {
                $SQL = 'SELECT COUNT(*) FROM productos WHERE codigo_ean = :codigo_ean';
                $stmt = $conexion->prepare($SQL);
                $stmt->bindValue(':codigo_ean', $codigo_ean);
                $stmt->execute();

                if ($stmt->fetchColumn() > 0) {
                    $errores[] = "El código EAN ya existe. Debe ser único.";
                }
            } catch (PDOException $e) {
                $errores[] = "Error al comprobar el código EAN: " . $e->getMessage();
            }
        }
    }
    
    //VALIDAR CATEGORÍA Y SANITIZARLA
    /*
    En categoría seguimos el mismo procedimiento que en las anteriores, como solo recibe una categoría he optado por usar sanitize full
    spcial chars, en lugar de sanitizarla como array. Luego cubrimos si es null o false, y en el else, introducimos las categorias permitidas
    si alguna de la seleccionada no se encuentra en el array, mandaremos un error de categoría no válida.
    */
   
    $categoria = filter_input(INPUT_POST, 'categoria', FILTER_SANITIZE_FULL_SPECIAL_CHARS);

    if($categoria === null){
        $errores[] = "La categoría no se envió por POST";

    } else if($categoria === false){
        $errores[] = "La categoría no es válida";
    } else{
        $categoriasPermitidas = ['lacteos', 'conservas', 'bebidas', 'snacks', 'dulces', 'otros'];
        if (!in_array($categoria, $categoriasPermitidas)) {
            $errores[] = "La categoría seleccionada no es válida.";
        }    
    }

    //VALIDAR Y SANITIZAR PROPIEDADES.
    /*
    De nuevo, seguimos los mismos pasos, pero en este caso como podemos elegir varias opciones, lo vamos a tratar como un array, filtrandolo
    como tal. Aquí no podemos usar el if($propiedades === null), porque esta sección puede estar vacía. 
    */
    $propiedades = filter_input(INPUT_POST, 'propiedades', FILTER_DEFAULT, FILTER_REQUIRE_ARRAY);
    
    /*Decimos que si no es null, creamos un array con las propiedades permitidas y un array de propiedades válidas vacio. Creamos un bucle
    que recorra nuestro array y saque la propiedad y la compare con las permitidas, si están permitidas las mete en el propiedades válidas
    si no está permitida lanza un error. */
    if ($propiedades !== null) {
        $propiedadesPermitidas = ['sin gluten', 'sin lactosa', 'vegano', 'orgánico', 'sin conservantes', 'sin colorantes'];
        $propiedadesValidas = [];
    
        foreach ($propiedades as $propiedad) {
            if (in_array($propiedad, $propiedadesPermitidas)) {
                $propiedadesValidas[] = $propiedad; // Solo agregar propiedades válidas
            } else {
                $errores[] = "La propiedad '$propiedad' no es válida."; // Error si la propiedad no es válida
            }
        }
    
        // Si no hay propiedades válidas, asignar un valor por defecto
        if (empty($propiedadesValidas)) {
            $propiedades = null; // Puede ser NULL si no hay propiedades, o un array vacío
        } else {
            // Convertir el array de propiedades válidas a una cadena separada por comas
            $propiedades = implode(',', $propiedadesValidas);
        }

    } else {
        // Si no se han seleccionado propiedades, asegurarse de que 'propiedades' esté vacío o nulo
        $propiedades = null; // Si no hay propiedades seleccionadas, asigna un valor nulo
    }

    //VALIDAR Y SANITIZAR UNIDADES.
    /* 
    Seguimos el mismo procedimiento que en los anteriores, esta vez usamos sanitize number int para santizarlo y convertirlo en un número
    entero, cubrimos si es null o false con los errores correspondientes y en el else, especificamos, que el campo no puede estar vacio y que
    tiene que ser superior a 0.
    */
    $unidades = filter_input(INPUT_POST, 'unidades', FILTER_SANITIZE_NUMBER_INT);
    if($unidades === null){
        $errores[] = "Las unidades no se enviaron por POST";
    } else if($unidades === false){
        $errores[] = "Las unidades no son válidas";
    } else {
        if (empty($unidades) || $unidades <= 0) {
            $errores[] = "Las unidades deben ser un número entero mayor a cero.";
        }
    }

    //VALIDAR PRECIO
    /*
    Hacemos el mismo proceso que en el anterior, pero en este utilizamos un sanitize number float y flag allow faction, para que el usuario
    pueda introducir número decimales sin problema. También evitamos que el campo no esté vacio y que sea mayor que 0.
    */
    $precio = filter_input(INPUT_POST, 'precio', FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION);
    if($precio === null){
        $errores[]="El precio no se envió por POST";
    } else if($precio === false){
        $errores[]="El precio no es válido";
    } else {
        if (empty($precio) || $precio < 0) {
            $errores[] = "El precio debe ser un número decimal mayor o igual a cero.";
        }
    }
    

    /*
    Cuando terminamos de filtrar todos los parámetros introducidos, decmimos que si no hay errores cree el array producto con 
    todos los datos filtrados.
    */
    if (empty($errores)) {

        $producto = [
            'nombre' => $nombre,
            'codigo_ean' => $codigo_ean,
            'categoria' => $categoria,
            'propiedades' => $propiedades,
            'unidades' => $unidades,
            'precio' => $precio,
        ];

        //Lo he llamado id porque esta función devuelve el id si el producto se ha registrado. Le pasamos la conexión y el array.
        $id = registrarProducto($conexion, $producto);

        //aquó contralamos que si se guarde lo notifique y si no produzca un error. 
        if ($id) {
            echo "<br><br>Producto guardado correctamente. ID del producto: " . $id;
        } else {
            $errores[] = "Error al guardar el producto.";
        }
    }
}

// En el caso de que hay errores lo muestra como un lista y vuelve a cargar el formulario.
if (!empty($errores)) {
    echo "<ul>";
    foreach ($errores as $error) {
        echo "<li>" . htmlspecialchars($error) . "</li>";
    }
    echo "</ul>";
    require_once 'nuevoProducto.php'; // Mostrar formulario de nuevo si hay errores
}
