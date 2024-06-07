package vidal.daniel.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.SignupPageBinding
import vidal.daniel.alkewallet.databinding.SplashScreenBinding
import vidal.daniel.alkewallet.viewmodel.LoginViewModel
import vidal.daniel.alkewallet.viewmodel.SignupViewModel

class SignupPageActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : SignupPageBinding

    // Variable para viewModel de Login
    lateinit var viewModel : SignupViewModel

    // habilito SharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    // Variables
    var v_nombre   : String = ""
    var v_apellido : String = ""
    var v_email    : String = ""
    var v_imgperfil: String = ""
    var v_password : String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding = SignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Vamos a implementar los SharedPreferences
        sharedPreferences = getSharedPreferences("AlkeWalet", Context.MODE_PRIVATE)

        // Configuramos el ViewModel
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        // Crear cuenta
        binding.btnCrearcuenta.setOnClickListener {

            // Rescato valores ingresados
            v_nombre    = binding.txtNombre.text.toString()
            v_apellido  = binding.txtApellido.text.toString()
            v_email     = binding.txtEmail.text.toString()
            v_password  = binding.txtClave.text.toString()

            viewModel.crearUsuario(v_nombre, v_apellido, v_email, v_password, 1, null)
        }

        // Observa respuesta de la creación
        viewModel.creadoLiveData.observe(this) {
            // loginOK es la variable que tendrá el resultado
            creadoOK ->
            if (creadoOK == true)
            {
                /*
                // Guardo datos en sharedPreferences para primer ingreso
                val editor = sharedPreferences.edit()
                editor.putString("nombre", v_nombre)
                editor.putString("apellido", v_apellido)
                editor.putString("email", v_email)
                editor.putString("clave", v_password)

                // editor.putBoolean("recuerdame", true)
                editor.apply()
                */

                // Mensaje Creación OK
                Toast.makeText(this,"Usuario creado correctamente", Toast.LENGTH_SHORT).show()

                // Va a Login
                val winCrear = Intent(this, LoginActivity::class.java)
                startActivity(winCrear)
                finish()
            }
            else
            {
                viewModel.errorLiveData.observe(this)
                {
                    error_vm ->
                    Toast.makeText(
                        this,
                        "No es posible crear el usuario. Error: $error_vm",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        // FIN Crear cuenta


        binding.txtTienecuenta.setOnClickListener {
            val winLogin = Intent(this, LoginActivity::class.java)
            startActivity(winLogin)
        }

    }
}