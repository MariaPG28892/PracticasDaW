<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\MascotaMPG;
use Illuminate\Support\Facades\Auth;

class MascotaControllerMPG extends Controller
{
    //Función para mostrar el formulario, le pasamos la vista donde se encuentra
    public function mostrarFormularioNuevaMascota(){
        return view('privada.formmascotaMPG');
    }

    public function recibirDatosMascota(Request $request)
    {
        // Validar los datos del formulario con resquest y lo guardamos todo en el array datos validados
        $datosvalidados = $request->validate([
            'nombre' => 'required|string|min:4|max:50',
            'descripcion' => 'required|string|max:250',
            'publica' => 'required|string|in:Sí,No',
            'tipo' => 'required|string|in:Perro,Gato,Pájaro,Dragón,Conejo,Hamster,Tortuga,Pez,Serpiente'
        ]);

        //Creamos la mascota y la guardamos, le aplicamos los datos recogidos en el array datosValidados.
        $mascota = new MascotaMPG();
        $mascota->user_id = auth()->id();
        $mascota->nombre = $datosvalidados['nombre'];
        $mascota->descripcion = $datosvalidados['descripcion'];
        $mascota->publica = $datosvalidados['publica'];
        $mascota->tipo = $datosvalidados['tipo'];
        $mascota->save();

        //creo una variable con el nombre para pasar mis datos a la plantilla y que lo muestre
        $nombre = "María Palomares Gallo";
        //Pasamos los datos a la vista de mascota guardada.
        return view('privada.mascotaGuardada', [
            'nombreMascota'=>$mascota->nombre,
            'mascota_id' => $mascota->id,
            'nombre' => $nombre
        ]);
    }

    //Aqui le pasamos el id que hemos recogido en la ruta get
    public function eliminarMascota($id)
    {
        // Buscar la mascota con find
        $mascota = MascotaMPG::find($id);

        // Si la mascota no existe, redirigir con un mensaje de error
        if (!$mascota) {
            return view('privada.mascota_eliminadaMPG')->with('error', 'Mascota no encontrada.');
        }

        // Verificar si la mascota pertenece al usuario autenticado y si no pertenece mandamos error.
        if ($mascota->user_id !== Auth::id()) {
            return view('privada.mascota_eliminadaMPG')->with('error', 'No puedes eliminar una mascota que no te pertenece.');
        }

        // Eliminar la mascota usando delete
        $mascota->delete();

        // Pasar datos a la vista
        return view('privada.mascota_eliminadaMPG', [
            'mascota_id' => $id
        ]);
    }

}
