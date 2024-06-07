package vidal.daniel.alkewallet.model.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import vidal.daniel.alkewallet.model.LoginRequest
import vidal.daniel.alkewallet.model.LoginResponse
import vidal.daniel.alkewallet.model.LoginUser

/**
 * Api Service que contiene los métodos para logear al usuario
 */
interface LoginService
{
    /**
     * Servicio que hace login recibe como parámetros el email y contraseña encapsulado
     * en un objeto de Request
     */
    @POST("auth/login")
    fun hacerLogin(@Body request : LoginRequest) : Call<LoginResponse>

    /**
     * Servicioque obtiene la información del usuario que se logeó
     * Recibe como parámetro el TokenAcceso como header con la palabra BEARER
     */
    @GET("auth/me")
    fun obtenerInfoLogin(@Header("Authorization") token: String) : Call<LoginUser>

}