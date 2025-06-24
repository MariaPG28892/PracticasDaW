<?php

namespace dwes04\modelo;
/**
 * Es una interfaz que contiene los métodos, pero no se desarrollan, solo sirve para implementarlas a las otras clases.
 */
interface IGuardableMPG{

    public function guardar(\PDO $pdo);

    public static function rescatar(\PDO $pdo, int $id);

    public static function borrar(\PDO $pdo, int $id);
}