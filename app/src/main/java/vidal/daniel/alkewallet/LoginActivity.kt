package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Ir a HOME
        val login = findViewById<Button>(R.id.btn_login)
        login.setOnClickListener {
            val abrirPantallaLogin = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantallaLogin)
            finish()
        }

        // Ir a Crear nueva cuenta
        val crearcuenta = findViewById<TextView>(R.id.txt_crearcuenta)
        crearcuenta.setOnClickListener {
            val abrirPantalla = Intent(this, SignupPageActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Ir a Olvidaste tu contraseña
        val olvidaste = findViewById<TextView>(R.id.txt_olvidaste)
        olvidaste.setOnClickListener {
            val abrirPantalla = Intent(this, RememberPasswordActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

    }
}