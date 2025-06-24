<?php

session_start();

$errors = [];  
$notifs = [];  

// Verificamos si el usuario está autenticado usando isset y comprobando que almacena id y login
if (isset($_SESSION['id']) && isset($_SESSION['login'])) {

    // Verificamos si existe 'ultimo_acceso' en la sesión, de lo contrario lo creará y comenzará a contar.
    if (!isset($_SESSION['ultimo_acceso'])) {
        $_SESSION['ultimo_acceso'] = time();
    }

    // Calculamos el tiempo transcurrido desde el último acceso, restando al tiempo actual el ultimo acceso y guardandolo en tiempo transcurrido.
    $ultimo_acceso = $_SESSION['ultimo_acceso'];
    $tiempo_transcurrido = time() - $ultimo_acceso;

    /*
    Si el tiempo transcurrido es menor a 10 minutos (600 segundos)  renovamos el tiempo al tiempo actual y retornamos un array con el 
    id del usuario, el login, el ultimo acceso y el tiempo transcurrido.
    */
    if ($tiempo_transcurrido < 600) {
        $_SESSION['ultimo_acceso'] = time();

        $usuario_autenticado = [
            'id_usuario' => $_SESSION['id'],               
            'login' => $_SESSION['login'],                 
            'ultimo_acceso' => $_SESSION['ultimo_acceso'], 
            'tiempo_transcurrido' => $tiempo_transcurrido  
        ];

        // Si todo está bien, devolvemos el array de usuario autenticado.
        return $usuario_autenticado;

    } else {
        /*
        Si el tiempo de inactividad supera los 10 minutos, eliminamos los datos del usuario utilizando unset, notificando al usuario
        que la sesión esta cerrada por inactividad y retornamos un array vacio.
        */
        unset($_SESSION['id']);
        unset($_SESSION['login']);
        unset($_SESSION['ultimo_acceso']);

        $notifs[] = "Sesión cerrada por inactividad.";

        return [];
    }

} else {
    /*
    Si no hay usuario almacenado en la sesión o hay algún error, notificamos al usuario que no hay información en la sesión y 
    retornamos un array vacio.
     */
    $errors[] = "No hay información de usuario en la sesión.";
    return [];
}

