package vidal.daniel.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vidal.daniel.alkewallet.AlkeWalletApp
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.usuarioLogeado
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.HomeBinding
import vidal.daniel.alkewallet.model.CuentaContableResponse
import vidal.daniel.alkewallet.model.HomeModel
import vidal.daniel.alkewallet.model.TransaccionModel
import vidal.daniel.alkewallet.viewmodel.HomeViewModel
import java.util.Date


class HomeActivity : AppCompatActivity()
{
    // Habilito binding
    lateinit var binding : HomeBinding

    // Variable para viewModel
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

         // Obtengo datos desde objeto global con los datos del usuario
        binding.txtSaludo.text = "Hola " + usuarioLogeado?.first_name + " " + usuarioLogeado?.last_name + "!"

        // Ir a Perfil
        binding.imgPerfil.setOnClickListener {
            val winPerfil = Intent(this, ProfileActivity::class.java)
            startActivity(winPerfil)
        }

        //Se crea el adaptador con data vacia
        homeAdapter = HomeAdapter(emptyList()) // Inicialmente, la lista está vacía

        // Las transacciones realizados
        // Se configura el recyclerView
        recyclerView = findViewById(R.id.txList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = homeAdapter

        //Aca se configura el viewModel
        viewModel.loadTx()

        //Se configura el observador que va a estar observando al sujeto "userList"
        viewModel.homeTx.observe(this)
        {
            listTx ->
                Log.d("DvTec", "Transacciones obtenidas: $listTx")

                // Filtro TX para mostrar solo las que realicé a terceros
                //val objetoTx = parseJsonToTx(listTx.toString())
                //Log.d("DvTec", "Transacciones obtenidas toString: $objetoTx")

                //val idCuentaContableDestino = AlkeWalletApp.vg_idCuentaUsuarioLogueado!!
                //val txFiltradas  = filtraTx(objetoTx, idCuentaContableDestino)

                //Log.d("DvTec", "Transacciones filtradas toString: $txFiltradas")

                //val idCuentaContableEnvia = AlkeWalletApp.vg_idCuentaUsuarioLogueado!!

                //Log.d("DvTec", "IdCuentaEnvia: $idCuentaContableEnvia")

                // filtrarTxxId(idCuentaContableEnvia)

                //Recibi datos!!! actualizo
                homeAdapter = HomeAdapter(listTx)
                recyclerView.adapter = homeAdapter

        }
        // FIN Carga el saldo y los movimientos realizados

        // Obtengo saldo de la cuenta
        viewModel.obtenerMiCuentaContable()

        //Se configura el observador que va a estar observando al sujeto "miCuentaContableLiveData"
        viewModel.miCuentaContableLiveData.observe(this)
        {
            saldo ->
            //Recibi datos!!! actualizo
            binding.txtMontototal.text = "$ ${saldo?.firstOrNull()?.money}"
            AlkeWalletApp.vg_idCuentaUsuarioLogueado = saldo?.firstOrNull()?.id
        }
        // FIN Carga el saldo y los movimientos realizados

        // Ir a Enviar Dinero
        binding.btnEnviarDinero.setOnClickListener {
            val winSendMoney = Intent(this, SendMoneyActivity::class.java)
            startActivity(winSendMoney)
        }

        // Ir a Ingresar Dinero
        binding.btnIngresardinero.setOnClickListener {
            val winRequestMoney = Intent(this, RequestMoneyActivity::class.java)
            startActivity(winRequestMoney)
        }

        // Ir a Login -> Cerrar Sesión
        binding.btnCerrarsesion.setOnClickListener {
            val winCerrar = Intent(this, LoginActivity::class.java)
            startActivity(winCerrar)
            finish()
        }

        // Alerta
        binding.imgMensajes.setOnClickListener {
            // val winMensajes = Intent(this, LoginActivity::class.java)
            Toast.makeText(this, "No tienes mensajes nuevos", Toast.LENGTH_SHORT).show()
            // startActivity(winMensajes)
        }



    }

    /*
    private fun filtrarTxxId(vp_idEnvia: Int)
    {
        homeAdapter.filtrarTxbyId(vp_idEnvia)
    }
    */

}

