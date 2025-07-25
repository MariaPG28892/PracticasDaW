<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ApiMPG\Auth\LoginController;
use App\Http\Controllers\ApiMPG\MPGMascotasControllerAPI;
use App\Models\MascotaMPG;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

/** Respuesta por defecto cuando no hay usuario autenticado*/
Route::get('/login', function () {
    return response()->json(["mensaje"=>"Es necesaria autenticación para acceder"],401);
})->name('login');

/** Ruta que permite a un usuario autenticado ver sus datos completos (JSON) tras autenticación.
 *  HTTP GET
 *  http://localhost:.../api/user
 */
Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

/** Ruta que permite a un usuario hacer login vía API. 
 *  HTTP POST
 *  http://localhost:.../api/login
 */
Route::post('/login', [LoginController::class,'doLogin']);

/** Ruta que permite a un usuario hacer logout (borrar tokens) 
 *  HTTP Cualquiera
 *  http://localhost:.../api/logout
 */
Route::any('/logout', [LoginController::class,'doLogout'])->middleware('auth:sanctum');

Route::get('/mascotasMPG', [MPGMascotasControllerAPI::class, 'listarMascotasMPG'])->middleware('auth:sanctum');

Route::post('/crearmascotaMPG', [MPGMascotasControllerAPI::class, 'crearMascotaMPG'])->middleware('auth:sanctum');

Route::put('/mascotaMPG/{mascota}', [MPGMascotasControllerAPI::class, 'cambiarMascotaMPG'])->middleware('auth:sanctum');

Route::delete('/mascotaMPG/{mascota}', [MPGMascotasControllerAPI::class, 'destroy'])->middleware('auth:sanctum');
