<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=100%, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>ZONA PRIVADA</title>
</head>
<body>
<!--Establecemos de nuevo la plantilla del layout para establecer los mismos estilos-->
@extends('plantillas.base')

@section('titulo', 'Inicio')

@section('contenido')

    @auth
    <h2>Bienvenido {{ Auth::user()->name}} a la página principal de la zona PRIVADA.</h2>
    <nav>
        <a href="{{ route('zonapublica') }}">Ve a la zona pública</a><br><br>
        <a href="{{ route('logout') }}">Cierra sesión.</a></br><br>
    @endauth
    </nav>

    <h3>MIS MASCOTAS</h3><br>
<!--Enlace del formulario para crear nuevas mascotas-->
    <a href="{{ route('formmascotaMPG') }}">Crear nueva mascota</a><br><br>
    <br><br>
<!--De nuevo creamos una tabla con las diferentes mascotas que posee el usuario con sus datos-->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Tipo</th>
                <th>Me gustas</th>
                <th>Propietario</th>
                <th>¿Pública?</th>
                <th>Cambiar publica-privada</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($mascotasMPG as $mascota)
            <tr>
                <td>{{ $mascota->id }}</td>
                <td>{{ $mascota->nombre }}</td>
                <td>{{ $mascota->descripcion }}</td>
                <td>{{ $mascota->tipo }}</td>
                <td>{{ $mascota->megusta }}</td>
                <td>{{ $mascota->user_id }}</td>
                <td>{{ $mascota->publica }}</td>
                <td>
<!--Introducimos el botón para eliminar, le he dado metodo post y he añadido un onsubmit para que pregunte que si esta
seguro de la confirmación y lo borre automaticamente-->
                <form action="{{ route('eliminarmascotaMPG', $mascota->id) }}" method="POST" onsubmit="return confirm('¿Estás seguro de que quieres eliminar esta mascota?');">
                    @csrf
                    <button type="submit">Borrar</button>
                </form>
                </td>
            </tr>
            @endforeach
        </tbody>
    </table>
    @endsection
</body>
</html>