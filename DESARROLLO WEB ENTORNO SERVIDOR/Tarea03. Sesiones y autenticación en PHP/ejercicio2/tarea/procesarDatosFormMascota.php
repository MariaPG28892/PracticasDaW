<?php

require_once __DIR__ . '/../nuevamascotasesion.php';

//Abrimos la sesión para almacenar los datos que introduce el usuario por el formulario.
session_start();

//Creamos los arrays para devolver notificaciones o mensajes.
$errors = [];
$notifs = [];

//Comprobamos que $_POST no se encuentre vacio.
if(!empty($_POST)) {

    /*
    Aplicamos filter_input al nombre y lo sanitizamos, una vez hecho esto, comprobamos que no este vacio, sea una cadena vacia o sea
    mayor que 50 caracteres, si cumple alguno de estos mandamos un error explicandoselo al usuario.
    */
    $nombre = filter_input(INPUT_POST, 'nombre', FILTER_SANITIZE_SPECIAL_CHARS);
    if(empty($nombre) || trim($nombre) === '' || strlen($nombre)>50)
    {
        $errors[] = "El nombre no puede estar vacío, ser una cadena vacía o superar los 50 carácteres.";
    }

    /*
    Con tipo hacemos lo mismo, usamos filter_input y lo sanitizamos, en este caso, vamos a comprobar el tipo se encuentre en el array
    de la constante creada TIPOS_DE_MASCOTAS, en el caso que no este, mandará un error informando al usuario.
    */
    $tipo = filter_input(INPUT_POST, 'tipo', FILTER_SANITIZE_SPECIAL_CHARS);
    if(!in_array($tipo, TIPOS_DE_MASCOTAS)){
        $errors[] = "El tipo de mascota seleccionado no es válido.";
    }

    /*
    Con publica igual, aplicamos filter_input y sanitizamos y esta vez decimos que si publica no es si o no, mande un error informando
    al usuario.
    */
    $publica = filter_input(INPUT_POST, 'publica', FILTER_SANITIZE_SPECIAL_CHARS);
    if(!in_array($publica, ['si', 'no'])){
        $errors[]="El valor de publica debe de ser sí o no.";
    }

    //Si errores no está vacio retornanmos ERROR_IN_FORM.
    if(!empty($errors)){
        
        return ERROR_IN_FORM;
    }

    /*
    Si errores está vacio continua el codigo, aqui creamos un array bidimensional con el nombre, el tipo, y si es publica o no de los datos
    introducidos por el usuario
    */
    $mascota_introducida = ['nombre' => $nombre, 'tipo' => $tipo, 'publica'=>$publica];

    /*
    Comprobamos que mascota existe en la sesion, si existe decimos que si la mascota introducida mo es igual que la que hay guardada en 
    la sesión, actualice los datos de la mascota, mande un mensaje al usuario y retorne UPDATE_IN_SESSION, de lo contrario, mandara un mensaje
    de que no se han producido cambios y retornará lo mismo.
    */
    if(isset($_SESSION['mascota']))
    {
        if($_SESSION['mascota']!==$mascota_introducida){
            $_SESSION['mascota']  = $mascota_introducida;
            $notifs[] = "Los datos de la mascota se han actualizado correctamente.";
            return UPDATED_IN_SESSION;
        }else{
            $notifs[]="No hubo cambios en los datos de la mascota";
            return UPDATED_IN_SESSION;
        }
    } else{
        /*
        Si la mascota almacenada en la sesión no es la misma que la introducida, le diremos al usuario que se han guardado los datos
        correctamente y retornaremos SAVED_IN_SESSION.
         */
        $_SESSION['mascota'] = $mascota_introducida;
        $notifs[] = "La mascota ha sido registrada con éxito.";
        return SAVED_IN_SESSION;
    }

} else {
    //Si la sesión está vacia retorna un error.
    $errors[]="Los datos del formulario no han sido cargados.";
    return ERROR_IN_FORM;
}
