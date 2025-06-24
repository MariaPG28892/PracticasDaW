<?php

require __DIR__ . '/vendor/autoload.php';
use GuzzleHttp\Client;

//Abrimos la sesión
session_start();

/*
Comprobamos si dentro de la sesión hay algún token almacenado, lo que quiere decir que hay un usuario autenticado
si es así seguimos con el código, si no enviamos un mensaje de error diciendo que no hay ninguna sesión activa,
por lo tanto no se puede cerrar.
*/
if(isset($_SESSION['token'])){
    
    //Creamos el cliente GuzzleHttp controlando los errores.
    $cliente = new Client(['http_errors'=>false]);

    //Guardamos la URL dentro de una variable para poder usarla posteriormente.
    $url = 'http://localhost:8080/api/logout';

    //Abrimos un try-catch para controlar los errores y que no crashee el programa.
    try {

        /*
        La respuesta seria tipo post, donde introducimos la url, pero en este caso, lo que hacemos es decirle
        que a la cabecera le añada la autentificación y su token.
        */
        $response = $cliente->post($url, [
            'headers' => [
                'Authorization' => 'Bearer ' . $_SESSION['token']
            ]
        ]);

        //Extraemos el código y el body y descodificamos el json.
        $code = $response->getStatusCode();
        $body = $response->getBody();

        $data = json_decode($body, true);

        /*
        Realizamos un if-else, donde vayamos controlando los códigos que nos mandan en la respuesta, en el caso
        que el código sea 200, quiere decir que el logout se ha realizado con existo y cerramos la sesión. De lo
        contrario, lanzamos un mensaje de error. 
        */
        if ($code == 200) {
            echo "Logout correcto. Sesión cerrada";
            // Eliminamos el token de la sesión. 
            unset($_SESSION['token']);
        } else {
            echo "Error al cerrar sesión." . ($data['mensaje'] ?? 'Error desconocido.');
        }

    } catch (Exception $e) {
        echo "Hubo un error en la conexión: " . $e->getMessage();
    }

}else {
    echo "Error. No se puede cerrar sesión, no hay ninguna activa.";
}