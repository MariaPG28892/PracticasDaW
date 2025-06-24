<?php
require_once __DIR__ . '/../../etc/config.php';
require_once __DIR__ . '/../../funciones/dbconn.php';
require_once 'funcionlogin.php';
require_once __DIR__ . '/../login.php';

$errors = [];
$notifs = []; 

//Abrimos la sesión para obtener los datos que hay alojados en ella y poder interactuar.
session_start();

//Abrimos la conexión con la función conectar, si no hay conexión retornamos LOGIN_FAIL_DB y mandamos un error al usuario diciendo que no hay conexión.
$conexion = conectar();

if(!$conexion){
    $errors[] = "Ha ocurrido un error en la conexión a la base de datos.";
    return LOGIN_FAIL_DB;
}

//A continuación nos aseguramos de que $_POST no se encuentre vacio, una vez nos aseguramos podemos continuar en el código.
if(!empty($_POST)){

    /*
    Lo que hacemos es comprobar si en la sesión hay un id y un login por lo que la sesión no estaría cerrada y el usuario ya estaría
    autenticado, retornamos LOGIN_PREV y notificamos al usauario de que ya está autenticado y no tiene que volver a hacerlo.
     */
    if(isset($_SESSION['id']) && isset($_SESSION['login'])){
        $notifs[]= "El usuario ya está autenticado y no es necesario volver a autenticarlo.";
        return LOGIN_PREV;
    } else {
        /*
        Si el usuario y la contraseña no están en la sesión lo que hacemos es filtrar y sanitizar login, con filter_input y filtramos 
        la contraseña, pero no la santizamos por eso he usado filter_unsafe_raw que no haría cambios en nuestra contraseña. Luego comprobamos
        que ni la contraseña ni el login estén vacios, de ser así lanzaremos un error de que están vacios y retornamos LOGIN_ERR.
        */
        $login = filter_input(INPUT_POST, 'login' , FILTER_SANITIZE_SPECIAL_CHARS);
        $contraseña = filter_input(INPUT_POST,'contraseña', FILTER_UNSAFE_RAW);

        if(empty($login) || empty($contraseña)){
            $errors[] = "El usuario o la contraseña están vacíos";
            return LOGIN_ERR;
        } else {

            //Autenticamos el usuario primero usando la función de loguear para que compruebe los datos.
            $autenticacion = loguearUsuario($conexion, $login, $contraseña);

            /*
            Si los datos que devuelve son usuario no registrado, contraseña incorrecta, retornamos LOGIN_ERR informando del tipo de error
            de lo contrario, guardamos en la sesión el id que en mi caso sería la variable $autenticación, por que es lo que devolvería la 
            función y el login, notificamos al usuario de que se ha autenticado correctamente y retornariamos  LOGIN_OK, si la función 
            devuelve cualquier otra cosa, retornamos LOGIN_ERR e informamos al usuario de un error desconocido.
             */
            if($autenticacion === "Usuario no registrado."){
                $errors[] = "El usuario no ha sido registrado";
                return LOGIN_ERR;
            } else if($autenticacion ==="Error, contraseña incorrecta"){
                $errors[] = "Error, la contraseña es incorrecta.";
                return LOGIN_ERR;
            } else if(is_int($autenticacion)){
                $_SESSION['id'] = $autenticacion; 
                $_SESSION['login'] = $login;
                $notifs[]= "El usuario se ha autenticado correctamente y se guardan su ID y login en la sesión.";
                return LOGIN_OK;
            } else {
                $errors[] = "Error desconocido en la autenticación.";
                return LOGIN_ERR;
            }
        }
    }
} else{
    //Si el formulario está vacio, retornamos LOGIN_ERR e informamos al usuario de qe los datos del formulario no han sido enviados.
    $errors[]="Los datos del formulario no fueron enviados";
    return LOGIN_ERR;
}