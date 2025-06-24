<?php
/* Smarty version 4.5.5, created on 2025-03-05 20:18:04
  from 'C:\xampp\htdocs\dwes04\template\formulario.html' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '4.5.5',
  'unifunc' => 'content_67c8a36c8245c0_81536400',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    'a96a7ad5ab3c5d0c5ecd68c14130d2a499e6e509' => 
    array (
      0 => 'C:\\xampp\\htdocs\\dwes04\\template\\formulario.html',
      1 => 1741200591,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_67c8a36c8245c0_81536400 (Smarty_Internal_Template $_smarty_tpl) {
?><!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Libros</title>
    <style>
        body { 
            font-family: 'Times New Roman', Times, serif; 
            margin: 20px; 
        }
        h2 { 
            color: darkcyan; 
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
        }
        th, td { 
            border: 1px solid black; 
            padding: 8px; 
            text-align: center; 
        }
        th { 
            background-color: darkcyan; 
            color: antiquewhite; 
            text-align: center;
        }
        td { 
            background-color: antiquewhite;
        }
        form { 
            margin-bottom: 20px; 
        }
        button{
            margin-top: 20px;
            background-color: gray; 
            color: white; 
            border: none; 
            padding: 5px 10px; 
            cursor: pointer; 
        }
        input[type="submit"] { 
            background-color: gray; 
            color: white; 
            border: none; 
            padding: 5px 10px; 
            cursor: pointer;
        }
    </style>
</head>
<body>

    <h2>Listado de Libros</h2>

    <!-- Formulario para ordenar por fecha de actualización o creacion-->
    <form method="post" action="index.php">
        <label for="seleccion">Ordenar por:</label>
        <select name="seleccion" id="seleccion">
            <option value="actualizacion">Fecha de Actualización</option>
            <option value="creacion">Fecha de Creación</option>
        </select>
        <input type="submit" value="Ordenar">
    </form>

    <!-- Mostrar mensaje si no hay libros que mostrar de la base de datos. -->
    <?php if (empty($_smarty_tpl->tpl_vars['libros']->value)) {?>
        <p>No hay libros en la base de datos.</p>
    <?php } else { ?>
        <!-- Tabla con los libros -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ISBN</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Año de publicación</th>
                    <th>Páginas</th>
                    <th>Ejemplares disponibles</th>
                    <th>Fecha de Creación</th>
                    <th>Fecha de Actualización</th>
                    <th>BORRAR LIBRO</th>
                </tr>
            </thead>
            <tbody>
                <!--Hacemos un for each para ir sacando los libros uno a uno e ir sacando los getters de información de cada uno-->
                <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['libros']->value, 'libro');
$_smarty_tpl->tpl_vars['libro']->do_else = true;
if ($_from !== null) foreach ($_from as $_smarty_tpl->tpl_vars['libro']->value) {
$_smarty_tpl->tpl_vars['libro']->do_else = false;
?>
                    <tr>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getId();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getIsbn();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getTitulo();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getAutor();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getAnioPublicacion();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getPaginas();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getEjemplaresDisponibles();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getFechaCreacion();?>
</td>
                        <td><?php echo $_smarty_tpl->tpl_vars['libro']->value->getFechaActualizacion();?>
</td>
                        <td>
                            <!-- Formulario para borrar el libro -->
                            <form action="index.php?accion=borrar_libro_MPG" method="post">
                                <input type="hidden" name="id" value="<?php echo $_smarty_tpl->tpl_vars['libro']->value->getId();?>
">
                                <input type="submit" value="Borrar">
                            </form>
                        </td>                
                    </tr>
                <?php
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl, 1);?>
            </tbody>
        </table>
    <?php }?>

    <!--Enlace para crear un nuevo libro-->
    <a href="index.php?accion=nuevo_libro_form_MPG" style="text-decoration: none;">
        <button type="button">Añadir Nuevo Libro</button>
    </a>

</body>
</html>
<?php }
}
