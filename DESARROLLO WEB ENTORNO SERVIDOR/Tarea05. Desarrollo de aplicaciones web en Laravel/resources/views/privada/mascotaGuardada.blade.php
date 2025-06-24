<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mascota guardada</title>
</head>
<body>
@extends('plantillas.base')

@section('titulo', 'Inicio')

@section('contenido')

    <h1>¡Tu mascota {{ $nombreMascota }} ha sido guardada con éxito!</h1>
    <p>Se ha guardado la mascota con el ID: {{ $mascota_id }}</p>
    <p>Nombre: {{ $nombre }}</p>

    <a href="{{ route('zonaprivada') }}">Volver a la zona privada</a>

@endsection
</body>
</html>