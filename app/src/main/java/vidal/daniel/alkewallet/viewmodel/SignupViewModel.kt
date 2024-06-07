package vidal.daniel.alkewallet.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vidal.daniel.alkewallet.model.CuentaContableRequest
import vidal.daniel.alkewallet.model.CuentaContableResponse
import vidal.daniel.alkewallet.model.LoginRequest
import vidal.daniel.alkewallet.model.LoginResponse
import vidal.daniel.alkewallet.model.LoginUser
import vidal.daniel.alkewallet.model.SignupModel
import vidal.daniel.alkewallet.model.SignupRequest
import vidal.daniel.alkewallet.model.SignupResponse
import vidal.daniel.alkewallet.model.network.AccountService
import vidal.daniel.alkewallet.model.network.CuentaContableService
import vidal.daniel.alkewallet.model.network.LoginService
import vidal.daniel.alkewallet.model.network.RetrofitInstancia

/**
 * ViewModel para la creación de usuario
 *
 */
class SignupViewModel : ViewModel()
{
    // Variable que va a informar si hay error
    val creadoLiveData = MutableLiveData<Boolean>()
    // Variable que va a informar si hay error
    val errorLiveData = MutableLiveData<String?>()
    // Variable que tendrá los datos del usuario creado
    var usuarioLiveData = MutableLiveData<String?>()
    var userIdLiveData = MutableLiveData<Int?>()

    /**
     * Funcion que implementa una corrutina para llamar a la api y crear el usuario
     */
    fun crearUsuario(first_name : String,last_name : String,email : String,password : String,roleId : Int?,points: Int?)
    {
        // Implemento la corutina
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                // Aca llamaremos a la Api
                // Esta es la instancia de Retrofit
                var v_crearUsuario : AccountService = RetrofitInstancia.retrofit.create(AccountService::class.java)

                // Se crea la variabae que va a manejar la respuesta del servicio
                val v_llamadaApi : Call<SignupResponse> = v_crearUsuario.crearUsuario(SignupRequest(first_name, last_name, email, password, roleId, points)) // Funcion desde Service

                // Respuesta de la api
                v_llamadaApi.enqueue(object : Callback<SignupResponse> {

                    override fun onResponse (call : Call<SignupResponse>, response : Response<SignupResponse>)
                    {
                        // Obtengo la respuesta desde model LoginResponse
                        var respuesta : SignupResponse? = response.body()

                        if (response.isSuccessful)
                        {
                            if (respuesta?.first_name != null)
                            {
                                creadoLiveData.postValue(true)
                                userIdLiveData.postValue(respuesta?.userId)
                                errorLiveData.postValue(null)
                            }
                            else
                            {
                                creadoLiveData.postValue(false)
                                userIdLiveData.postValue(null)
                                errorLiveData.postValue(respuesta?.error)
                            }
                        }
                        else
                        {
                            userIdLiveData.postValue(null)
                            creadoLiveData.postValue(false)
                            errorLiveData.postValue(respuesta?.error)
                        }
                    }

                    override fun onFailure (call : Call<SignupResponse>, t : Throwable )
                    {
                        userIdLiveData.postValue(null)
                        usuarioLiveData.postValue(null)
                        creadoLiveData.postValue(false)
                        errorLiveData.postValue(null)
                    }
                })
                // FIN Respuesta de la api

            }
            catch (e: Exception)
            {
                // Error
                userIdLiveData.postValue(null)
                usuarioLiveData.postValue(null)
                creadoLiveData.postValue(false)
                errorLiveData.postValue(null)
            }
             




            /*
                // val response = ApiClient.apiService.login(username, password)
                // Respuestas:
                // status = 201 , "error": ""
                // status = 401 o 403 , "error": "No autorizado"

                var v_status    : Int = 201
                if ( v_status == 201 ) // Valores de prueba
                {
                    signupResultLiveData.postValue(true)
                    errorResultLiveData.postValue("")
                }
                else
                {
                    signupResultLiveData.postValue(false)
                    errorResultLiveData.postValue("No autorizado")
                }
            }
            catch (e: Exception)
            {
                // Error
                signupResultLiveData.postValue(false)
                errorResultLiveData.postValue("No autorizado")
            }
            */


        } // Fin Corutina
    } // fun crearUsuario


}