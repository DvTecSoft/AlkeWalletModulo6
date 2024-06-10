package vidal.daniel.alkewallet.model.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import vidal.daniel.alkewallet.model.CuentaContableModel
import vidal.daniel.alkewallet.model.CuentaContableRequest
import vidal.daniel.alkewallet.model.CuentaContableResponse

/**
 * Api Service que contiene los métodos para de la cuenta contable
 */
interface CuentaContableService
{

    /**
     * Servicio que consulta si existe una cuenta contable para el id
     */
    @GET("accounts/me")
    fun obtenerCuentaContable(@Header("Authorization") token: String) : Call<List<CuentaContableModel>>

    /**
     * Servicio que crea el usuario y recibe como parámetros el first_name, last_name, email, password, roleid y points
     * encapsulado en un objeto de Request
     */
    @POST("accounts")
    fun crearCuentaContable(@Body request : CuentaContableRequest
                            ,@Header("Authorization") token: String) : Call<CuentaContableResponse>

    /*
    /**
     * Servicio que consulta los datos de una cuenta contable
     */
    @GET("accounts/{id}")
    fun consultarMiCuentaContable(@Query("id") id: () -> Int?
                                ,@Header("Authorization") token: String) : Call<CuentaContableResponse>

    */


}