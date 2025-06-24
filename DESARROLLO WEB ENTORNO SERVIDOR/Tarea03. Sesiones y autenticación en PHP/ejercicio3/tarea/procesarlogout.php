<?php
require_once __DIR__ . '/../logout.php';
//Abrimos la sesión para poder procesar los datos para poder cerrar la sesión.
session_start();

$errors = [];
$notifs = [];

/*
Lo primero que hacemos es comprobar que en la sesión existe login y el id, si estas existen procedemos a borrarlas, no queremos borrar 
la sesión entera por lo que usamos unset() y elegimos el id y el login, luego informamos al usuario de que la sesión se ha cerrado con éxito 
y retornamos LOGOUT_OK. Si la sesión o el login no existen, mandará un error al usuario y retornará LOGOUT_ERR
*/
if(isset($_SESSION['id']) && isset($_SESSION['login'])){
    unset($_SESSION['id']);
    unset($_SESSION['login']);
    $notifs[]= "Sesión cerrada con éxito";
    return  LOGOUT_OK;
}else{
    $errors[] = "No hay sesión almacenada con este usuario.";
    return  LOGOUT_ERR;
}