<?php

use PHPUnit\Framework\TestCase;
use dwes04\modelo\Libros;
use dwes04\modelo\Libro;

class LibrosTest extends TestCase {

    private \PDO $pdo;

    // Configuración antes de cada prueba donde probams la conexión y lo hacemos con protected.
    protected function setUp(): void
    {
        $this->pdo = new \PDO('mysql:host=localhost;dbname=dwes04;port=3306', 'root', '');
        $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }

    // Test 1: Verificar que listarMPG devuelve un array de libros ordenados por fecha_creacion.
    public function testListarMPGPorFechaCreacion() {

        $libros = Libros::listarMPG($this->pdo, true); // Ordenar por fecha de creación
        
        // Comprobar que las fechas están ordenadas de más reciente a menos reciente
        for ($i = 0; $i < count($libros) - 1; $i++) {
            $this->assertGreaterThanOrEqual(
                $libros[$i+1]->getFechaCreacion(), // Fecha del libro siguiente
                $libros[$i]->getFechaCreacion(),   // Fecha del libro actual
                "Las fechas no están ordenadas correctamente en el índice $i"
            );
        }
    }
    

    // Test 2: Verificar que listarMPG devuelve un array de libros ordenados por fecha_actualizacion
    public function testListarMPGPorFechaActualizacion() {
        $libros = Libros::listarMPG($this->pdo, false); // Ordenar por fecha de actualización
    
        // Comprobar que las fechas están ordenadas de más reciente a menos reciente
        for ($i = 0; $i < count($libros) - 1; $i++) {
            $this->assertGreaterThanOrEqual(
                $libros[$i+1]->getFechaActualizacion(), // Fecha de actualización del libro siguiente
                $libros[$i]->getFechaActualizacion(),    // Fecha de actualización del libro actual
                "Las fechas de actualización no están ordenadas correctamente en el índice $i"
            );
        }
    }
    

}
