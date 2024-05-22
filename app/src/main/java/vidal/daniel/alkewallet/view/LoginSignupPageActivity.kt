package vidal.daniel.alkewallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.HomeBinding
import vidal.daniel.alkewallet.databinding.LoginBinding
import vidal.daniel.alkewallet.databinding.LoginSignupPageBinding

class LoginSignupPageActivity : AppCompatActivity()
{

    // Habilito binding
    lateinit var binding : LoginSignupPageBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  LoginSignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        // rescatando valores de pantalla anterior
        val nombre      = intent.getStringExtra("nombre")
        val apellido    = intent.getStringExtra("apellido")
        val tyc         = intent.getBooleanExtra("aceptotyc",false)

        // Mostrar el dato en un toast
        //Toast.makeText(this, "Hola $nombre $apellido -- Aceptó los TyC $tyc",Toast.LENGTH_LONG).show()
        */

        // Ir a Login
        binding.txtTienecuenta.setOnClickListener {
            val abrirPantalla = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantalla)
        }
        /*
        val tienecuenta = findViewById<TextView>(R.id.txt_tienecuenta)
        tienecuenta.setOnClickListener {
            val abrirPantallaLogin = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantallaLogin)
            finish()
        }
        */

        // Ir a SignupPage
        binding.btnCrearcuenta.setOnClickListener {
            val abrirPantalla = Intent(this, SignupPageActivity::class.java)
            startActivity(abrirPantalla)
        }
        /*
        val crearcuenta = findViewById<Button>(R.id.btn_crearcuenta)
        crearcuenta.setOnClickListener {
            val abrirPantallaLogin = Intent(this, SignupPageActivity::class.java)
            startActivity(abrirPantallaLogin)
            finish()
        }
        */

    }


}