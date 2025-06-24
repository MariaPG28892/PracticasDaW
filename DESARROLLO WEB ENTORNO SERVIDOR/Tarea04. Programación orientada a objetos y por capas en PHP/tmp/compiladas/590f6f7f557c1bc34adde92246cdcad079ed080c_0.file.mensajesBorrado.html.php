<?php
/* Smarty version 4.5.5, created on 2025-03-05 20:41:14
  from 'C:\xampp\htdocs\dwes04\template\mensajesBorrado.html' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '4.5.5',
  'unifunc' => 'content_67c8a8dae6dff7_04121349',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '590f6f7f557c1bc34adde92246cdcad079ed080c' => 
    array (
      0 => 'C:\\xampp\\htdocs\\dwes04\\template\\mensajesBorrado.html',
      1 => 1741198630,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_67c8a8dae6dff7_04121349 (Smarty_Internal_Template $_smarty_tpl) {
?><!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado de Borrado</title>
    <style>
        body { 
            font-family: 'Times New Roman', Times, serif;
            margin: 20px; 
        }
        h2 { 
            color: darkcyan; 
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
    <h2>Libro Borrado</h2>
    <!--Metemos el mensaje de si se ha borrado el libro o si hay algún error-->
    <p class="mensaje"><?php echo $_smarty_tpl->tpl_vars['mensaje']->value;?>
</p>
    <br>
    <!--He añadido un botón enlace para volver al listado una vez que ya se ha borrado y no tener que cambiar la url-->
    <a href="index.php" style="text-decoration: none;">
        <button type="button">Volver al listado</button>
    </a>
</body>
</html>
<?php }
}
