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
import retrofit2.await
import vidal.daniel.alkewallet.AlkeWalletApp
import vidal.daniel.alkewallet.model.CuentaContableRequest
import vidal.daniel.alkewallet.model.TransaccionCreaResponse
import vidal.daniel.alkewallet.model.TransaccionCuentaRequest
import vidal.daniel.alkewallet.model.TransaccionCuentaResponse
import vidal.daniel.alkewallet.model.TransaccionRequest
import vidal.daniel.alkewallet.model.TransaccionResponse
import vidal.daniel.alkewallet.model.network.RetrofitInstancia
import vidal.daniel.alkewallet.model.network.TransaccionService
import java.time.LocalDateTime
import java.util.Date

class SendMoneyViewModel : ViewModel()
{
    // Variables mutableLiveData
    //val transaccionGeneradaLiveData = MutableLiveData<TransaccionCreaResponse?>()
    val transaccionGeneradaLiveData = MutableLiveData<String?>()
    val transaccionGeneradaTerceroLiveData = MutableLiveData<TransaccionCreaResponse?>()
    val errores = MutableLiveData<String>()

    // Genera la transacción en endpoint Transaction para el usuario que envía
    //fun generaTransaccion(vp_amount: Int, vp_concept: String, vp_date: Date, vp_type: String, vp_accountId:Int, vp_userId: Int, vp_to_account_id: Int, vp_Operador: String)
    fun generaTransaccion(vp_amount: Int, vp_concept: String, vp_date: LocalDateTime, vp_type: String, vp_accountId:Int, vp_userId: Int, vp_to_account_id: Int)
    {
        // Implemento la corutina
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
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

                /*
                // Crea transacción para el usuario que envía
                // Se crea la variable que va a manejar la respuesta del servicio
                val v_llamadaApi1 : Call<TransaccionCreaResponse> = v_GeneraTransaccion.crearTransaccion(TransaccionRequest((vp_amount*-1), vp_concept, vp_date.toString(), vp_type, vp_accountId, vp_userId, vp_accountId), v_token) // Funcion desde Service
                v_llamadaApi1.enqueue(object : Callback<TransaccionCreaResponse>
                {
                    override fun onResponse(call : Call<TransaccionCreaResponse>, response : Response<TransaccionCreaResponse>)
                    {
                        Log.d("DvTec", "Resultado Antes Response ${response.body()}")

                        transaccionGenerada1 = response.body().toString()
                        if (response.isSuccessful)
                        {
                            if (transaccionGenerada1 != null)
                            {
                                Log.d("DvTec", "transaccionGenerada1 OK ")

                                // Crea transacción para el usuario destinatario
                                // Se crea la variable que va a manejar la respuesta del servicio
                                val v_llamadaApi2 : Call<TransaccionCreaResponse> = v_GeneraTransaccion.crearTransaccion(TransaccionRequest((vp_amount*1), vp_concept, vp_date.toString(), vp_type, vp_accountId, vp_userId, vp_to_account_id), v_token) // Funcion desde Service

                                v_llamadaApi2.enqueue(object : Callback<TransaccionCreaResponse>
                                {
                                    override fun onResponse(call : Call<TransaccionCreaResponse>, response : Response<TransaccionCreaResponse>)
                                    {
                                        transaccionGenerada2 = response.body().toString()
                                        if (response.isSuccessful)
                                        {
                                            if (transaccionGenerada2 != null)
                                            {
                                                // Concatenar los resultados y actualizar el LiveData
                                                //val finalResult = "$transaccionGenerada1 - $transaccionGenerada2"
                                                //transaccionGeneradaLiveData.postValue(finalResult)
                                                Log.d("DvTec", "transaccionGenerada2 OK ")
*/
/*
                                                // Crea TransacciónCuenta para el usuario que envía para actualizar saldo
                                                // Se crea la variable que va a manejar la respuesta del servicio
                                                val v_llamadaApi3 : Call<TransaccionCuentaResponse> = v_GeneraTransaccion.crearTransaccionCuenta(vp_accountId,TransaccionCuentaRequest("payment", vp_concept, (vp_amount*-1)), v_token)  // Funcion desde Service
                                                v_llamadaApi3.enqueue(object : Callback<TransaccionCuentaResponse>
                                                {
                                                    override fun onResponse(call : Call<TransaccionCuentaResponse>, response : Response<TransaccionCuentaResponse>)
                                                    {
                                                        transaccionGenerada3 = response.body().toString()
                                                        if (response.isSuccessful)
                                                        {
                                                            if (transaccionGenerada3 != null)
                                                            {
                                                                // Concatenar los resultados y actualizar el LiveData
                                                                //val finalResult = "$transaccionGenerada1 - $transaccionGenerada2"
                                                                //transaccionGeneradaLiveData.postValue(finalResult)
                                                                Log.d("DvTec", "transaccionGenerada3 OK ")
*/

                                                                // Crea TransacciónCuenta para el usuario destinatario para actualizar saldo
                                                                // Se crea la variable que va a manejar la respuesta del servicio
                                                                val v_llamadaApi4 : Call<TransaccionCuentaResponse> = v_GeneraTransaccion.crearTransaccionCuenta(vp_to_account_id,TransaccionCuentaRequest("payment", vp_concept, (vp_amount)*1), v_token)  // Funcion desde Service
                                                                v_llamadaApi4.enqueue(object : Callback<TransaccionCuentaResponse>
                                                                {
                                                                    override fun onResponse(call : Call<TransaccionCuentaResponse>, response : Response<TransaccionCuentaResponse>)
                                                                    {
                                                                        transaccionGenerada4 = response.body().toString()
                                                                        if (response.isSuccessful)
                                                                        {
                                                                            if (transaccionGenerada4 != null)
                                                                            {
                                                                                Log.d("DvTec", "transaccionGenerada4 OK ")

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
                                                                                transaccionGeneradaLiveData.postValue(finalResult)
                                                                                //}
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            errores.postValue("Error en la Api 4 - ${response.errorBody().toString()}")
                                                                        }
                                                                    }

                                                                    override fun onFailure(call: Call<TransaccionCuentaResponse>, t: Throwable)
                                                                    {
                                                                        // _txList.postValue(null)
                                                                        transaccionGeneradaLiveData.postValue(null)
                                                                        Log.d("DvTec", "onFailure transaccionGenerada4")
                                                                    }
                                                                })
/*
                                                            }
                                                        }
                                                        else
                                                        {
                                                            errores.postValue("Error en la Api 3 - ${response.errorBody().toString()}")
                                                        }
                                                    }

                                                    override fun onFailure(call: Call<TransaccionCuentaResponse>, t: Throwable)
                                                    {
                                                        // _txList.postValue(null)
                                                        transaccionGeneradaLiveData.postValue(null)
                                                        Log.d("DvTec", "onFailure transaccionGenerada3")
                                                    }
                                                })
                                            }
                                        }
                                        else
                                        {
                                            errores.postValue("Error en la Api 2 - ${response.errorBody().toString()}")
                                        }
                                    }

                                    override fun onFailure(call: Call<TransaccionCreaResponse>, t: Throwable)
                                    {
                                        // _txList.postValue(null)
                                        transaccionGeneradaLiveData.postValue(null)
                                        Log.d("DvTec", "onFailure transaccionGenerada2")
                                    }
                                })

                            }
                        }
                        else
                        {
                            Log.d("DvTec", "Resultado Retorno error ViewModel ${response.errorBody().toString()}")

                            errores.postValue("Error en la Api 1 - ${response.errorBody().toString()}")
                        }
                    }

                    override fun onFailure(call: Call<TransaccionCreaResponse>, t: Throwable)
                    {
                        transaccionGeneradaLiveData.postValue(null)
                        Log.d("DvTec", "onFailure transaccionGenerada1 $t")
                    }
                })
                // FIN Respuesta de la api
*/

            }
            catch (e: Exception)
            {
                // Error
                transaccionGeneradaLiveData.postValue(null)
                Log.d("DvTec", "Error corrutina: $e")
            }
        } // Fin Corutina







    }



}