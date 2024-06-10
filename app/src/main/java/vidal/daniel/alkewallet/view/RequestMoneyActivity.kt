package vidal.daniel.alkewallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import vidal.daniel.alkewallet.AlkeWalletApp
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.showMessageBox
import vidal.daniel.alkewallet.databinding.RequestMoneyBinding
import vidal.daniel.alkewallet.viewmodel.RequestMoneyViewModel
import vidal.daniel.alkewallet.viewmodel.SendMoneyViewModel

class RequestMoneyActivity : AppCompatActivity()
{
    // variable para Binding
    lateinit var binding : RequestMoneyBinding

    // Variable para viewModel
    // lateinit var viewModel : RequestMoneyViewModel
    lateinit var viewModel : RequestMoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  RequestMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuramos el ViewModel
        viewModel = ViewModelProvider(this).get(RequestMoneyViewModel::class.java)

        // Ir a Home
        binding.imgAtras.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Ingresar dinero y volver al home
        binding.btnIngresardinero.setOnClickListener {

            Log.d("DvTec", "Clic btnIngresardinero")

            // Rescato valores ingresados
            val numeroCuentaDestino = AlkeWalletApp.vg_idCuentaUsuarioLogueado
            val montoStr            = binding.editMonto.text.toString()
            val nota                = binding.editNota.text.toString()

            Log.d("DvTec", "Clic btnIngresardinero-> Asigna variables")

            // Valida información ingresada
            if (montoStr.isEmpty())
            {
                //Toast.makeText(this, "Debe ingresar el número de cuenta destino y la cantidad. Valide y reintente", Toast.LENGTH_SHORT).show()
                showMessageBox("Error", "Debe ingresar la cantidad. Valide y reintente.")
                return@setOnClickListener // No continuar si el campo está vacío
            }

            Log.d("DvTec", "Clic btnIngresardinero-> Convierte valores")
            // Convierto valores
            val montoInt            = montoStr.toInt()
            val v_idUsuario         = AlkeWalletApp.usuarioLogeado?.id


            Log.d("DvTec", "Clic btnIngresardinero-> Si está bien, llama a viewmodel")
            // Genera la transacción en endpoint Transaction para el usuario que envía
            if (v_idUsuario != null && numeroCuentaDestino != null)
            {
                Log.d("DvTec", "Clic btnIngresardinero-> v_idUsuario != null && numeroCuentaDestino != null")
                viewModel.generaDeposito(montoInt, nota, numeroCuentaDestino)
            }

            //Se configura el observador que va a estar observando al sujeto "depositoGeneradoLiveData"
            viewModel.depositoGeneradoLiveData.observe(this)
            {
                transaccionCreada ->
                //Recibi datos!!! actualizo

                val results = transaccionCreada?.split(" - ")
                val result1 = results?.get(0)
                val result2 = results?.get(1)
                val result3 = results?.get(2)
                val result4 = results?.get(3)

                Log.d("DvTec", "Resultado Retorno 1 $result1")
                Log.d("DvTec", "Resultado Retorno 2 $result2")
                Log.d("DvTec", "Resultado Retorno 3 $result3")
                Log.d("DvTec", "Resultado Retorno 4 $result4")

                // Valido que se haya realizado la transacción correctamente


                // Volver a Home
                // Mensaje Transferencia realizada
                Toast.makeText(this, "Ingreso de dinero realizado correctamente!.", Toast.LENGTH_LONG).show()
                val abrirPantalla = Intent(this, HomeActivity::class.java)
                startActivity(abrirPantalla)
                finish()

                // Log.d("DvTec-HomeActivity", "Transacciones obtenidas: $listTx")
            }
            // FIN Se configura el observador que va a estar observando al sujeto "depositoGeneradoLiveData"
        }

    }
}