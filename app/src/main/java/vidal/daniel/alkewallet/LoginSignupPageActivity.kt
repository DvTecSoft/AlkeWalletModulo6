package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginSignupPageActivity : AppCompatActivity()
{
    // override fun onBackPressed(){}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_signup_page)

        /*
        // rescatando valores de pantalla anterior
        val nombre      = intent.getStringExtra("nombre")
        val apellido    = intent.getStringExtra("apellido")
        val tyc         = intent.getBooleanExtra("aceptotyc",false)

        // Mostrar el dato en un toast
        //Toast.makeText(this, "Hola $nombre $apellido -- Aceptó los TyC $tyc",Toast.LENGTH_LONG).show()
        */

        // Ir a Login
        val tienecuenta = findViewById<TextView>(R.id.txt_tienecuenta)
        tienecuenta.setOnClickListener {
            val abrirPantallaLogin = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantallaLogin)
            finish()
        }

        // Ir a SignupPage
        val crearcuenta = findViewById<Button>(R.id.btn_crearcuenta)
        crearcuenta.setOnClickListener {
            val abrirPantallaLogin = Intent(this, SignupPageActivity::class.java)
            startActivity(abrirPantallaLogin)
            finish()
        }

    }


}