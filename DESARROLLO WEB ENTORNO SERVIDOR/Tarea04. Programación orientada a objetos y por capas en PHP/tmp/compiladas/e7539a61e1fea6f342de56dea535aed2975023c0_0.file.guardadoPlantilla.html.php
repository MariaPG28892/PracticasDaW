<?php
/* Smarty version 4.5.5, created on 2025-03-05 20:41:05
  from 'C:\xampp\htdocs\dwes04\template\guardadoPlantilla.html' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '4.5.5',
  'unifunc' => 'content_67c8a8d1c347b2_51610733',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    'e7539a61e1fea6f342de56dea535aed2975023c0' => 
    array (
      0 => 'C:\\xampp\\htdocs\\dwes04\\template\\guardadoPlantilla.html',
      1 => 1741200027,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_67c8a8d1c347b2_51610733 (Smarty_Internal_Template $_smarty_tpl) {
?><!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guardar libro</title>
    <style>
        button{
            margin-top: 10px;
            background-color: gray; 
            color: white; 
            border: none; 
            padding: 5px 10px; 
            cursor: pointer; 
        }
    </style>
</head>
<body>
    <!--Si hay errores hacemos un for each para mostrarlos en color rojo, ya que errores es un array y lo ponemos en una lista-->
    <?php if ($_smarty_tpl->tpl_vars['errores']->value) {?>
    <ul>
        <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['errores']->value, 'error');
$_smarty_tpl->tpl_vars['error']->do_else = true;
if ($_from !== null) foreach ($_from as $_smarty_tpl->tpl_vars['error']->value) {
$_smarty_tpl->tpl_vars['error']->do_else = false;
?>
            <li style="color: red;"><?php echo $_smarty_tpl->tpl_vars['error']->value;?>
</li>
        <?php
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl, 1);?>
    </ul>
    <?php }?>

    <!--Por el contrario si hay mensaje, lo compatimos aquí-->
    <?php if ($_smarty_tpl->tpl_vars['mensaje']->value) {?>
        <p style="color: green;"><?php echo $_smarty_tpl->tpl_vars['mensaje']->value;?>
</p>
    <?php }?>

    <!--He puesto dos enlaces, uno para crear un libro nuevo y otro para volver al listado-->
    <a href="index.php?accion=nuevo_libro_form_MPG" style="text-decoration: none;">
        <button type="button">Añadir Nuevo Libro</button>
    </a>
    <a href="index.php" style="text-decoration: none;">
        <button type="button">Volver al listado</button>
    </a>
</body>
</html>

<?php }
}
