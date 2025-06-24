<?php
require_once __DIR__ . '/comun.php';

use Jaxon\Jaxon;
use Jaxon\Response\Response;
use GuzzleHttp\Client;

$jaxon = jaxon();
$jaxon->setOption("js.lib.uri", BASE_URL . "jaxon-dist");
$jaxon->setOption('core.request.uri', BASE_URL . 'backend.php');

function logMessage(Response $r, mixed $id)
{
    $r->append('log', 'innerHTML', '<div>' . print_r($id, true) . '</div>');
}

function funcion1($fechaYhora)
{
    $response = new Response();
    logMessage($response,"La fecha y la hora es: $fechaYhora");
    return $response;
}

function funcion2($nombre)
{
    $response = new Response();
    logMessage($response,"El nombre del autor o autora es $nombre");
    return $response;
}

//El id será el isbn en esta función
function listarLibrosAutor($id)
{
    $response = new Response();
    $response->clear('otros_libros_autor');

    // Si el id tiene entre 10 y 13 dígitos si lo cumple sigue el codigo.
    if (preg_match('/^\d{10,13}$/', $id)) {
        try {
            $pdo = new PDO(DB_DSN, DB_USER, DB_PASSWD);
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            //Consultamos el autor en la base dedatos que coincida con el isbn que hemos introducido.
            $stmt = $pdo->prepare("SELECT autor FROM libros WHERE isbn = :isbn");
            $stmt->bindValue(':isbn', $id);
            $stmt->execute();

            $autor = $stmt->fetchColumn();

            //Si no hay ningún autor asociado con el isbn mandamos un fallo.
            if (!$autor) {
                $response->assign('log', 'innerHTML', "No se encontró ningún libro con ISBN <b>$id</b>.");
                return $response;
            }

            //Creamos un cliente. 
            $client = new \GuzzleHttp\Client([
                'base_uri' => 'https://openlibrary.org/',
                'headers' => ['User-Agent' => 'MiApp/1.0 (miemail@dominio.com)']
            ]);

            //Extraemos el cliente y descodificamos el json.
            $res = $client->get("search.json?author=" . urlencode($autor));
            $data = json_decode($res->getBody(), true);

            //Si el json esta vacío mandamosun error por el log
            if (empty($data['docs'])) {
                $response->assign('otros_libros_autor', 'innerHTML', "No se encontraron libros del autor <b>$autor</b>.");
            } else {
                //Si tiene datos, lo que hacemos es mostrar los libros de la libreria que tengan el mismo autor.
                $resultado = "<h3>Libros de $autor:</h3><ul>";
                foreach (array_slice($data['docs'], 0, 10) as $libro) {
                    $titulo = htmlspecialchars($libro['title'] ?? 'Sin título');
                    $resultado .= "<li>$titulo</li>";
                }
                $resultado .= "</ul>";
                $response->assign('otros_libros_autor', 'innerHTML', $resultado);
            }
        } catch (Exception $e) {
            $response->assign('log', 'innerHTML', "Error: " . $e->getMessage());
        }
    } else {
        // Otro tipo de id, por ejemplo un ID interno
        $response->assign('otros_libros_autor', 'innerHTML', "Aquí mostrar libros del autor del libro con id $id");
    }

    $response->assign('otros_libros_autor', 'style.display', 'block');
    $response->assign('otros_libros_autor', 'style.border', '2px dotted blue');
    $response->assign('otros_libros_autor', 'style.padding', '10px');

    return $response;
}

//Función de listar libros.
function listarLibrosMPG(){

    $response = new Response();

    try {
        $pdo = new PDO(DB_DSN, DB_USER, DB_PASSWD);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        //Oreparamos la consulta y la ejecutamos
        $SQL = "SELECT titulo, isbn, autor, anio_publicacion, paginas, ejemplares_disponibles, fecha_creacion, fecha_actualizacion FROM libros";
        $stmt = $pdo->query($SQL);
        $libros = $stmt->fetchAll(PDO::FETCH_ASSOC);

        //Si no hay libros, mandamos un error
        if (!$libros) {
            $resultado = "<p>No hay libros disponibles.</p>";
            //Si hay libros creamos una tabla html con todos sus datos.
        } else {
            $resultado = "<table border='1'><thead><tr>
                        <th>Título</th><th>ISBN</th><th>Autor</th><th>Año</th><th>Páginas</th><th>Ejemplares</th><th>Fecha Creación</th><th>Fecha Actualización</th>
                     </tr>
                     </thead>
                     <tbody>";
            foreach ($libros as $libro) {
                $resultado .= "<tr>
                    <td>{$libro['titulo']}</td>
                    <td>{$libro['isbn']}</td>
                    <td>{$libro['autor']}</td>
                    <td>{$libro['anio_publicacion']}</td>
                    <td>{$libro['paginas']}</td>
                    <td>{$libro['ejemplares_disponibles']}</td>
                    <td>{$libro['fecha_creacion']}</td>
                    <td>{$libro['fecha_actualizacion']}</td>
                </tr>";
            }
            $resultado .= "</tbody>
                      </table>";
        }

        $response->assign('listaLibros', 'innerHTML', $resultado);
    } catch (PDOException $e) {
        $response->append('log', 'innerHTML', '<div>Error: ' . $e->getMessage() . '</div>');
    }

    return $response;
}

