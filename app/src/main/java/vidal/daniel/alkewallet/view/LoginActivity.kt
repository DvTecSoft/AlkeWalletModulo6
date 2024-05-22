package vidal.daniel.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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

            viewModel.hacerLogin(v_correo, v_clave)
        }

        viewModel.loginResultLiveData.observe(this){
            // loginOK es la variable que tendrá el resultado
                loginOK ->
            if(loginOK == true)
            {
                // Rescato valores ingresados
                v_correo    = binding.txtEmail.text.toString()

                // Obtengo datos del usuario logueado
                val instancia = LoginViewModel()
                val funcionRetornada = instancia.datosLogin(v_correo)

                // Rescato valores
                val (vm_nombre, vm_apellido, vm_imgperfil) = funcionRetornada(v_correo)
                var first_name : String? = vm_nombre
                var last_name  : String? = vm_apellido
                var img_perfil : String? = vm_imgperfil

                // Habilito la pantalla
                val abrirPantalla = Intent(this, HomeActivity::class.java)

                // Guardo datos en sharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("nombre", first_name)
                editor.putString("apellido", last_name)
                editor.putString("imgperfil", img_perfil)
                editor.putString("usuario", v_correo)
                // editor.putBoolean("recuerdame", true)
                editor.apply()

                // Agrego parámetros a pantalla
                //abrirPantalla.putExtra("nombre", first_name.toString())
                //abrirPantalla.putExtra("apellido", last_name.toString())

                // Habilito la pantalla
                // val abrirPantalla = Intent(this, HomeActivity::class.java)
                // Carga pantalla
                startActivity(abrirPantalla)
            }
            else
            {
                // Pregunto si es un usuario recien creado, obtengo usuario y clave
                var nombreUsuarioShared = sharedPreferences.getString("email", "")
                var claveUsuarioShared  = sharedPreferences.getString("clave", "")

                // Rescato valores ingresados
                v_correo    = binding.txtEmail.text.toString()
                v_clave     = binding.txtClave.text.toString()
                v_imgperfil = "mi_informacion_icon"

                if (v_correo == nombreUsuarioShared && v_clave == claveUsuarioShared)
                {
                    // Guardo datos en sharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("imgperfil", v_imgperfil)
                    editor.apply()

                    val winCrear = Intent(this, HomeActivity::class.java)
                    startActivity(winCrear)
                }
                else
                {
                    Toast.makeText(this, "El usuario/clave incorrecto. Valide y rentente.", Toast.LENGTH_SHORT).show()
                }



            }
        }
        // FIN Login





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


}