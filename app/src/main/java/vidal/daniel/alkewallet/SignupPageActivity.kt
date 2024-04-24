package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SignupPageActivity : AppCompatActivity()
{
    // override fun onBackPressed(){}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        // Crear la cuenta
        // Vuelve al HOME
        val home = findViewById<Button>(R.id.btn_crearcuenta)
        home.setOnClickListener {
            val abrirPantalla = Intent(this, LoginActivity::class.java)
            // Mensaje OK
            Toast.makeText(this, "Hola, Cuenta Creada.",Toast.LENGTH_LONG).show()
            startActivity(abrirPantalla)
            finish()
        }

        // Ir a Login
        val login = findViewById<TextView>(R.id.txt_tienecuenta)
        login.setOnClickListener {
            val abrirPantalla = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

    }
}