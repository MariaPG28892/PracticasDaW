<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mascota Eliminada</title>
</head>
<body>
@extends('plantillas.base')

@section('titulo', 'Inicio')

@section('contenido')
    <h1>Resultado de la eliminación</h1>

    @if(isset($error))
        <p style="color: red;">{{ $error }}</p>
    @else
        <p>Se ha eliminado la mascota con ID: {{ $mascota_id }}</p>
    @endif

    <a href="{{ route('zonaprivada') }}">Volver a la zona privada</a>

@endsection
</body>
</html>