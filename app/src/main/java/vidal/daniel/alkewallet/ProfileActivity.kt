package vidal.daniel.alkewallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ProfileActivity : AppCompatActivity()
{
    // override fun onBackPressed(){}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Ir a Home
        val home = findViewById<ImageView>(R.id.img_atras)
        home.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

    }
}