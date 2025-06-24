<?php
/**
 * Nombre: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 * Tarea: Trabajar con bases de datos en PHP.
 */

//He enlazado los archivos necesarios.
require_once __DIR__.'/funciones/registrarProducto.php';
require_once 'guardarproducto.php';

//Tuve que inicializar categoria porque me mostraba error, la inicialice y me va bien.
$categoria = "";

?>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo producto</title>
</head>
<body>
    <form action="guardarproducto.php" method="POST">

        <!--FORMULARIO NOMBRE-->
        <label for="nombre">Nombre del producto: </label>
        <input type="text" id="nombre" name="nombre"><br><br>
        
        <!--FORMULARIO CÓDIGO EAN-->
        <label for="codigo_ean" id="codigo_ean">Código Ean: </label>
        <input type="text" id="codigo_ean" name="codigo_ean"><br><br>

        <!--FORMULARIO CATEGORÍA-->
        <label for="categoria">Categoría: </label>
        <select name="categoria" id="categoria">
            <option value="lacteos" <?= $categoria === 'lacteos' ? 'selected' : '' ?>>Lácteos</option>
            <option value="conservas" <?= $categoria === 'conservas' ? 'selected' : '' ?>>Conservas</option>
            <option value="bebidas" <?= $categoria === 'bebidas' ? 'selected' : '' ?>>Bebidas</option>
            <option value="snacks" <?= $categoria === 'snacks' ? 'selected' : '' ?>>Snacks</option>
            <option value="dulces" <?= $categoria === 'dulces' ? 'selected' : '' ?>>Dulces</option>
            <option value="otros" <?= $categoria === 'otros' ? 'selected' : '' ?>>Otros</option>
            <option value="opcionIncorrecta" <?= $categoria === 'opcionIncorrecta' ? 'selected' : '' ?>>Opción Incorrecta</option>
        </select>
        
        <!--FORMULARIO PROPIEDADES-->
        <p>Propiedades: </p>
        <label><input type="checkbox" name="propiedades[]" value="sin gluten">Sin gluten</label>
        <label><input type="checkbox" name="propiedades[]" value="sin lactosa">Sin lactosa</label>
        <label><input type="checkbox" name="propiedades[]" value="vegano">Vegano</label>
        <label><input type="checkbox" name="propiedades[]" value="orgánico">Orgánico</label>
        <label><input type="checkbox" name="propiedades[]" value="sin conservantes">Sin conservantes</label>
        <label><input type="checkbox" name="propiedades[]" value="sin colorantes">Sin colorantes</label>
        <label><input type="checkbox" name="propiedades[]" value="opcionIncorrecta">Opción Incorrecta</label><br><br>

        <!--FORMULARIO UNIDADES-->
        <label for="unidades">Unidades del producto: </label>
        <input type="text" id="unidades" name="unidades"><br><br>

        <!--FORMULARIO PRECIO-->
        <label for="precio">Precio del producto: </label>
        <input type="text" id="precio" name="precio">

        <!--BOTÓN-->
        <br><br>
        <button type="submit">Enviar</button>
    </form>
</body>
</html>