<?php
/* Smarty version 4.5.5, created on 2025-03-05 20:40:51
  from 'C:\xampp\htdocs\dwes04\template\formularioLibro.html' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '4.5.5',
  'unifunc' => 'content_67c8a8c340d8f1_22872724',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '174ce938b10a6efa7564353cdf1547003607f3f2' => 
    array (
      0 => 'C:\\xampp\\htdocs\\dwes04\\template\\formularioLibro.html',
      1 => 1741200492,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_67c8a8c340d8f1_22872724 (Smarty_Internal_Template $_smarty_tpl) {
?><!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Nuevo Libro</title>
    <style>
        body { 
            font-family: 'Times New Roman', Times, serif; 
            margin: 20px; 
        }
        h2 { 
            color: darkcyan; 
        }
        form div {
            margin-bottom: 10px;
        }
        input[type="submit"] { 
            background-color: gray; 
            color: white; 
            border: none; 
            padding: 5px 10px; 
            cursor: pointer; 
        }
        label{
            font-weight: bold;
        }

        button{
            background-color: gray; 
            color: white; 
            border: none; 
            padding: 5px 10px; 
            cursor: pointer; 
        }
    </style>
</head>
<body>

<h2>Nuevo Libro</h2>
<!--Creamos un formulario donde ponemos todos los datos que queremos recoger y lo mandamos a crear libro por el método post-->
<form action="index.php?accion=crear_libro_MPG" method="post">
    <div>
        <label for="isbn">ISBN:</label>
        <input type="text" name="isbn" id="isbn">
    </div>
    
    <div>
        <label for="titulo">Título:</label>
        <input type="text" name="titulo" id="titulo">
    </div>

    <div>
        <label for="autor">Autor:</label>
        <input type="text" name="autor" id="autor">
    </div>

    <div>
        <label for="anio_publicacion">Año de publicación:</label>
        <input type="text" name="anio_publicacion" id="anio_publicacion">
    </div>

    <div>
        <label for="paginas">Páginas:</label>
        <input type="text" name="paginas" id="paginas">
    </div>

    <div>
        <label for="ejemplares_disponibles">Ejemplares disponibles:</label>
        <input type="text" name="ejemplares_disponibles" id="ejemplares_disponibles">
    </div>

    <!--Dos botones un input para crear un libro y un enlace para llevar de nuevo al index, no lo pedía pero me parecía correcto-->
    <input type="submit" value="Crear Libro">

    <a href="index.php" style="text-decoration: none;">
        <button type="button">Cancelar</button>
    </a>

    
</form>

</body>
</html><?php }
}
