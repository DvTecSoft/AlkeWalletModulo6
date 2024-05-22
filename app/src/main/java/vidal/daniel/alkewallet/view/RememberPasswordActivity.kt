package vidal.daniel.alkewallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.ProfileBinding
import vidal.daniel.alkewallet.databinding.RememberPasswordBinding

class RememberPasswordActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : RememberPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  RememberPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Ir a Home
        binding.btnHome.setOnClickListener {
            val abrirPantalla = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantalla)
        }
        /*
        val home = findViewById<Button>(R.id.btn_home)
        home.setOnClickListener {
            val abrirPantalla = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }
        */

    }
}