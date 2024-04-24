package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class HomeActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // Ir a Perfil
        val perfil = findViewById<ImageView>(R.id.img_perfil)
        perfil.setOnClickListener {
            val abrirPantalla = Intent(this, ProfileActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Ir a Enviar Dinero
        val enviar = findViewById<Button>(R.id.btn_enviardinero)
        enviar.setOnClickListener {
            val abrirPantalla = Intent(this, SendMoneyActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Ir a Ingresar Dinero
        val ingresar = findViewById<Button>(R.id.btn_ingresardinero)
        ingresar.setOnClickListener {
            val abrirPantalla = Intent(this, RequestMoneyActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Ir a Login -> Cerrar Sesión
        val login = findViewById<Button>(R.id.btn_cerrarsesion)
        login.setOnClickListener {
            val abrirPantalla = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Alerta
        val alerta = findViewById<ImageView>(R.id.img_mensajes)
        alerta.setOnClickListener {
            Toast.makeText(this, "No tienes mensajes nuevos", Toast.LENGTH_SHORT).show()
        }

    }
}