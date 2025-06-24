<?php

namespace App\Http\Controllers\ApiMPG\Auth;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\JsonResponse;
use App\Models\User;

class LoginController extends Controller
{
    public function doLogin(Request $request): JsonResponse
    {
        // Creamos un validador
        $validator = Validator::make($request->only(['email','password']), [
            'email' => 'required|email',
            'password' => 'required',
        ]);

        // Si no se valida, se retorna 422 Unprocessable Entity
        if ($validator->fails()) {
            return response()->json(['mensaje' => 'Email o password no proporcionados o no válidos.'], 422);
        }

        $data = $validator->safe()->only('email', 'password');

        // Validamos usuario
        if (!Auth::validate($data)) {
            return response()->json([
                'mensaje' => 'Credenciales no válidas',
            ], 401);
        }

        $user = User::where('email', $validator->getData()['email'])->first();
        $token = $user->createToken('TOKEN-ACCESO-API');

        return response()->json([
            'mensaje' => 'Login correcto',
            'token' => $token->plainTextToken
        ]);
    }

    public function doLogout(Request $request): JsonResponse
    {
        $eliminados = $request->user()->currentAccessToken()->delete();

        return response()->json([
            'mensaje' => $eliminados > 0 ? 'Logout correcto' : 'El usuario no estaba autenticado',
            'codigo' => $eliminados > 0 ? 1 : 0
        ], 200);
    }
}