function registrarLibrosMPG($titulo, $isbn, $autor, $anio_publicacion, $paginas, $ejemplares_disponibles){
    $response = new Response();
    $errores = [];

    // Validación de entradas almacenando los errores en un array
    if (empty($titulo) || strlen($titulo) > 255) {
        $errores[] = "El título debe tener menos de 255 caracteres o no estar vacío";
    }

    if (empty($isbn) || !preg_match('/^\d{1,13}$/', $isbn)) {
        $errores[] = "El ISBN debe ser numérico, menor de 13 caracteres y no puede estar vacío";
    }

    if (empty($autor) || strlen($autor) > 255) {
        $errores[] = "El autor no puede estar vacío o superar los 255 caracteres";
    }

    if (!is_numeric($anio_publicacion) || $anio_publicacion <= 0 || $anio_publicacion > date('Y')) {
        $errores[] = "El año debe de ser un número entero mayor que 0 y menor que el año actual";
    }

    if (!is_numeric($paginas) || $paginas < 0) {
        $errores[] = "El número de páginas debe ser entero o mayor a 0";
    }

    if (!is_numeric($ejemplares_disponibles) || $ejemplares_disponibles < 0) {
        $errores[] = "El número de ejemplares disponibles debe ser entero o mayor a 0";
    }

    // Si hay errores, se devuelven los mensajes de error por log
    if (!empty($errores)) {
        foreach ($errores as $error) {
            logMessage($response, $error);
        }
        return $response;
    }

    // Conexión a la base de datos
    try {
        $pdo = new PDO(DB_DSN, DB_USER, DB_PASSWD);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Primero tenemos que comprobar si existe un libro con ese mismo isbn y si existe mandamos un error.
        $SQL1 = "SELECT COUNT(*) FROM libros WHERE isbn = :isbn";
        $stmt = $pdo->prepare($SQL1);
        $stmt->bindValue(':isbn', $isbn);
        $stmt->execute();
        
        if ($stmt->fetchColumn() > 0) {
            $response->append('log', 'innerHTML', "<div>Ya existe un libro con ISBN $isbn</div>");
            return $response;
        }

        //Si no existe insertamos el libro
        $SQL2 = "INSERT INTO libros (titulo, isbn, autor, anio_publicacion, paginas, ejemplares_disponibles)
                 VALUES (:titulo, :isbn, :autor, :anio_publicacion, :paginas, :ejemplares_disponibles)";
        
        $stmt2 = $pdo->prepare($SQL2);
        $stmt2->bindValue(':titulo', $titulo);
        $stmt2->bindValue(':isbn', $isbn);
        $stmt2->bindValue(':autor', $autor);
        $stmt2->bindValue(':anio_publicacion', $anio_publicacion);
        $stmt2->bindValue(':paginas', $paginas);
        $stmt2->bindValue(':ejemplares_disponibles', $ejemplares_disponibles);
        $stmt2->execute();

        $id = $pdo->lastInsertId();

        // Actualizar la lista de libros que tenemos hecho anteriormente
        $tablaActualizada = listarLibrosMPG();
        $response->assign('listaLibros', 'innerHTML', $tablaActualizada);

        //Mandamos un mensaje log diciendo que se ha insertado el libro.
        $response->append('log', 'innerHTML', "<div>Libro insertado con ID $id por María Palomares Gallo</div>");

    } catch (PDOException $e) {
        // Errores de conexión de la base de datos.
        $response->append('log', 'innerHTML', '<div>Error: ' . $e->getMessage() . '</div>');
    }

    return $response;
}




$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'funcion1');
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'funcion2');
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'listarLibrosAutor');
//Añadimos las funciones para que se puedan usar con jaxon en el html.
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'listarLibrosMPG');
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'registrarLibrosMPG');

