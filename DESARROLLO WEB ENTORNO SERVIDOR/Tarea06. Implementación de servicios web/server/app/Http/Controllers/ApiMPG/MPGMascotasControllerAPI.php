<?php

namespace App\Http\Controllers\ApiMPG;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Models\MascotaMPG;
use Illuminate\Support\Facades\Validator;

class MPGMascotasControllerAPI extends Controller
{
    public function listarMascotasMPG(){
        $usuario = Auth::user(); 

        $mascotas = MascotaMPG ::where('user_id', $usuario->id)
        ->select('user_id', 'nombre', 'descripcion', 'tipo', 'publica', 'megusta')
        ->get();

        return response()->json($mascotas);
    }

    public function crearMascotaMPG(Request $request){

        //Primero usamos validator para validar errores en laravel, se puede hacer con if-else, pero en laravel se hace con validator
        $validacion = Validator::make($request->all(),[
            'nombre' => 'required|string|max:50',
            'descripcion' => 'required|string|max:250',
            'tipo' => 'required|in:Perro,Gato,Pájaro,Dragón,Conejo,Hamster,Tortuga,Pez,Serpiente',
            'publica' => 'required|in:Si,No',
        ],
        //Pongo los errores como quiero que salgan dentro de validator en otro array diferente.
        [
            'nombre.required' => 'El nombre es obligatorio.',
            'nombre.max' => 'El nombre no puede superar los 50 caracteres.',
            'descripcion.required' => 'La descripción es obligatoria.',
            'descripcion.max' => 'La descripción no puede superar los 250 caracteres.',
            'tipo.required' => 'El tipo es obligatorio.',
            'tipo.in' => 'El tipo debe estar dentro de los permitidos.',
            'publica.required' => 'Debe indicar si es pública obligatoriamente.',
            'publica.in' => 'El valor de pública debe ser Si o No.',
        ]);

        //Si hay algún error en la validación lo mandaremos con un json y el error junto a 400.
        if ($validacion->fails()) {
            return response()->json([
                'errores' => $validacion->errors()
            ], 400); 
        }

        //guardamos los datos validados dentro de una variable para poder crear la nueva mascota
        $datosValidados = $validacion->validated();
        //Creamos la mascota usando la variable donde hemos guardado los datos y con el id del usuario al que le pertenece.
        $mascota = new MascotaMPG();
        $mascota->nombre = $datosValidados['nombre'];
        $mascota->descripcion = $datosValidados['descripcion'];
        $mascota->tipo = $datosValidados['tipo'];
        $mascota->publica = $datosValidados['publica']; 
        $mascota->user_id = Auth::id();
    
        $mascota->save();

        //Si todo ha salido bien, retornamos el id que se le ha asignado a la mascota, mi nombre y el codigo 200.
        return response()->json([
            'id' => $mascota->id,
            'nombreCreador' => 'María Palomares Gallo'
        ], 200);

    }

    public function cambiarMascotaMPG(MascotaMPG $mascota, Request $request){

        //Comprobamos que el id del usuario sea igual que el que esta intentando cambiar los datos, si
        //no es así mandamos un error con el codigo 403
        if ($mascota->user_id !== Auth::id()) {
            return response()->json([
                'mensaje' => 'No puedes modificar una mascota que no es tuya.'
            ], 403);
        }

         // Verificamos que los datos recibidos son JSON, si no se manda un mensaje de error.
        if (!$request->isJson()) {
            return response()->json([
                'mensaje' => 'Se esperaba un contenido de tipo JSON.'
            ], 403);
        }

        // Recogemos los datos del JSON y los metemos en una variable.
        $datos = $request->json()->all();

        // Validamos los datos y describimos los errores.
        $validacion = Validator::make($datos, [
            'descripcion' => 'required|string|max:250',
            'publica' => 'required|in:Si,No',
        ],
        [
            'descripcion.required' => 'La descripción es obligatoria.',
            'descripcion.max' => 'La descripción no puede superar los 250 caracteres.',
            'publica.required' => 'Debe indicar si es pública.',
            'publica.in' => 'El valor de pública debe ser Si o No.',
        ]);

        //Si en la validación encuentra errores los mandamos.
        if ($validacion->fails()) {
            return response()->json([
                'errores' => $validacion->errors()
            ], 400);
        }

        // Si todo está bien, actualizamos la mascota y mandamos un mensaje diciendo que fue actualizada, el id y el codigo 200.
        $mascota->descripcion = $datos['descripcion'];
        $mascota->publica = $datos['publica']; 
        $mascota->save();

        return response()->json([
            'mensaje' => 'Mascota actualizada correctamente',
            'id' => $mascota->id
        ], 200);

    }

    public function destroy($mascota) {
        //Si el ID no es un número mandamos un mensaje de error y un codigo 400.
        if (!is_numeric($mascota)) {
            return response()->json([
                'mensaje' => 'El ID de la mascota debe ser un número.'
            ], 400);
        }

        // Buscar la mascota por ID.
        $mascotaEncontrada = MascotaMPG::find($mascota);

        // Si la mascota no existe o no es del usuario autenticado mandamos un error 200 y un mensaje de error
        if (!$mascotaEncontrada || $mascotaEncontrada->user_id !== Auth::id()) {
            return response()->json([
                'mensaje' => 'Mascota no encontrada o no te pertenece.'
            ], 200);
        }

        //Si la mascota existe y es del usuario la eliminamos y mandamos un mensaje diciendo que se ha eliminado y el codigo 200
        $mascotaEncontrada->delete();

        return response()->json([
            'mensaje' => 'Mascota eliminada correctamente.'
        ], 200);
    }

}
