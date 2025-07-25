<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Creamos la base de datos y completamos las parte del enum, si no rellenas la parte de me gusta, por defecto lo que hará sera 
     * asignarle 0. Lo he hecho con default.
     */
    public function up(): void
    {
        Schema::create('mascotas', function (Blueprint $table) {
            $table->id();
            // Cada mascota pertenece a un usuario, si se borra el usuario o se actualiza su id, se actualiza en cascada
            $table->unsignedBigInteger('user_id');
            $table->foreign('user_id')->references('id')
                ->on('users')->cascadeOnDelete()->cascadeOnUpdate();
            $table->string('nombre', 50);
            $table->string('descripcion', 250);
            $table->enum('tipo', ['Perro', 'Gato', 'Pájaro', 'Dragón', 'Conejo', 'Hamster', 'Tortuga', 'Pez', 'Serpiente']);
            $table->enum('publica', ['Sí','No']);
            $table->unsignedInteger('megusta')->default(0);
            $table->timestamps();
        });
    }

    /**
     * Esta parte sirve para que si existe otra tabla llamada mascotas la borre para crear la nuestra.
     */
    public function down(): void
    {
        Schema::dropIfExists('mascotas');
    }
};
