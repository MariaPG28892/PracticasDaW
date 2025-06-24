<?php

/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//Enlazamos los archivos que necesitamos.
require_once __DIR__ . '/etc/conf.php';
require_once __DIR__ . '/funciones/conectarBaseDatos.php';
require_once __DIR__ . '/funciones/obtenerListaProductos.php';

//conectamos la base de datos y controlamos que si no hay conexión acabe el programa.
$conexion = conectarBaseDatos();

if ($conexion === false) {
    
    die("Error al conectar a la base de datos.");
}

//Aquí realizamos la función para obtener los productos, pasandole solamete la conexión. Controlamos que si es igual a false, de un error.
$productos = obtenerListaProductos($conexion);

if ($productos === false) {

    echo 'Ocurrió un problema al recuperar los datos de la base de datos. Intenta de nuevo más tarde.';
}

//Inicializamos la categoría si esta en el formulario y si no le asignamos null como prederminada.
$categoria = $_POST['categoria'] ?? null;

// Aquí controlamos que si post esta vacio que emplee la funcion para obtener la lista de productos con la conexión y la categoria del formulario.
if (empty($_POST)) {
    echo 'Seleccione una categoría para empezar.';
} else {
    $productos = obtenerListaProductos($conexion, $categoria);
}



?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tabla de productos</title>
    <!--Hoja de estilos interna para hacerlo un poco más estético-->
    <style>
        table{
            border: 1px solid black;
            border-collapse: collapse;
            width: 100%;
        }

        table th, table td{
            border: 1px solid black;
            padding: 5px;
            text-align: center;
        }

        table th {
            background-color: #4CAF50;
            color: white;
        }

        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table tr:hover {
            background-color: #ddd;
        }

        h3{
            color:#4CAF50;
        }
    </style>
</head>
<body>

    <br><br>
    <h3>Selecciona una categoría</h3>
    <form action="productos.php" method="POST">
        <label for="categoria">Categoría</label>
            <select name="categoria" id="categoria">
    <!--Busque como dejar la categoría seleccionada en el formulario select con php y encontré esto, para que al buscar no volviera
    a lácteos y quedara mas claro.-->
                <option value="lacteos" <?= $categoria === 'lacteos' ? 'selected' : '' ?>>Lácteos</option>
                <option value="conservas" <?= $categoria === 'conservas' ? 'selected' : '' ?>>Conservas</option>
                <option value="bebidas" <?= $categoria === 'bebidas' ? 'selected' : '' ?>>Bebidas</option>
                <option value="snacks" <?= $categoria === 'snacks' ? 'selected' : '' ?>>Snacks</option>
                <option value="dulces" <?= $categoria === 'dulces' ? 'selected' : '' ?>>Dulces</option>
                <option value="otros" <?= $categoria === 'otros' ? 'selected' : '' ?>>Otros</option>
            </select>
            <br><br>
            <button type="submit">Enviar</button>
    </form>
    <br><br>
    <!--Rellenamos la tabla con el array productos-->
    <?php if (is_array($productos)): ?>
    <table>
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>CÓDIGO EAN</th>
            <th>CATEGORÍA</th>
            <th>PROPIEDADES</th>
            <th>UNIDADES</th>
            <th>PRECIO</th>
            <th>OPERACIONES</th>
        </tr>
    <!--Utilizamos un for each para ir producto por producto y luego vamos sacando cada valor del array.-->
        <?php foreach($productos as $producto): ?>
        <tr>
            <td><?=$producto['id']?></td>
            <td><?=$producto['nombre']?></td>
            <td><?=$producto['codigo_ean']?></td>
            <td><?=$producto['categoria']?></td>
            <td><?=$producto['propiedades']?></td>
            <td><?=$producto['unidades']?></td>
            <td><?=$producto['precio']?></td>
            <td>
        <!--Introducimos los campos para modificar producto y el campo hidden para el id-->
                <form action="modificarproducto.php" method="POST">
                    <input type="hidden" name="idmodificar" value="<?=$producto['id']?>">
                    <label for="unidadesmodificar">Unidades (incremento/decremento):</label>
                    <input type="text" id="unidadesmodificar" name="unidadesmodificar" required>
                    <br><br>
                    <button type="submit">Modificar</button>
                </form>
        <!--Formulario para eliminar los productos hidden para sacar el id y solo un botón para eliminar. -->
                <form action="eliminar.php" method="POST">
                    <input type="hidden" name="ideliminar" value="<?=$producto['id']?>">
                    <br><br>
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
        <?php endforeach; ?>
    </table>
        <!--Sacar los errores de conexión.-->
    <?php elseif ($productos === false): ?>
        <p>Error al obtener los productos de la base de datos.</p>
    <?php elseif ($productos === null): ?>
        <p>Selecciona una categoría para ver los productos.</p>
    <?php else: ?>
        <p>No se encontraron productos para la categoría seleccionada.</p>
    <?php endif; ?>

   
</body>
</html>

