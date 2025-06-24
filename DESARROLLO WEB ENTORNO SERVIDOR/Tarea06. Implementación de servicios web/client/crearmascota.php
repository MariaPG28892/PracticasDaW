<?php

require __DIR__ . '/vendor/autoload.php';

use GuzzleHttp\Client;

//Abrimos sesión.
session_start();

//Si no hay usuario con la sesión iniciada, mandamos por un enlace a hacerlo.
if(!isset($_SESSION['token'])){
    echo '<p>No has iniciado sesión. <a href="login.php">Inicia sesión aquí</a></p>';
    exit; 
} else{
    //Creamos el cliente
    $cliente = new Client(['http_errors'=>false]);

    //Almacenamos los datos en una variable y la url.
    $datos = [
        'nombre' => $_POST['nombre'] ?? '',
        'descripcion' => $_POST['descripcion'] ?? '',
        'tipo' => $_POST['tipo'] ?? '',
        'publica' => $_POST['publica'] ?? ''
    ];

    $url = 'http://localhost:8080/api/crearmascotaMPG';

    try{
        //Mandamos la respuesta tipo post, donde le pasamos la utl, los datos y nos aseguramos de que mande el token en la cabecera.
        $response =  $cliente->post($url, [
            'form_params' => $datos,
            'headers' => [
                'Authorization' => 'Bearer ' . $_SESSION['token']
        ]]);

        //Obtenemos el codigo y el body y descodificamos el json.
        $code = $response->getStatusCode();
        $body = $response->getBody();
        $data = json_decode($body, true);

        //Si el código es 200 mandamos la aprobación y los datos del id y el usuario que lo ha creado.
        if($code === 200){
            echo '<p>ID de la nueva mascota: ' . $data['id'] . '</p>';
            echo '<p>Usuario: ' . $data['nombreCreador'] . '</p>';
        } else {
            echo '<h2>Error en la creación de la mascota</h2>';
            echo '<p>Código de respuesta: ' . $code . '</p>';
    
            // Mostrar los errores si existen
            if (isset($data['errores'])) {
                echo '<ul>';
                foreach ($data['errores'] as $campo => $errores) {
                    foreach ($errores as $error) {
                        echo '<li>' . $campo . ': ' . $error . '</li>';
                    }
                }
                echo '</ul>';
            }
        }

    }catch (Exception $e) {
        // En caso de error en la conexión
        echo '<p>Hubo un error al conectar con el servicio: ' . $e->getMessage() . '</p>';
    }
}