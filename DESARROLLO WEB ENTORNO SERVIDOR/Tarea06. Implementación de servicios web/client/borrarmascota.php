<?php

require __DIR__ . '/vendor/autoload.php'; // Ajusta si tu autoloader está en otro lugar

use GuzzleHttp\Client;

session_start();

// Si no hay token, mandamos al usuario a inciar sesión.
if (!isset($_SESSION['token'])) {
    echo '<p>No has iniciado sesión. <a href="login.php">Inicia sesión aquí</a></p>';
    exit;
}

// Si se envió el formulario tipo post y existe un id, trabajamos con eso.
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['id'])) {

    //Lo almacenamos en una variable.
    $idMascota = $_POST['id'];

    //Creamos el cliente controlando los errores.
    try {
        $cliente = new Client(['http_errors' => false]);

        //Mandamos una respuesta delete con la url con el id que hemos almacenado y nos aseguramos que la sesión este inciiada y que el archivo sea json.
        $response = $cliente->delete("http://localhost:8080/api/mascotaMPG/{$idMascota}", [
            'headers' => [
                'Authorization' => 'Bearer ' . $_SESSION['token'],
                'Accept' => 'application/json'
            ]
        ]);

        $code = $response->getStatusCode();
        $body = json_decode($response->getBody(), true);


        //Mostramos el codigo. Y los mensajes del server si es borrada o si hay algún error.
        echo "<p><strong>Código HTTP:</strong> $code</p>";
        if (isset($body['mensaje'])) {
            echo "<p><strong>Mensaje:</strong> " . htmlspecialchars($body['mensaje']) . "</p>";
        } elseif (isset($body['errores'])) {
            echo "<p><strong>Errores:</strong></p><ul>";
            foreach ($body['errores'] as $campo => $mensaje) {
                echo "<li><strong>$campo:</strong> $mensaje</li>";
            }
            echo "</ul>";
        } else {
            echo "<p>No se recibió un mensaje del servidor.</p>";
        }

    } catch (Exception $e) {
        echo "<p>Error al intentar borrar la mascota: " . $e->getMessage() . "</p>";
    }
} else {
    // Mostrar formulario si no se ha enviado aún
    ?>

    <h2>Borrar Mascota</h2>
    <form method="post" action="borrarmascota.php">
        <label for="id">ID de la mascota:</label>
        <input type="number" name="id" id="id" required>
        <br><br>
        <input type="submit" value="Eliminar Mascota">
    </form>

<?php } ?>
