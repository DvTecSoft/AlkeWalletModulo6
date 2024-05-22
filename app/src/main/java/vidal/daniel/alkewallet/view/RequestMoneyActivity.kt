package vidal.daniel.alkewallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.RememberPasswordBinding
import vidal.daniel.alkewallet.databinding.RequestMoneyBinding

class RequestMoneyActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : RequestMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  RequestMoneyBinding.inflate(layoutInflater)
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

        // Ingresar dinero y volver al home
        binding.btnIngresardinero.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)

            // Mensaje Ingreso realizado
            Toast.makeText(this, "Ingreso de dinero realizado!.", Toast.LENGTH_LONG).show()

            startActivity(abrirPantalla)
        }
        /*
        val ingresar = findViewById<Button>(R.id.btn_ingresardinero)
        ingresar.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            // Mensaje Ingreso realizado
            Toast.makeText(this, "Ingreso de dinero realizado!.", Toast.LENGTH_LONG).show()
            startActivity(abrirPantalla)
            finish()
        }
        */



    }
}