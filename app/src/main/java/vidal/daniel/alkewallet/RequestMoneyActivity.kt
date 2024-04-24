package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class RequestMoneyActivity : AppCompatActivity()
{
    // override fun onBackPressed(){}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.request_money)

        // Ir a Home
        val home = findViewById<ImageView>(R.id.img_atras)
        home.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Ingresar dinero y volver al home
        val ingresar = findViewById<Button>(R.id.btn_ingresardinero)
        ingresar.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            // Mensaje Ingreso realizado
            Toast.makeText(this, "Ingreso de dinero realizado!.", Toast.LENGTH_LONG).show()
            startActivity(abrirPantalla)
            finish()
        }



    }
}