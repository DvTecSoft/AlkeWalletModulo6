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

/**
 * ViewModel para crear usuarios
 *
 */
class SignupViewModel : ViewModel()
{
    // Variable que va a informar a la vista, si se crea o no
    val signupResultLiveData = MutableLiveData<Boolean>()
    val errorResultLiveData = MutableLiveData<String>()
    val error : LiveData<String> get() = errorResultLiveData

    /**
     * Funcion que implementa una corrutina para llamar a la api
     */
    fun crearUsuario(first_name : String,last_name : String,email : String,password : String,roleId : Int?,points: Int?)
    {

        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                // Aca llamaremos a la Api
                //
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
        }
    }

}