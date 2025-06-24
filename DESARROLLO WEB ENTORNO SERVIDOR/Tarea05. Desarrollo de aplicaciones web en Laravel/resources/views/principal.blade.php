<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=100%, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Página principal</title>
</head>
<body>
<!--Añadimos las plantillas del layout para tener el mismo estilo y que se aplique el css-->
    @extends('plantillas.base')

    @section('titulo', 'Inicio')

    @section('contenido')

    <h2>Bienvenido a la página principal PÚBLICA.</h2>
    <nav>
        @auth
            Estás autenticado, puedes ir a ...
            <a href="{{ route('zonaprivada') }}">tu zona privada</a><br>
        @endauth
        @guest
            No estás autenticado, por favor ...
            <a href="{{ route('formlogin') }}">Inicia sesión.</a><BR>
        @endguest
    </nav>
    <h2>Listado de Mascotas Públicas</h2>
<!--Mostramos una tabla con los datos de las mascotas públicas-->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Tipo</th>
                <th>Likes</th>
                <th>Usuario</th>
                <th>¿Pública?</th>
            </tr>
        </thead>
        <tbody>
<!--Hacemos un for each del array que pasamos por la view y extraemos cada dato-->
            @foreach ($mascotasMPG as $mascota)
            <tr>
                <td>{{ $mascota->id }}</td>
                <td>{{ $mascota->nombre }}</td>
                <td>{{ $mascota->descripcion }}</td>
                <td>{{ $mascota->tipo }}</td>
                <td>{{ $mascota->megusta }}</td>
                <td>{{ $mascota->user_id }}</td>
                <td>{{ $mascota->publica }}</td>
            </tr>
            @endforeach
        </tbody>
    </table>
    
    @endsection
</body>
</html>