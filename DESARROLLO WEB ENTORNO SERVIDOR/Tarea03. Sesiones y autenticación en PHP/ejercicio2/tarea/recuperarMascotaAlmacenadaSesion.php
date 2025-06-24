<?php

 /*
 Primero abrimos la sesión para recuperar los datos de la sesión anterior, para poder recuperarla, si no abrimos sesión no se recuperarian los
 datos.
 */

session_start();

//Creamos los dos arrays para almacenar tanto los datos como los errores para poder mostrarlos después.
$errors = [];
$notifs = [];

/*
Lo primero que comprobamos es que la sesión mascota existe y para concretar más si es un array. Si es así guardamos la sesión 
en una variable para poder comprobar cada uno de los datos. Después de crear la variable, lo que hacemos es comprobar si existe el 
nombre, tipo y pública, si todo existe devolvemos este array con los datos y notificamos al usuario de que se han recuperado los datos.
 */
if(isset($_SESSION['mascota']) && is_array($_SESSION['mascota'])){
        $mascotas = $_SESSION['mascota'];
        if(isset($mascotas['nombre']) && isset($mascotas['tipo']) && isset($mascotas['publica'])){
                $notifs[]="Datos recuperados con éxito";
                return $mascotas;
        }else{
                //Si no contiene todos los datos se envia un array vacío y un error diciendo que faltan datos.
                $errors[]="Información de la mascota incompleta.";
                return [];
        }
} else{
        //Si no se encuentra la sesión, retorna un array vacío y un error que innforma de que no hay mascotas almacenadas en la sesión.
        $errors[] = "No se ha encontrado mascotas en la sesión";
        return [];
}