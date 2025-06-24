<?php

require __DIR__ . '/vendor/autoload.php';

use GuzzleHttp\Client;
//Abrimos la sesión
session_start();

//Si nuestro token no existe en la sesión mandamos por medio de un enlace al login. 
if(!isset($_SESSION['token'])){
    echo '<p>No has iniciado sesión. <a href="login.php">Inicia sesión aquí</a></p>';
    exit; 
} else{
    //creamos el cliente controlando los fallos
    $cliente = new Client(['http_errors'=>false]);

    //Guardamos la url en una variable.
    $url = 'http://localhost:8080/api/mascotasMPG';

    try{
        //La respuesta queremos un tipo get que obtenga los datos, metemos la url y en el header le metemos el token del usuario.
        $response = $cliente->get($url, [
            'headers' => [
                'Authorization' => 'Bearer ' . $_SESSION['token']
            ]
        ]);

        //Obtenemos el codigo y el body y descodificamos el json.
        $code = $response->getStatusCode();
        $body = $response->getBody();

        $data = json_decode($body, true);

        //Si el codigo es 200 haremos una tabla con la lista de mascotas que tenga el usuario y sus datos.
        if($code === 200){
        echo "<h2>Lista de Mascotas</h2>";
        echo "<table border='1' cellpadding='5'>";
        echo "<tr><th>ID</th><th>Nombre</th><th>Descripción</th><th>Tipo</th><th>Pública</th><th>Me Gusta</th></tr>";

        foreach ($data as $mascota) {
            echo "<tr>";
            echo "<td>{$mascota['user_id']}</td>";
            echo "<td>{$mascota['nombre']}</td>";
            echo "<td>{$mascota['descripcion']}</td>";
            echo "<td>{$mascota['tipo']}</td>";
            echo "<td>{$mascota['publica']}</td>";
            echo "<td>{$mascota['megusta']}</td>";
            echo "</tr>";
        }

        echo "</table>";
        } else {
            echo "No se ha podido establecer conexión";
        }
    } catch (Exception $e) {
        echo "Error al conectar con el API: " . $e->getMessage();
    }
}