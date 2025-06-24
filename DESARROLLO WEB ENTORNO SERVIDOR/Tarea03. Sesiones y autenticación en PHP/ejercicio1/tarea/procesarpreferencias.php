<?php


/**
 * Autor: María Palomares Gallo
 * Asignatura: Desarrollo Web Entorno Servidor.
 */

//Primero creo un array para almacenar las mascotas seleccionadas y otro para los errores.
$mascotas_seleccionadas = [];
$errors = [];

/*
En primer lugar nos aseguramos de que post no está vacío, una vez pasamos este paso, lo siguiente con lo que vamos a trabajar es con el 
array tipos[], que proviene de checkbox de listarmascotas.php y lo que  hacemos es utilizar filter_input y sanitizar el array.
*/
if (!empty($_POST)) {

    $tipos = filter_input(INPUT_POST, 'tipos', FILTER_DEFAULT, FILTER_REQUIRE_ARRAY);

    /*
    Lo siguienete es asegurarnos de que $tipos es un array, una vez verificado, utilizamos un for-each donde vamos separando cada tipo 
    del array en uno y miramos si está en la constante TIPOS_DE_MASCOTAS, si son validos, se almacenará en el array $mascotas_seleccionadas
    si no, lanzará un error diciendo que el tipo no es válido. Si $tipos no es un array también lanza un error dciendo que los tipos no
    son válidos.
    */
    if (is_array($tipos)) {
        foreach ($tipos as $tipo) {
            if (in_array($tipo, TIPOS_DE_MASCOTAS)) {
                $mascotas_seleccionadas[] = $tipo;
            } else {
                $errors[] = "El tipo '$tipo' no es válido.";
            }
        }
    } else {
        $errors[] = "No se recibieron tipos de mascotas válidos.";
    }

    /*
    Lo que hacemos a continuación es asegurarnos de que no hay errores, si este array está vacío, almacenamos las cookies, una normal
    con las mascostas serializadas, es decir, convertimos el array en una cadena de texto y le pedimos que las almacene por 10 minutos.
    En cuanto a las cookie hasheada lo que hacemos es hashearla con 'sha256' serializar las mascotas seleccionadas y concatenarlo con la
    constate SALTEADO, estas también se almacenan durante 10 min. Ponemos time()+600 porque se cuenta en segundos.
    */
    if (empty($errors)) {
        setcookie('tipos_mascotas_MPG', serialize($mascotas_seleccionadas), time() + 600);
        setcookie('hash_tipos_mascotas_MPG', hash('sha256', serialize($mascotas_seleccionadas) . SALTEADO), time() + 600);
    }

    //Devolvemos las mascotas seleccionadas en array.
    return $mascotas_seleccionadas;

} else if (isset($_POST['restablecer'])) {
    //Si el usuario pulsa el botón restableces, restablecemos las cookis, quitandole la cadena de texto y  retornando a un tiempo pasado.
    setcookie('tipos_mascotas_MPG', '', time() - 3600);
    setcookie('hash_tipos_mascotas_MPG', '', time() - 3600);

    //Retornamos todos los tipos de mascotas, aunque solo se muestran las públicas.
    return TIPOS_DE_MASCOTAS;
}

/*
Procesamos las cookies si existen, con isset nos aseguramos si existen las cookies llamadas así, si existen pasamos a filtrarlas
con filter input, pero no las sanitizamos, solo queremos obtener sus valores.
*/
if (isset($_COOKIE['tipos_mascotas_MPG'], $_COOKIE['hash_tipos_mascotas_MPG'])) {

    $mascotas_recibidas_cookies = filter_input(INPUT_COOKIE, 'tipos_mascotas_MPG');
    $mascotas_recibidas_cookies_hash = filter_input(INPUT_COOKIE, 'hash_tipos_mascotas_MPG');

    /*
    Primero nos aseguramos de que $mascotas_recibidas_cookies, sea una cadena de texto(anteriormente la serializamos) y que las mascotas
    _recibidas_cookies_hash este bien hasheada.
    */
    if (is_string($mascotas_recibidas_cookies)
        && hash('sha256', serialize($mascotas_recibidas_cookies) . SALTEADO) === $mascotas_recibidas_cookies_hash) {

        // Deserializamos las cookies y las almacenamos en $mascotas_seleccionadas. Esto significa que vamos a volver a convertirlo en un array-
        $mascotas_seleccionadas = unserialize($mascotas_recibidas_cookies);

        // Verificamos que se haya deserializado y creamos un nuevo array donde iremos almacenando los tipos validos.
        if (is_array($mascotas_seleccionadas)) {
            $mascotas_validadas = [];

            // Validar que los valores deserializados son tipos válidos
            foreach ($mascotas_seleccionadas as $tipo) {
                if (in_array($tipo, TIPOS_DE_MASCOTAS)) {
                    $mascotas_validadas[] = $tipo;
                } else {
                    $errors[] = "El tipo '$tipo' no es válido.";
                }
            }

            // Si todo es válido, retornamos las mascotas validadas.
            if (empty($errors)) {
                return $mascotas_validadas;
            }
        } else {
            $errors[] = "El contenido de la cookie no es un array válido.";
        }
    } else {
        $errors[] = "La cookie ha sido manipulada o es inválida.";
    }
} else {
    $errors[] = "No se encuentran las cookies esperadas.";
}

// Si hay errores, retornamos todos los tipos por defecto.
if (!empty($errors)) {
    return TIPOS_DE_MASCOTAS;
}

// Retornar los tipos seleccionados si no hay errores.
return $mascotas_seleccionadas;
