<html xmlns="http://www.w3.org/1999/xhtml" lang="es">
  <head>
    <meta charset="utf-8" />
    <title>@yield('titulo') - Empresa-mascotas</title>
    <link rel="stylesheet" href="{{ asset('css/estilo.css') }}" />
  </head>
  <body>
<!--Aquí he creado una plantilla base para aplicar a todas las demas y en el head he añadido el css para que se vaya aplicando a todas
las plantillas a las que le pase este.-->
    <header>
        <h1>EMPRESA DE MASCOTAS</h1>
    </header>

    <main>
        @yield('contenido')
    </main>

    <footer>
        Sitio web de elaboración propia.
    </footer>


  </body>
</html>