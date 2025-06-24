<?php

require __DIR__ . '/vendor/autoload.php';

use GuzzleHttp\Client;

//Iniciamos sesión para poder guardar los token
session_start();

/*
Comprobamos si la sesión ya esta abierta, comprobando si existe una sesión con un token almacenados, si es así,
lanzamos un mensaje de error donde digamos que el usuario ya esta autenticado.
*/
if(isset($_SESSION['token'])){
    echo 'El usuario ya se ha autenticado.';
    exit;
}

/*
De lo contrario comprobamos si tenemos una petición POST, si es así continuamos con el código, de lo contrario
volvemos a mandar el formulario, así si no hemos iniciado sesión siempre tendremos el formulario.
*/
if($_SERVER['REQUEST_METHOD']==='POST'){

    //Si hay POST creamos un cliente GuzzleHttp controlando los errores.
    $cliente = new Client(['http_errors'=>false]);

    //Almacenamos los datos y la url en una variable para poder usarla a continuación.
    $datos = [
        'email' => $_POST['email'],
        'password' => $_POST['password']
    ];

    $url = 'http://localhost:8080/api/login'; 

    //Realizamos un try-catch para controlar los errores.
    try{

        //Dentro de la respuesta, mandaremos la url y los datos.
        $response=$cliente->post($url, ['form_params'=>$datos]);

        //Obtenemos el código de cliente y el body.
        $code=$response->getStatusCode();
        $body=$response->getBody(); 

        //Descodificamos tambien el json que trae la respuesta.
        $data = json_decode($body, true);

        /*
        Si el código que manda es 200 significa que la sesión se ha iniciado correctamente, por lo tanto, 
        guardamos en la sesión el token que hemos descodificado anteriormente, para hacer que la sesión se
        mantenga abierta. De lo contrario, mandamos un error para decir que no se ha podido abrir la sesión
        por algún error.
        */
        if($code == 200){
            $_SESSION['token'] = $data['token'];
            echo "Login correcto. Tu token es: " . $data['token'];
        } else if ($code == 422){
            echo "Email o contraseña incorrecta. Intentelo de nuevo";
        } else if ($code == 401){
            echo "Credenciales no válidas.";
        }

    } catch (Exception $e) {
        echo "Hubo un error en la conexión: " . $e->getMessage();
    }

}else {
    // Si no se han enviado datos POST, mostramos el formulario de login
    echo '
    <h2>Iniciar sesión:</h2>
    <form method="POST" action="login.php">
        <label for="email">Email:</label>
        <input type="email" name="email" required><br><br>
        <label for="password">Contraseña:</label>
        <input type="password" name="password" required><br><br>
        <button type="submit">Iniciar sesión</button>
    </form>';
}