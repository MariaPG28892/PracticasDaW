<?php

use PHPUnit\Framework\TestCase;
use dwes04\modelo\Libro;

class LibroTest extends TestCase {

    private $pdo;

    protected function setUp(): void {
        // Configurar una base de datos en memoria para pruebas
        $this->pdo = new PDO('sqlite::memory:');
        $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Vi que muchas veces hay que probar insertando una tabla de prueba.
        $this->pdo->exec("CREATE TABLE libros (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            isbn TEXT,
            titulo TEXT,
            autor TEXT,
            anio_publicacion INTEGER,
            paginas INTEGER,
            ejemplares_disponibles INTEGER,
            fecha_creacion TEXT DEFAULT CURRENT_TIMESTAMP,
            fecha_actualizacion TEXT DEFAULT CURRENT_TIMESTAMP
        )");
    }

    //En este test probamos la funcion guardar, lo hacemos con todos los datos y luego comprobamos el resultado y el id
    public function testGuardar() {
        $libro = new Libro();
        $libro->setIsbn("978-3-16-148410-0");
        $libro->setTitulo("Libro de Prueba");
        $libro->setAutor("Autor de Prueba");
        $libro->setAnio_publicacion(2024);
        $libro->setPaginas(300);
        $libro->setEjemplaresDisponibles(10);

        $resultado = $libro->guardar($this->pdo);

        $this->assertTrue($resultado);
        $this->assertNotNull($libro->getId());
    }

    //En este test intentamos rescatar un libro que introducimos manualmente en la base de datos, después comprobamos que es una isntacia,
    //y también comprobamos que coincide el isbn y el titulo.
    public function testRescatar() {
        // Insertar un libro de prueba en la base de datos para rescatarlo.
        $this->pdo->exec("INSERT INTO libros (isbn, titulo, autor, anio_publicacion, paginas, ejemplares_disponibles) 
                          VALUES ('978-3-16-148410-0', 'Libro de Prueba', 'Autor de Prueba', 2024, 300, 10)");
        $id = $this->pdo->lastInsertId();

        $libroRescatado = Libro::rescatar($this->pdo, $id);

        $this->assertInstanceOf(Libro::class, $libroRescatado);
        $this->assertEquals('978-3-16-148410-0', $libroRescatado->getIsbn());
        $this->assertEquals('Libro de Prueba', $libroRescatado->getTitulo());
    }

    //En este test intentamos borrar un libro que introducimos manualmente y luego comprobamos el resultado, si es true es que lo ha borrado
    //luego intentamos rescatar el libro después de borrarlo y tiene que devolver null.
    public function testBorrar() {
        // Insertar un libro de prueba
        $this->pdo->exec("INSERT INTO libros (isbn, titulo, autor, anio_publicacion, paginas, ejemplares_disponibles) 
                          VALUES ('978-3-16-148410-0', 'Libro de Prueba', 'Autor de Prueba', 2024, 300, 10)");
        $id = $this->pdo->lastInsertId();

        $resultado = Libro::borrar($this->pdo, $id);
        $this->assertTrue($resultado);

        // Intentar recuperar el libro después de borrarlo
        $libroRescatado = Libro::rescatar($this->pdo, $id);
        $this->assertNull($libroRescatado);
    }

    //En estos test comprobamos que introduce los setter y que devuelve los getters.
    public function testSettersYGetters() {
        $libro = new Libro();
        $libro->setIsbn("123456789");
        $libro->setTitulo("Mi Libro");
        $libro->setAutor("Un Autor");
        $libro->setAnio_publicacion(2023);
        $libro->setPaginas(250);
        $libro->setEjemplaresDisponibles(5);

        $this->assertEquals("123456789", $libro->getIsbn());
        $this->assertEquals("Mi Libro", $libro->getTitulo());
        $this->assertEquals("Un Autor", $libro->getAutor());
        $this->assertEquals(2023, $libro->getAnioPublicacion());
        $this->assertEquals(250, $libro->getPaginas());
        $this->assertEquals(5, $libro->getEjemplaresDisponibles());
    }
}