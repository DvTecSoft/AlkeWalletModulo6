package vidal.daniel.alkewallet.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.tokenAcceso
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.usuarioLogeado
import vidal.daniel.alkewallet.model.CuentaContableModel
import vidal.daniel.alkewallet.model.CuentaContableRequest
import vidal.daniel.alkewallet.model.CuentaContableResponse
import vidal.daniel.alkewallet.model.LoginRequest
import vidal.daniel.alkewallet.model.LoginResponse
import vidal.daniel.alkewallet.model.LoginUser
import vidal.daniel.alkewallet.model.network.CuentaContableService
import vidal.daniel.alkewallet.model.network.LoginService
import vidal.daniel.alkewallet.model.network.RetrofitInstancia
import java.util.Date

/**
 * ViewModel encargado de hacer el login de la app
 *
 */

class LoginViewModel : ViewModel()
{
    // Variable que va a informar a la vista, si el login es ok o no
    // val loginResultLiveData = MutableLiveData<Boolean>()

    // Variable que va a informar a la vista, el token
    var tokenLiveData = MutableLiveData<String?>()
    // Variable que va a informar a la vista, si el login es ok o no
    var errorLiveData = MutableLiveData<String?>()
    // VAriable livedata cuando tenga la información del usuario
    var usuarioLiveData = MutableLiveData<LoginUser?>()
    // Variable livedata que tendrá la cuenta del usuario si tiene.
    var validaCuentaContableLiveData = MutableLiveData<Boolean>()
    // Variable livedata que indicará si se creó la cuenta contable de un usuario nuevo
    var cuentaContableCreadaLiveData = MutableLiveData<Boolean>()
    // Variable livedata que indicará si se creó la cuenta contable de un usuario nuevo
    var idCuentaContableLoginLivedata = MutableLiveData<List<CuentaContableModel>?>()

    val combinedData = MediatorLiveData<Pair<Boolean?, List<CuentaContableModel>?>>().apply {
        addSource(cuentaContableCreadaLiveData) { value = Pair(it, idCuentaContableLoginLivedata.value) }
        addSource(idCuentaContableLoginLivedata) { value = Pair(cuentaContableCreadaLiveData.value, it) }
    }

