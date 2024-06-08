package vidal.daniel.alkewallet.model.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import vidal.daniel.alkewallet.model.LoginRequest
import vidal.daniel.alkewallet.model.LoginResponse
import vidal.daniel.alkewallet.model.TransaccionCuentaRequest
import vidal.daniel.alkewallet.model.TransaccionCuentaResponse
import vidal.daniel.alkewallet.model.TransaccionRequest
import vidal.daniel.alkewallet.model.TransaccionResponse

/**
 * Api Service que contiene los métodos para realizar las transacciones y actualizar saldo del usuario logueado
*/
interface TransaccionService
{
    /**
     * Servicio que consulta las transacciones del usuario logeado
     */
    @GET("transactions")
    fun obtenerTransacciones(@Header("Authorization") token: String): Call<TransaccionResponse>

    /**
     * Servicio que genera una transacción en endpoint Transactions
     */
    @POST("transactions")
    fun crearTransaccion(    @Body request : TransaccionRequest
                            ,@Header("Authorization") token: String) : Call<TransaccionResponse>

    /**
     * Servicio que genera una transacción en endpoint Account para actualizar saldo
     */
    @POST("accounts")
    fun crearTransaccionCuenta(  @Body request : TransaccionCuentaRequest
                                ,@Header("Authorization") token: String) : Call<TransaccionCuentaResponse>

}

