package vidal.daniel.alkewallet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vidal.daniel.alkewallet.model.LoginUser

/**
 * ViewModel encargado de hacer el login de la app
 *
 */

class LoginViewModel : ViewModel()
{
    // Variable que va a informar a la vista, si el login es ok o no
    val loginResultLiveData = MutableLiveData<Boolean>()

    /**
     * Funcion que implementa una corrutina para llamar a la api
     */
    fun hacerLogin(email : String, contrasena : String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                // Aca llamaremos a la Api
                //
                // val response = ApiClient.apiService.login(username, password)

                if ( (email == "daniel@dvtec.cl" || email == "johanna@dvtec.cl" || email == "barbara@dvtec.cl" || email == "josue@dvtec.cl" || email == "jack@dvtec.cl") && contrasena == "123") // Valores de prueba
                {
                    loginResultLiveData.postValue(true)
                }
                else
                {
                    loginResultLiveData.postValue(false)
                }
            }
            catch (e: Exception)
            {
                // Error
                 loginResultLiveData.postValue(false)
            }
        }
    }

    // Para retornar 3 -> Triple<String, String, Int>
    // Acá debería usar el UserLogin accediendo a la api Authentication->GET
    fun datosLogin(email : String) : (String) -> Triple<String, String, String>
    {
        var first_name : String = ""
        var last_name : String = ""
        var img_perfil : String = ""

        // Aca llamaremos a la Api y obtenemos datos del usuario logueado

        // Datos de prueba
        if (email == "daniel@dvtec.cl")
        {
            first_name = "Daniel"
            last_name = "Vidal"
            img_perfil = "dv"
        }
        else if (email == "josue@dvtec.cl")
        {
            first_name = "Josué"
            last_name = "Vidal"
            img_perfil = "jv"
        }
        else if (email == "barbara@dvtec.cl")
        {
            first_name = "Bárbara"
            last_name = "Vidal"
            img_perfil = "bv"
        }
        else if (email == "johanna@dvtec.cl")
        {
            first_name = "Johanna"
            last_name = "Paredes"
            img_perfil = "jp"
        }
        else if (email == "jack@dvtec.cl")
        {
            first_name = "Jack"
            last_name = "Daniels"
            img_perfil = "jd"
        }
        return { Triple(first_name, last_name, img_perfil) }
    }

}