    /**
     * Funcion que implementa una corrutina para llamar a la api
     */
    fun hacerLogin(email : String, contrasena : String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                // Aca llamaremos a la Api
                // Esta es la instancia de Retrofit
                var login : LoginService = RetrofitInstancia.retrofit.create(LoginService::class.java)

                // Se crea la variabae que va a manejar la respuesta del servicio
                val llamadaApi : Call<LoginResponse> = login.hacerLogin(LoginRequest(email, contrasena)) // Funcion desde Service

                // Respuesta de la api
                llamadaApi.enqueue(object : Callback<LoginResponse>{
                    override fun onResponse (call : Call<LoginResponse>, response : Response<LoginResponse>)
                    {
                        // Obtengo la respuesta desde model LoginResponse
                        var respuesta : LoginResponse? = response.body()

                        if (response.isSuccessful)
                        {
                            if (respuesta?.accessToken != null)
                            {
                                tokenLiveData.postValue(respuesta.accessToken)
                                errorLiveData.postValue(null)
                            }
                            else
                            {
                                tokenLiveData.postValue(null)
                                errorLiveData.postValue(respuesta?.error)
                            }
                        }
                        else
                        {
                            tokenLiveData.postValue(null)
                            errorLiveData.postValue(respuesta?.error)
                        }
                    }

                    override fun onFailure (call : Call<LoginResponse>, t : Throwable )
                    {
                        tokenLiveData.postValue(null)
                        errorLiveData.postValue(null)
                    }
                })
                // FIN Respuesta de la api
            }
            catch (e: Exception)
            {
                // Error
                tokenLiveData.postValue(null)
                errorLiveData.postValue(null)
            }
        }
    }

    // Obtengo los datos del usuario
    fun datosLogin()
    {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Aca llamaremos a la Api

                // Esta es la instancia de Retrofit
                var getUsuario = RetrofitInstancia.retrofit.create(LoginService::class.java)

                // Se crea la variabae que va a manejar la respuesta del servicio
                val token = "Bearer $tokenAcceso"

                val usuarioLlamada : Call<LoginUser> = getUsuario.obtenerInfoLogin(token) // Funcion desde Service

                usuarioLlamada.enqueue(object : Callback<LoginUser>
                {
                    override fun onResponse(call : Call<LoginUser>, response : Response<LoginUser>)
                    {
                        var usuarioLogin = response.body()
                        if (response.isSuccessful)
                        {
                            usuarioLiveData.postValue(usuarioLogin)
                        }
                        else
                        {
                            usuarioLiveData.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<LoginUser>, t: Throwable)
                    {
                        usuarioLiveData.postValue(null)
                    }
                })

            } catch (e: Exception) {
                // Error
                // loginResultLiveData.postValue(false)
                usuarioLiveData.postValue(null)
            }
        }
    }
    // FIN Obtengo los datos del usuario

    // Valida si el usuario logueado tiene cuenta contable
    fun validaCuentaContable() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Aca llamaremos a la Api
                // Esta es la instancia de Retrofit
                var validaCuentaContable = RetrofitInstancia.retrofit.create(CuentaContableService::class.java)

                // Creo variable token
                val v_token = "Bearer $tokenAcceso"

                val cuentaContableLlamada: Call<List<CuentaContableModel>> = validaCuentaContable.obtenerCuentaContable(v_token) // Funcion desde Service

                cuentaContableLlamada.enqueue(object : Callback<List<CuentaContableModel>>
                {
                    override fun onResponse(call: Call<List<CuentaContableModel>>, response: Response<List<CuentaContableModel>>)
                    {
                        var cuentaContable = response.body()
                        if (response.isSuccessful)
                        {
                            if (cuentaContable?.size == 0)
                            {
                                validaCuentaContableLiveData.postValue(true)

                                idCuentaContableLoginLivedata.postValue(cuentaContable)

                            }
                            else
                            {
                                validaCuentaContableLiveData.postValue(false)
                                idCuentaContableLoginLivedata.postValue(cuentaContable)
                            }
                        }
                        else
                        {
                            validaCuentaContableLiveData.postValue(false)
                            idCuentaContableLoginLivedata.postValue(cuentaContable)
                        }
                    }

                    override fun onFailure(call: Call<List<CuentaContableModel>>, t: Throwable) {
                        validaCuentaContableLiveData.postValue(false)
                        idCuentaContableLoginLivedata.postValue(null)
                    }
                })
            } catch (e: Exception) {
                validaCuentaContableLiveData.postValue(false)
                idCuentaContableLoginLivedata.postValue(null)
            }
        }
    }
    // FIN Valida si el usuario logueado tiene cuenta contable

    // Realiza la creación de la cuenta contable si el usuario logueado no tiene
    fun crearCuentaContable() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Aca llamaremos a la Api
                // Esta es la instancia de Retrofit
                var crearCuentaContable = RetrofitInstancia.retrofit.create(CuentaContableService::class.java)

                // Creo variables
                val v_token = "Bearer $tokenAcceso"
                val v_creationDate = Date()
                val v_money : Int = 0
                val v_isBlocked : Boolean = false
                val v_userId : Int = usuarioLogeado?.id!!

                // Se crea la variabae que va a manejar la respuesta del servicio
                val v_llamadaApi : Call<CuentaContableResponse> = crearCuentaContable.crearCuentaContable(CuentaContableRequest(v_creationDate, v_money, v_isBlocked, v_userId),v_token) // Funcion desde Service

                // Respuesta de la api
                v_llamadaApi.enqueue(object : Callback<CuentaContableResponse>
                {
                    override fun onResponse (call : Call<CuentaContableResponse>, response : Response<CuentaContableResponse>)
                    {
                        // Obtengo la respuesta desde model LoginResponse
                        var respuesta: CuentaContableResponse? = response.body()

                        if (response.isSuccessful)
                        {
                            if (respuesta?.userId!= null)
                            {
                                cuentaContableCreadaLiveData.postValue(true)
                                // idCuentaContableLoginLivedata.postValue(respuesta.id)
                                errorLiveData.postValue(null)
                            }
                            else
                            {
                                cuentaContableCreadaLiveData.postValue(false)
                                // idCuentaContableLoginLivedata.postValue(null)
                                errorLiveData.postValue(respuesta?.error)
                            }
                        }
                        else
                        {
                            cuentaContableCreadaLiveData.postValue(false)
                            // idCuentaContableLoginLivedata.postValue(null)
                            errorLiveData.postValue(respuesta?.error)
                        }
                    }

                    override fun onFailure (call : Call<CuentaContableResponse>, t : Throwable )
                    {
                        cuentaContableCreadaLiveData.postValue(false)
                        // idCuentaContableLoginLivedata.postValue(null)
                        errorLiveData.postValue(null)
                    }
                })
            } catch (e: Exception)
            {
                cuentaContableCreadaLiveData.postValue(false)
                // idCuentaContableLoginLivedata.postValue(null)
                errorLiveData.postValue(null)
            }
        }
    }
    // FIN Realiza la creación de la cuenta contable si el usuario logueado no tiene

    /*
    // Obtengo los datos de la cuenta contable del usuario creado
    fun datosCuentaContableLogin(idCuentaContableLogin : Int)
    {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Aca llamaremos a la Api

                // Esta es la instancia de Retrofit
                var getDatosCuentacontableLogin = RetrofitInstancia.retrofit.create(CuentaContableService::class.java)

                // Se crea la variabae que va a manejar la respuesta del servicio
                val token = "Bearer $tokenAcceso"

                val usuarioLlamada : Call<CuentaContableResponse> = getDatosCuentacontableLogin.consultarCuentaContable(idCuentaContableLogin, token) // Funcion desde Service

                usuarioLlamada.enqueue(object : Callback<LoginUser>
                {
                    override fun onResponse(call : Call<LoginUser>, response : Response<LoginUser>)
                    {
                        var usuarioLogin = response.body()
                        if (response.isSuccessful)
                        {
                            usuarioLiveData.postValue(usuarioLogin)
                        }
                        else
                        {
                            usuarioLiveData.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<LoginUser>, t: Throwable)
                    {
                        usuarioLiveData.postValue(null)
                    }
                })

            } catch (e: Exception) {
                // Error
                // loginResultLiveData.postValue(false)
                usuarioLiveData.postValue(null)
            }
        }
    }
    // FIN Obtengo los datos de la cuenta contable del usuario creado
    */

}