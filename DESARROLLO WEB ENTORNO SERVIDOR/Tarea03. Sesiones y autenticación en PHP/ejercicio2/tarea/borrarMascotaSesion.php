<?php

require_once __DIR__ . '/../borrarmascota.php';

/*
Primero abrimos sesión para tener los datos almacenados en esta, si no lo abrimos no podemos trabajar con ellos.
*/
session_start();

$errors = [];
$notifs = [];

/*
Primero comprobamos si la sesión mascota existe y si es un array, si esto se cumple, usamos unset() y dentro la sesión especifica que queremos
borrar y no usar destroy, si borra la sesión se notifica con un mensaje al usuario y se retorna la constante creada DELETED_FROM_SESSION. 
Si no existe la sesión o no es un array, devolvemos un error y la constante NOT_IN_SESSION.
*/
if(isset($_SESSION['mascota']) && is_array($_SESSION['mascota'])){
    unset($_SESSION['mascota']);
    $notifs[] = "Los datos de la mascota fueron eliminados correctamente de la sesión.";
    return DELETED_FROM_SESSION;
}else{
    $errors[]="Los datos de la mascota no existe y no han podido ser eliminados.";
    return  NOT_IN_SESSION;
}