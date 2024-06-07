package vidal.daniel.alkewallet.model.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import vidal.daniel.alkewallet.model.LoginRequest
import vidal.daniel.alkewallet.model.LoginResponse
import vidal.daniel.alkewallet.model.LoginUser
import vidal.daniel.alkewallet.model.SignupRequest
import vidal.daniel.alkewallet.model.SignupResponse

/**
 * Api Service que contiene los métodos para la creación del usuario
 */
interface AccountService
{
    /**
     * Servicio que crea el usuario y recibe como parámetros el first_name, last_name, email, password, roleid y points encapsulado en un objeto de Request
     */
    @POST("users")
    fun crearUsuario(@Body request : SignupRequest) : Call<SignupResponse>
}