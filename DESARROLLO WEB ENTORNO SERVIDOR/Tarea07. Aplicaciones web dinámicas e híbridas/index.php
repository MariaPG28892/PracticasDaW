<?php
require_once __DIR__.'/setup.php';

$jaxonCss = $jaxon->getCss();
$jaxonJs = $jaxon->getJs();
$jaxonScript = $jaxon->getScript();
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Mi biblioteca</title>
    <?php echo $jaxonCss ?>
        <style>
            table {
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 0.9em;
                font-family: sans-serif;
                min-width: 400px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            }
            table thead tr {
                background-color: #009879;
                color: #ffffff;
                text-align: left;
            }
            table th,
            table td {
                padding: 12px 15px;
            }
            .formblock {
                margin:10px;
                padding:10px;
                border:1px solid blue;
            }
            #log {
                background-color: beige;
                border: 1px solid black;
                padding:10px;
                margin:5px;
                border-radius: 10px;
            }
        </style>
</head>
<body>

<h1>Tarea de <span id="PROF">María Palomares Gallo</span></h1>

<div id="listaLibros">
    //GENERAR AQUÍ LISTA DE LIBROS
</div>
<!--Añadimos en el evento la función de listar los libros.-->
<input type="button" value="Actualizar lista de libros" onclick="jaxon_listarLibrosMPG();";>

<br>
<br>
<form id="nuevoLibro" onSubmit="return false;">
    <label> Título del libro:<input id="titulo" type="text" name="titulo"></label>
    <BR>
    <label> ISBN:<input id="isbn" type="text" name="isbn"></label>
    <BR>
    <label> Autor:<input id="autor" type="text" name="autor"></label>
    <BR>
    <label> Año publicación:<input id="anio" type="text" name="anio"></label>
    <BR>
    <label> Páginas:<input id="paginas" type="text" name="paginas"></label>
    <BR>
    <label> Ejemplares disponibles:<input id="ejemplares" type="text" name="ejemplares"></label>
    <BR>
    <!--Añadimos en el evento onclick la función, donde por getElementById insertamos los datos para el libro que son los del evento-->
    <input type="button"
        onclick="jaxon_registrarLibrosMPG(
        document.getElementById('titulo').value,
        document.getElementById('isbn').value,
        document.getElementById('autor').value,
        document.getElementById('anio').value,
        document.getElementById('paginas').value,
        document.getElementById('ejemplares').value
        )";
    value="Añadir nuevo libro.">
</form>
<br>

<form id="otrosLibrosAutor" onSubmit="return false;">
    <label> ISBN del libro:<input id="otros_autores_isbn" type="text" name="ISBN"></label>
    <BR>
    <input type="button"
        onclick="<?=rq()->call('listarLibrosAutor',pm()->input('otros_autores_isbn'))?>";
    value="Ver otros libros del autor.">
</form>
<BR>

<div id="otros_libros_autor" style="display:none">
            //OCULTO
</div>

<div id='log'>
    <H1>Mensajes de LOG:</H1>
</div>

<?php echo $jaxonJs ?>

<?php echo $jaxonScript ?>

<script>    
     //Ejemplo de invocación usando javascript
     const fecha=new Date();
     jaxon_funcion1(fecha.toLocaleString("es-ES"));
</script>
<script>
    //Cambiamos el nombre y le pasamos la función para ello
    let nombre = document.getElementById('PROF').innerText;
    jaxon_funcion2(nombre);
</script>
<script>
    // Cargar lista automáticamente al abrir la página
    jaxon_listarLibrosMPG();
</script>

<script>
    //Función para registrar los libros.
    jaxon_registrarLibrosMPG();
</script>

</body>
</html>
