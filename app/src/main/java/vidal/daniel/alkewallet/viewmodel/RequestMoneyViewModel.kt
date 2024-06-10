package vidal.daniel.alkewallet.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vidal.daniel.alkewallet.AlkeWalletApp
import vidal.daniel.alkewallet.model.TransaccionCuentaRequest
import vidal.daniel.alkewallet.model.TransaccionCuentaResponse
import vidal.daniel.alkewallet.model.network.RetrofitInstancia
import vidal.daniel.alkewallet.model.network.TransaccionService

class RequestMoneyViewModel : ViewModel()
{
    // Variables MutableLiveData
    val depositoGeneradoLiveData = MutableLiveData<String?>()
    val errores = MutableLiveData<String>()

    // Genera el depósito en endpoint ACCOUNT para el usuario que envía
    fun generaDeposito(vpAmount: Int, vpConcept: String, vpNumeroCuentaDestino : Int)
    {
        Log.d("DvTec", "Entra a funcion generaDeposito")

        // Implemento la corutina
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                Log.d("DvTec", "Entra a corrutina generaDeposito")

                // Declaro objetos que recibirán las respuesta
                var transaccionGenerada1 : String? = ""
                var transaccionGenerada2 : String? = ""
                var transaccionGenerada3 : String? = ""
                var transaccionGenerada4 : String? = ""

                // Aca llamaremos a la Api
                // Esta es la instancia de Retrofit
                var v_GeneraTransaccion = RetrofitInstancia.retrofit.create(TransaccionService::class.java)

                // Se crea la variable token
                val v_token = "Bearer ${AlkeWalletApp.tokenAcceso}"

                // Crea TransacciónCuenta para el usuario destinatario
                // Se crea la variable que va a manejar la respuesta del servicio
                val v_llamadaApi4 : Call<TransaccionCuentaResponse> = v_GeneraTransaccion.crearTransaccionCuenta(vpNumeroCuentaDestino,
                    TransaccionCuentaRequest("topup", vpConcept, vpAmount), v_token)  // Funcion desde Service

                Log.d("DvTec", "Asigna v_llamadaApi4 ")

                v_llamadaApi4.enqueue(object : Callback<TransaccionCuentaResponse>
                {
                    override fun onResponse(call : Call<TransaccionCuentaResponse>, response : Response<TransaccionCuentaResponse>)
                    {
                        transaccionGenerada4 = response.body().toString()
                        if (response.isSuccessful)
                        {
                            if (transaccionGenerada4 != null)
                            {
                                Log.d("DvTec", "Request TransaccionGenerada4 OK ")

                                // Si no hay ningún error, procesa
                                //if (isFailed)
                                //{
                                //    // Revertir la transacción
                                //    // Puedes llamar a una función que maneje la reversión aquí
                                //}
                                // else
                                // {
                                // Concatenar los resultados y actualizar el LiveData
                                val finalResult = "$transaccionGenerada1 - $transaccionGenerada2 - $transaccionGenerada3 - $transaccionGenerada4"
                                depositoGeneradoLiveData.postValue(finalResult)
                                //}
                            }
                        }
                        else
                        {
                            errores.postValue("Error en la Api 4 - ${response.errorBody().toString()}")
                            Log.d("DvTec", "Error en la Api 4 - ${response.errorBody().toString()}")
                        }
                    }

                    override fun onFailure(call: Call<TransaccionCuentaResponse>, t: Throwable)
                    {
                        depositoGeneradoLiveData.postValue(null)
                        Log.d("DvTec", "onFailure transaccionGenerada4: $t")
                    }
                })
            }
            catch (e: Exception)
            {
                // Error
                depositoGeneradoLiveData.postValue(null)
                Log.d("DvTec", "Error corrutina: $e")
            }
        } // Fin Corutina

    }

}