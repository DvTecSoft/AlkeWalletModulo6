package vidal.daniel.alkewallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.SplashScreenBinding
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Englobo pantalla
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Log.i("Ciclo de vida", "onCreate")
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
                // abrirPantallaLogin.putExtra("nombre", "Daniel")
                // abrirPantallaLogin.putExtra("apellido", "Vidal")
                // abrirPantallaLogin.putExtra("aceptotyc", true)
                startActivity(abrirPantallaLogin)
                finish()
            }
        }

        // Genera variable de tiempo
        val timer = Timer()
        timer.schedule(task, 2000)



    }


}