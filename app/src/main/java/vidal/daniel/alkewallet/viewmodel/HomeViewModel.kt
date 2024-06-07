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
import vidal.daniel.alkewallet.model.CuentaContableModel
import vidal.daniel.alkewallet.model.CuentaContableResponse
import vidal.daniel.alkewallet.model.TransaccionModel
import vidal.daniel.alkewallet.model.TransaccionResponse
import vidal.daniel.alkewallet.model.network.CuentaContableService
import vidal.daniel.alkewallet.model.network.RetrofitInstancia
import vidal.daniel.alkewallet.model.network.TransaccionService
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel : ViewModel()
{
    //Variable de lista
    // private val _txList = MutableLiveData<List<TransaccionResponse>?>()
    //Esta variable es la que se va a encargar de propagar el cambio a sus observadores
    // val homeTx : LiveData<List<TransaccionResponse>?>  = _txList

     val homeTx          = MutableLiveData<List<TransaccionModel>?>()

    val miCuentaContableLiveData = MutableLiveData<List<CuentaContableModel>?>()

     //Método para cargar la lista de usuarios desde una fuente de datos (API, base de datos, etc.)
    fun loadTx()
    {
        // Implemento la corutina
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                // Aca llamaremos a la Api
                // Esta es la instancia de Retrofit
                var v_listaTransacciones = RetrofitInstancia.retrofit.create(TransaccionService::class.java)

                // Se crea la variabae que va a manejar la respuesta del servicio
                val v_token = "Bearer ${AlkeWalletApp.tokenAcceso}"

                // Se crea la variabae que va a manejar la respuesta del servicio
                val v_llamadaApi : Call<TransaccionResponse> = v_listaTransacciones.obtenerTransacciones(v_token) // Funcion desde Service

                Log.d("TransactionViewModel", v_llamadaApi.toString())

                v_llamadaApi.enqueue(object : Callback<TransaccionResponse>
                {
                    override fun onResponse(call : Call<TransaccionResponse>, response : Response<TransaccionResponse>)
                    {
                        // val transactions = transactionsResponse?.data
                        Log.d("TransactionViewModel", "Dentro de apillamada")

                        var transaccionesLista = response.body()
                        if (response.isSuccessful)
                        {
                            // _txList.postValue(transaccionesLista)
                            if (transaccionesLista != null)
                            {
                                homeTx.postValue(transaccionesLista.data)
                            }
                            // Log.d("TransactionViewModel", "Error: $transaccionesLista")
                        }
                        else
                        {
                            // _txList.postValue(null)
                            homeTx.postValue(null ?: emptyList())
                        }
                    }

                    override fun onFailure(call: Call<TransaccionResponse>, t: Throwable)
                    {
                        // _txList.postValue(null)
                        homeTx.postValue(emptyList())
                    }
                })
                // FIN Respuesta de la api
            }
            catch (e: Exception)
            {
                // Error
                // _txList.postValue(null)
                homeTx.postValue(null ?: emptyList())
                Log.d("TransactionViewModel", "Error: $e")
            }
        } // Fin Corutina
    }


    fun obtenerMiCuentaContable()
    {
    // Implemento la corutina
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                // Aca llamaremos a la Api
                // Esta es la instancia de Retrofit
                var v_ObtieneMiCuentaContable = RetrofitInstancia.retrofit.create(CuentaContableService::class.java)

                // Se crea la variabae que va a manejar la respuesta del servicio
                val v_token     = "Bearer ${AlkeWalletApp.tokenAcceso}"
                val v_idCuenta  = {AlkeWalletApp.vg_idCuentaUsuarioLogueado}

                // Se crea la variabae que va a manejar la respuesta del servicio
                // val v_llamadaApi : Call<CuentaContableResponse> = v_ObtieneMiCuentaContable.consultarMiCuentaContable(v_idCuenta, v_token) // Funcion desde Service
                val v_llamadaApi : Call<List<CuentaContableModel>> = v_ObtieneMiCuentaContable.obtenerCuentaContable(v_token) // Funcion desde Service

                // Log.d("TransactionViewModel", v_llamadaApi.toString())

                v_llamadaApi.enqueue(object : Callback<List<CuentaContableModel>>
                {
                    override fun onResponse(call : Call<List<CuentaContableModel>>, response : Response<List<CuentaContableModel>>)
                    {
                        Log.d("DvTec", "Dentro de apillamada")

                        var miCuentaContable = response.body()
                        if (response.isSuccessful)
                        {
                            if (miCuentaContable != null)
                            {
                                miCuentaContableLiveData.postValue(miCuentaContable)
                            }
                        }
                        else
                        {
                            miCuentaContableLiveData.postValue(miCuentaContable)
                        }
                    }

                    override fun onFailure(call: Call<List<CuentaContableModel>>, t: Throwable)
                    {
                        miCuentaContableLiveData.postValue(null)
                    }
                })
                // FIN Respuesta de la api
            }
            catch (e: Exception)
            {
                // Error
                miCuentaContableLiveData.postValue(null)
                Log.d("TransactionViewModel", "Error: $e")
            }
        } // Fin Corutina
    }



    // Función para convertir a formato fecha un string YYYY-MM-DD
    fun stringToDate(dateString: String, format: String): Date
    {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.parse(dateString) ?: Date() // Si la conversión falla, devuelve la fecha actual
    }





}