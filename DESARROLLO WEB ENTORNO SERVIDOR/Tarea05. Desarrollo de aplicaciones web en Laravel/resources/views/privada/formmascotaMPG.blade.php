<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingresar nueva mascota</title>
</head>
<body>
<!--Añadimos el layout para darle el mismo estilo a todas las paginas-->
@extends('plantillas.base')

@section('titulo', 'Inicio')

@section('contenido')
    <h1>Registrar nueva mascota</h1>
<!--Ponemos una parte donde si hay errores los muestre-->
    @if ($errors->any())
        <h3>Se han producido errores en el formulario:</h3>
        <ul>
            @foreach ($errors->all() as $error)
                <li>{{ $error }}</li>
            @endforeach
        </ul>
    @endif
<!--luego añadimos el formulario con metodo post y en action metemos la ruta donde vamos a procesar esos datos-->
    <form method="POST" action="{{ route('nuevamascotaMPG') }}">
<!--Ponemos @csrf porque sino no funciona-->
        @csrf

        <label for="nombre">Nombre de la mascota:</label>
        <input type="text" id="nombre" name="nombre" required>
        <br><br>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required></textarea>
        <br><br>

        <label for="tipo">Tipo de mascota:</label>
        <select name="tipo" id="tipo" required>
            <option value="">Seleccione un tipo</option>
            <option value="Perro">Perro</option>
            <option value="Gato">Gato</option>
            <option value="Pájaro">Pájaro</option>
            <option value="Dragón">Dragón</option>
            <option value="Conejo">Conejo</option>
            <option value="Hamster">Hamster</option>
            <option value="Tortuga">Tortuga</option>
            <option value="Pez">Pez</option>
            <option value="Serpiente">Serpiente</option>
        </select>
        <br><br>

        <label>¿Pública?</label><br>
        <input type="radio" name="publica" value="Sí" id="publica_si" required>
        <label for="publica_si">Pública</label>

        <input type="radio" name="publica" value="No" id="publica_no" required>
        <label for="publica_no">Privada</label>
        <br><br>

        <button type="submit">Crear!</button>
    </form>
@endsection
</body>
</html>
