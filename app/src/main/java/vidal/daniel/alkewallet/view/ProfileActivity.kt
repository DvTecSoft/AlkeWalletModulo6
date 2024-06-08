package vidal.daniel.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.usuarioLogeado
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.LoginSignupPageBinding
import vidal.daniel.alkewallet.databinding.ProfileBinding

class ProfileActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : ProfileBinding

    // habilito SharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  ProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Vamos a implementar los SharedPreferences
        sharedPreferences = getSharedPreferences("AlkeWalet", Context.MODE_PRIVATE)

        // Implemento los SharedPreferences
        //val nombreUsuario = sharedPreferences.getString("nombre", "") + " " + sharedPreferences.getString("apellido", "")
        //val imgPerfil = sharedPreferences.getString("imgperfil", "")
        // Asigno valor en textview
        // binding.txtNombreusuriosend.text = nombreUsuario
        // Asigno imagen de perfil dinámicamente
        // val imageName = imgPerfil
        // Obtengo el ID de la imagen
        // val resourceId = resources.getIdentifier(imageName, "drawable", packageName)
        // Establecer la imagen de fondo del ImageView
        // binding.imgFotousuario.setImageResource(resourceId)

        // Obtengo datos desde objeto global con los datos del usuario
        var nombreUsuario = usuarioLogeado?.first_name + " " + usuarioLogeado?.last_name
        binding.editNombreUsuario.text = nombreUsuario

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

    }
}