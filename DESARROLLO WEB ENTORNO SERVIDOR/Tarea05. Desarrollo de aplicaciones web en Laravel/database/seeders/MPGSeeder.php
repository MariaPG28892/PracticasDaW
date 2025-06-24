<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use App\Models\User; //importar el modelo User
use App\Models\MascotaMPG; // importar el modelo MascotaMPG
use Illuminate\Support\Facades\Hash; //importar Hash para poder usar Hash::make para la contraseña.

class MPGSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        // Crear el primer usuario, MPG1. Aquí decimos que si no esta creado, que lo cree y añadimos la información más  save para guardar.
        if (User::where('name', 'MPG1')->count() == 0) { 
            $user1 = new User;
            $user1->name = 'MPG1';
            $user1->email = 'MPG1@email.MPG'; 
            $user1->password = Hash::make('MPG1'); 
            $user1->email_verified_at = now(); 
            $user1->save(); 
        }

        // Crear las mascotas para el primer usuario
        if (MascotaMPG::where('nombre', 'Mickey')->where('user_id', $user1->id)->count() == 0) {
            $mascota1 = new MascotaMPG;
            $mascota1->user_id = $user1->id;
            $mascota1->nombre = 'Mickey';
            $mascota1->descripcion = 'Ratón ruso de color gris';
            $mascota1->tipo = 'Hamster';
            $mascota1->publica = 'Sí';
            $mascota1->megusta = 23;
            $mascota1->save();
        }

        if (MascotaMPG::where('nombre', 'May')->where('user_id', $user1->id)->count() == 0) {
            $mascota2 = new MascotaMPG;
            $mascota2->user_id = $user1->id;
            $mascota2->nombre = 'May';
            $mascota2->descripcion = 'Gata persa';
            $mascota2->tipo = 'Gato';
            $mascota2->publica = 'No';
            $mascota2->megusta = 87;
            $mascota2->save();
        }

        // Crear el segundo usuario, MPG2
        if (User::where('name', 'MPG2')->count() == 0) { 
            $user2 = new User;
            $user2->name = 'MPG2';
            $user2->email = 'MPG2@email.MPG'; 
            $user2->password = Hash::make('MPG2'); 
            $user2->email_verified_at = now(); 
            $user2->save(); 
        }

        // Crear las mascotas para el segundo usuario
        if (MascotaMPG::where('nombre', 'Will')->where('user_id', $user2->id)->count() == 0) {
            $mascota3 = new MascotaMPG;
            $mascota3->user_id = $user2->id;
            $mascota3->nombre = 'Will';
            $mascota3->descripcion = 'Perro blanco con manchas grises';
            $mascota3->tipo = 'Perro';
            $mascota3->publica = 'Sí';
            $mascota3->megusta = 34;
            $mascota3->save();
        }

        if (MascotaMPG::where('nombre', 'Luna')->where('user_id', $user2->id)->count() == 0) {
            $mascota4 = new MascotaMPG;
            $mascota4->user_id = $user2->id;
            $mascota4->nombre = 'Luna';
            $mascota4->descripcion = 'Agaporni de color verde y naranja';
            $mascota4->tipo = 'Pájaro';
            $mascota4->publica = 'No';
            $mascota4->megusta = 74;
            $mascota4->save();
        }
    }
}
