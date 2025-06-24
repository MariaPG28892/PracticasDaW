<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Auth\LoginController;

use App\Models\MascotaMPG;
use App\Http\Controllers\MascotaControllerMPG;

//Ruta a la zona pública (simplemente accediendo a / vía GET)
Route::get('/', function () {
    $mascotas = MascotaMPG :: where ('publica', 'Sí')->get();
    return view('principal', ['mascotasMPG'=>$mascotas]);
})->name('zonapublica');

//Ruta a la zona privada (simplemente accediendo a /zonaprivada vía GET)
Route::get('/zonaprivada', function () {
    $user = Auth::user(); // Obtener usuario autenticado
    $mascotas = $user->mascotas;
    return view('privada.principal', ['mascotasMPG'=>$mascotas]);
})->middleware('auth')->name('zonaprivada');

//Creamos una ruta nombrada (formlogin) tipo GET a '/login' que mostrará el formulario
Route::get('/login', [LoginController::class, 'mostrarFormularioLoginMPG'])->name('formlogin');
//Creamos una ruta nombrada (login) tipo POST a '/login' que procesará el formulario
Route::post('/login', [LoginController::class, 'loginMPG'])->name('login');
//Creamos una ruta nombrada (logout) tipo POST a '/logout' que cerrará la sesión
Route::get('/logout', [LoginController::class, 'logoutMPG'])->name('logout');

//Rutaa get para mostrar el formulario para crear la nueva mascota
Route::get('/mascota/nueva', [MascotaControllerMPG::class, 'mostrarFormularioNuevaMascota'])->middleware('auth')->name('formmascotaMPG');
//Ruta post para mandar los datos de la mascota y procesarlos
Route::post('/mascota/nueva', [MascotaControllerMPG::class, 'recibirDatosMascota'])->middleware('auth')->name('nuevamascotaMPG');

// Ruta para eliminar la mascota (usando parámetro id en la URL para poder eliminarlo)
Route::post('/mascota/eliminar/{id}', [MascotaControllerMPG::class, 'eliminarMascota'])->middleware('auth')->name('eliminarmascotaMPG');
