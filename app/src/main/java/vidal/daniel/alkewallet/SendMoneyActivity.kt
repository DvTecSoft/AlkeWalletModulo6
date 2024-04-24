package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class SendMoneyActivity : AppCompatActivity()
{
    // override fun onBackPressed(){}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_money)

        // Ir a Home
        val home = findViewById<ImageView>(R.id.img_atras)
        home.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }


        // Enviar transferencia y volver al home
        val enviar = findViewById<Button>(R.id.btn_enviardinero)
        enviar.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            // Mensaje Transferencia realizada
            Toast.makeText(this, "Envío de dinero realizado!.", Toast.LENGTH_LONG).show()
            startActivity(abrirPantalla)
            finish()
        }


    }
}