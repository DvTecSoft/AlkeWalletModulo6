package vidal.daniel.alkewallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.RequestMoneyBinding
import vidal.daniel.alkewallet.databinding.SendMoneyBinding

class SendMoneyActivity : AppCompatActivity()
{

    // Habilito binding
    lateinit var binding : SendMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  SendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ir a Home
        binding.imgAtras.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
        }
        /*
        val home = findViewById<ImageView>(R.id.img_atras)
        home.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }
        */

        // Enviar transferencia y volver al home
        binding.btnEnviardinero.setOnClickListener {

            // Mensaje Transferencia realizada
            Toast.makeText(this, "Envío de dinero realizado!.", Toast.LENGTH_LONG).show()

            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
        }
        /*
        val enviar = findViewById<Button>(R.id.btn_enviardinero)
        enviar.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            // Mensaje Transferencia realizada
            Toast.makeText(this, "Envío de dinero realizado!.", Toast.LENGTH_LONG).show()
            startActivity(abrirPantalla)
            finish()
        }
        */

    }
}