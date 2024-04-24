package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity()
{
    // override fun onBackPressed(){}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        /*
        // Abrir pantalla con clic
        // Se declara la imagen cómo una variable
        val logoApp = findViewById<ImageView>(R.id.img_logoapp)
        logoApp.setOnClickListener {
            val abrirPantallaLogin = Intent(this, login_signup_page::class.java)
            abrirPantallaLogin.putExtra("nombre", "Jaime")
            abrirPantallaLogin.putExtra("apellido", "Perez")
            abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaLogin)
        }
        */

         // Abiendo la pantalla con un timer
        var task: TimerTask? = object : TimerTask()
        {
            override fun run()
            {
                val abrirPantallaLogin = Intent(baseContext, LoginSignupPageActivity::class.java)
                abrirPantallaLogin.putExtra("nombre", "Daniel")
                abrirPantallaLogin.putExtra("apellido", "Vidal")
                abrirPantallaLogin.putExtra("aceptotyc", true)
                startActivity(abrirPantallaLogin)
                finish()
            }
        }

        val timer = Timer()
        timer.schedule(task, 2000)

    }
}