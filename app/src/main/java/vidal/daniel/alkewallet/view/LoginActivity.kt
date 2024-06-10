package vidal.daniel.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vidal.daniel.alkewallet.AlkeWalletApp
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.tokenAcceso
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.usuarioLogeado
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.vg_idCuentaUsuarioLogueado
import vidal.daniel.alkewallet.databinding.LoginBinding
import vidal.daniel.alkewallet.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : LoginBinding

    // Variable para viewModel de Login
    lateinit var viewModel : LoginViewModel

    // habilito SharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    // Variables
    var v_correo    : String = ""
    var v_clave     : String = ""
    var v_imgperfil : String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Vamos a implementar los SharedPreferences
        sharedPreferences = getSharedPreferences("AlkeWalet", Context.MODE_PRIVATE)

        // Configuramos el ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Login
        binding.btnLogin.setOnClickListener {
            
            // Rescato valores ingresados 
            v_correo    = binding.txtEmail.text.toString()
            v_clave     = binding.txtClave.text.toString()

            // Llamar a la api para logear al usuario en ViewModel
            viewModel.hacerLogin(v_correo, v_clave)
        }
        // FIN Login

        // Observa respuesta del token del usuario logueado
        viewModel.tokenLiveData.observe(this)
        {
            // loginOK es la variable que tendrá el resultado
            token ->
            if (token != null)
            {
                // Guardo token en variable global de la app
                tokenAcceso = token

                // Llamar a la api para obtener los datos del usuario en ViewModel
                viewModel.datosLogin()
            }
        }
        // FIN Observa respuesta del token del usuario logueado

        // Observa respuesta de los datos del usuario logueado
        viewModel.usuarioLiveData.observe(this)
        {
            // Usuario con datos
            usuario ->
            if (usuario != null)
            {
                // Actualizo objeto UsuarioLogueado variable global
                usuarioLogeado = usuario

                // Valido si el usuario no tiene cuenta, la crea automáticamente
                // Llamar a la api para revisar no tiene cuenta en ViewModel
                viewModel.validaCuentaContable()

                /*
                // Habilito la pantalla
                val abrirPantalla = Intent(this, HomeActivity::class.java)

                // Agrego parámetros a pantalla
                //abrirPantalla.putExtra("nombre", first_name.toString())
                //abrirPantalla.putExtra("apellido", last_name.toString())

                // Carga pantalla
                startActivity(abrirPantalla)
                */

            }
        }
        // FIN Observa respuesta de los datos del usuario logueado

        // Observa el MediatorLiveData
        viewModel.combinedData.observe(this, Observer { combined ->
            val data1 = combined.first
            val data2 = combined.second
            // Actualiza la UI con data1 y data2
        })

        viewModel.combinedData.observe(this, Observer {
            combined ->
                val sincuentacontable = combined.first
                val datosCuentaContableLogin = combined.second

                if (sincuentacontable == true) // No tiene cuenta
                {
                    // Llamar a la api para obtener los datos del usuario en ViewModel
                    viewModel.crearCuentaContable()
                }
                else
                {
                    // Obtentengo el id de la cuenta contable del usuario logeado

                    vg_idCuentaUsuarioLogueado = datosCuentaContableLogin?.get(0)?.id
                    // Log.d("DvTec", {AlkeWalletApp.vg_idUsuarioLogueado}.toString())

                    // Entra a Home
                    // Habilito la pantalla
                    val abrirPantalla = Intent(this, HomeActivity::class.java)
                    // Carga pantalla
                    startActivity(abrirPantalla)
                    finish()

                }

        })

        // Observa respuesta de la validación de la cuenta contable
        viewModel.cuentaContableCreadaLiveData.observe(this)
        {
            // loginOK es la variable que tendrá el resultado
                ctacreadaOK ->

                // Entra a Home
                // Habilito la pantalla
                val abrirPantalla = Intent(this, HomeActivity::class.java)
                // Carga pantalla
                startActivity(abrirPantalla)
                finish()
        }
        // FIN Observa respuesta de la validación de la cuenta contable

        // Ir a Crear nueva cuenta
        binding.txtCrearcuenta.setOnClickListener {
            val abrirPantalla = Intent(this, SignupPageActivity::class.java)
            startActivity(abrirPantalla)
        }

        // Ir a Olvidaste tu contraseña
        binding.txtOlvidaste.setOnClickListener {
            val abrirPantalla = Intent(this, RememberPasswordActivity::class.java)
            startActivity(abrirPantalla)
        }


    }
    // FIN OnCreate


}