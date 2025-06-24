<?php

/*
Hacemos una función para la conexión, el login y la contraseña y retornara un string o un int.
*/

function loguearUsuario(PDO $pdo, String $login, String $contraseña): String | int
{

    //Hacemos una consulta donde recupere el id y la contraseña de la tabla usuarios donde el login sea el mismo que el introducido.
    try{
        $SQL = 'SELECT id, hash_contraseña FROM usuarios WHERE login =:login';
        $stmt = $pdo -> prepare($SQL);
        $stmt ->bindValue(':login', $login);

        if($stmt->execute()){
            //Guardamos los datos en un array asociativo, con fetchAll no funcionaría y daría error.
            $datos = $stmt ->fetch(PDO::FETCH_ASSOC);
        }

        //Si no hay datos se retorna usuario no registrado.
        if(!$datos){
            return "Usuario no registrado.";
        } else {
            //Hasheamos la contraseña de igual modo que se hace para introducirla en la base de datos, usamos SALTEADO como constante.
            $contraseniaHasheada = hash('sha256',strrev($login). $contraseña . SALTEADO);

            //Si la contraseña hasheada es igual que hash_contraseña dentro de datos, retornamos el id, lo convertimos en int para que no haya problemas.
            if($contraseniaHasheada === $datos['hash_contraseña']){
                return (int)$datos['id'];
            } else {
                //De lo contrario, devolvemos un error, diciendo que la contraseña es incorrecta.
                return "Error, contraseña incorrecta";
            }
        }


    } catch(PDOException $ex){
        //si no podemos acceder a la base de datos retornamos un error.
        return "Error al establecer conexión con la base de datos.";
    }
}