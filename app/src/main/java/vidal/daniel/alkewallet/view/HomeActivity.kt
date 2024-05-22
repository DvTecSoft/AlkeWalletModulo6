package vidal.daniel.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.HomeBinding
import vidal.daniel.alkewallet.databinding.SplashScreenBinding
import vidal.daniel.alkewallet.viewmodel.HomeViewModel


class HomeActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : HomeBinding

    // Variable para viewModel de Login
    lateinit var viewModel : HomeViewModel

    // habilito SharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    // Declaro variable para reciclerview
    private lateinit var recyclerView: RecyclerView

    // Declaro variable para adapter
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  HomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Implemento los SharedPreferences
        sharedPreferences = getSharedPreferences("AlkeWalet", Context.MODE_PRIVATE)

        // Configuro el ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Creo el adaptador con data vacia
        homeAdapter = HomeAdapter(emptyList()) // Inicialmente, la lista está vacía

        // Implemento los SharedPreferences
        val nombreUsuario = sharedPreferences.getString("nombre", "") + " " + sharedPreferences.getString("apellido", "")
        val imgPerfil = sharedPreferences.getString("imgperfil", "")
        var correo = sharedPreferences.getString("“usuario", "") // Rescato el usuario logueado

        // Asigno valor en textview
        binding.txtSaludo.text = " Hola " + nombreUsuario + "!"

        // Asigno imagen de perfil dinámicamente
        val imageName = imgPerfil

        // Obtengo el ID de la imagen
        val resourceId = resources.getIdentifier(imageName, "drawable", packageName)

        // Establecer la imagen de fondo del ImageView
        binding.imgPerfil.setImageResource(resourceId)

        // Ir a Perfil
        binding.imgPerfil.setOnClickListener {
            val winPerfil = Intent(this, ProfileActivity::class.java)
            startActivity(winPerfil)
        }
        /*
        val perfil = findViewById<ImageView>(R.id.img_perfil)
        perfil.setOnClickListener {
            val abrirPantalla = Intent(this, ProfileActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }
        */

        // Carga el saldo y los movimientos realizados
        //
        //Aca se configura el viewModel
        viewModel.loadTx()

        //Se crea el adaptador con data vacia
        homeAdapter = HomeAdapter(emptyList()) // Inicialmente, la lista está vacía

        //Se configura el recyclerView
        recyclerView = findViewById(R.id.txList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = homeAdapter


        //Se configura el observador que va a estar observando al sujeto "userList"
        viewModel.homeTx.observe(
            this
        ) { listTx ->
            //Recibi datos!!! actualizo
            homeAdapter = HomeAdapter(listTx)
            recyclerView.adapter = homeAdapter
        }
        // FIN Carga el saldo y los movimientos realizados


        // Ir a Enviar Dinero
        binding.btnEnviardinero.setOnClickListener {
            val winSendMoney = Intent(this, SendMoneyActivity::class.java)
            startActivity(winSendMoney)
        }
        /*
        val enviar = findViewById<Button>(R.id.btn_enviardinero)
        enviar.setOnClickListener {
            val abrirPantalla = Intent(this, SendMoneyActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }
        */

        // Ir a Ingresar Dinero
        binding.btnIngresardinero.setOnClickListener {
            val winRequestMoney = Intent(this, RequestMoneyActivity::class.java)
            startActivity(winRequestMoney)
        }
        /*
        val ingresar = findViewById<Button>(R.id.btn_ingresardinero)
        ingresar.setOnClickListener {
            val abrirPantalla = Intent(this, RequestMoneyActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }
        */

        // Ir a Login -> Cerrar Sesión
        binding.btnCerrarsesion.setOnClickListener {
            val winCerrar = Intent(this, LoginActivity::class.java)
            startActivity(winCerrar)
        }
        /*
        val login = findViewById<Button>(R.id.btn_cerrarsesion)
        login.setOnClickListener {
            val abrirPantalla = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }
        */

        // Alerta
        binding.imgMensajes.setOnClickListener {
            // val winMensajes = Intent(this, LoginActivity::class.java)
            Toast.makeText(this, "No tienes mensajes nuevos", Toast.LENGTH_SHORT).show()
            // startActivity(winMensajes)
        }
        /*
        val alerta = findViewById<ImageView>(R.id.img_mensajes)
        alerta.setOnClickListener {
            Toast.makeText(this, "No tienes mensajes nuevos", Toast.LENGTH_SHORT).show()
        }
        */

    }
}