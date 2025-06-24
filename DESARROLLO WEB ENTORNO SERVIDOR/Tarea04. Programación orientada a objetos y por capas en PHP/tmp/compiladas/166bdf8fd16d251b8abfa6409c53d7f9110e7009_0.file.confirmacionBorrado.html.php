<?php
/* Smarty version 4.5.5, created on 2025-03-05 20:41:11
  from 'C:\xampp\htdocs\dwes04\template\confirmacionBorrado.html' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '4.5.5',
  'unifunc' => 'content_67c8a8d7c15935_00048454',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '166bdf8fd16d251b8abfa6409c53d7f9110e7009' => 
    array (
      0 => 'C:\\xampp\\htdocs\\dwes04\\template\\confirmacionBorrado.html',
      1 => 1741201049,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_67c8a8d7c15935_00048454 (Smarty_Internal_Template $_smarty_tpl) {
?><!-- confirmar_borrado.tpl -->
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Confirmar Borrado</title>
  <style>
    body { 
        font-family: 'Times New Roman', Times, serif;
        margin: 20px; 
    }
    label { 
        margin-right: 10px; 
    }
    h2{
        color: darkcyan; 
    }
  </style>
</head>
<body> 
    <h2>Confirmar Borrado</h2>
    <p>¿Estás seguro de que deseas eliminar el libro con ID: <?php echo $_smarty_tpl->tpl_vars['id']->value;?>
?</p>
    <!--Formulario para hacer el checkbox, lo mandamos a borrar libros con método post-->
    <form action="index.php?accion=borrar_libro_MPG" method="post" style="display: flex; gap: 10px; align-items: center;">
        <!--Guardamos el id también-->
        <input type="hidden" name="id" value="<?php echo $_smarty_tpl->tpl_vars['id']->value;?>
">
        <label>
            <input type="checkbox" name="confirmacion" value="si"> Confirmar
        </label>
        <br><br>
        <!--De nuevo he añadido dos botones uno para borrar y otro para cancelar, he incluido el estilo en la misma etiqueta para no
        hacer el código más largo con el css.-->
        <input type="submit" value="Aceptar" style="background-color: red; color: white; border: none; padding: 5px 10px; cursor: pointer;">
        
        <a href="index.php" style="text-decoration: none;">
        <button type="button" style="background-color: gray; color: white; border: none; padding: 5px 10px; cursor: pointer;">Cancelar</button>
      </a>
    </form>
</body>
</html>
  <?php }
}
