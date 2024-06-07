package vidal.daniel.alkewallet.model.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import vidal.daniel.alkewallet.model.TransaccionResponse

interface TransaccionService
{
    /**
     * Servicio que consulta las transacciones del usuario logeado
     */
    @GET("transactions")
    fun obtenerTransacciones(@Header("Authorization") token: String): Call<TransaccionResponse>
}

