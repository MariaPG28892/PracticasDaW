<?php
session_start();

use GuzzleHttp\Client;

require __DIR__ . '/vendor/autoload.php';

// Si no hay token en sesión, redirige a iniciar sesión.
if (!isset($_SESSION['token'])) {
    echo '<p>No has iniciado sesión. <a href="login.php">Inicia sesión aquí</a></p>';
    exit;
}

$mensaje = '';
$errores = [];

// Comprobamos si al formulario se han mandado tipo post y los recogemos cada uno en una variable.
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'] ?? null;
    $descripcion = $_POST['descripcion'] ?? null;
    $publica = $_POST['publica'] ?? null;

    //Si existen los tres datos y han pasado la validación del otro archivo, creamos el cliente.
    if ($id && $descripcion && $publica) {
        $cliente = new Client(['http_errors' => false]);

        //Almacenamos los datos a enviar.
        $datosAEnviar = [
            'descripcion' => $descripcion,
            'publica' => $publica
        ];

        /**
         * Dentro del try-catch mandamos la url con put, donde metemos la url con el id, convertimos
         * los datos a enviar en json y en el header nos aseguramos que tenga el token de la sesión iniciada
         * y que solo acepte archivos json.
         */
        try {
            $response = $cliente->put("http://localhost:8080/api/mascotaMPG/{$id}", [
                'json' => $datosAEnviar,
                'headers' => [
                    'Authorization' => 'Bearer ' . $_SESSION['token'],
                    'Accept'        => 'application/json'
                ]
            ]);

            //Extraemos el código y descodificamos el body. Lo vi que se podía hacer así en las clases.
            $code = $response->getStatusCode();
            $body = json_decode($response->getBody(), true);

            //Mostramos el código.
            echo "<p>Código HTTP: $code</p>";

            //Si el codigo es 200 se muestra el mensaje que tenemos en el server.
            if ($code === 200) {
                echo "<p>{$body['mensaje']}</p>";
            //Si el codigo es 400 tendremos errores de validación y los mostramos.
            } elseif ($code === 400) {
                echo "<p>Errores de validación:</p><ul>";
                foreach ($body['errores'] as $campo => $mensajes) {
                    foreach ($mensajes as $msg) {
                        echo "<li><strong>$campo:</strong> $msg</li>";
                    }
                }
                echo "</ul>";
            //Si el codigo es 403 o 404, mostramos también sus errores correspondientes en el server.
            } elseif ($code === 403 || $code === 404) {
                echo "<p>Error: {$body['mensaje']}</p>";
            } else {
                echo "<p>Error desconocido.</p>";
            }

        } catch (Exception $e) {
            echo "<p>Error de conexión con el servicio: " . $e->getMessage() . "</p>";
        }

    } else {
        echo "<p>Faltan datos obligatorios.</p>";
    }
}
?>
<!--Aquí ponemos el formulario para que se muestre si no se ha rellenado.-->
<h2>Cambiar datos de una mascota</h2>
<form method="POST">
    <label for="id">ID de la mascota:</label>
    <input type="number" name="id"><br><br>

    <label for="descripcion">Descripción nueva:</label><br>
    <textarea name="descripcion"></textarea><br><br>

    <label>¿Es pública?</label><br>
    <input type="radio" name="publica" value="Si"> Sí<br>
    <input type="radio" name="publica" value="No"> No<br><br>

    <button type="submit">Actualizar mascota</button>
</form>
