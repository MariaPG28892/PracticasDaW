<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
//Usamos la parte de usuario para poder usar belong, para poder juntar ambas tablas con el id del usuario.
use App\Models\User; 


class MascotaMPG extends Model
{
    use HasFactory;

    protected $table = 'mascotas'; // Define el nombre correcto de la tabla

    //En fillable tenemos que poner las columnas de cada tabla.
    protected $fillable = ['user_id', 'nombre', 'descripcion', 'tipo', 'publica', 'megusta'];

    //La función user para poder usar el id del usuario con las mascotas.
    public function user()
    {
        return $this->belongsTo(User::class);
    }
}

