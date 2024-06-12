package vidal.daniel.alkewallet.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import vidal.daniel.alkewallet.AlkeWalletApp
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.showMessageBox
import vidal.daniel.alkewallet.databinding.SendMoneyBinding
import vidal.daniel.alkewallet.viewmodel.SendMoneyViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class SendMoneyActivity : AppCompatActivity()
{
    // Variable para Binding
    lateinit var binding : SendMoneyBinding

    // Variable para viewModel
    lateinit var viewModel : SendMoneyViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Englobo pantalla
        binding =  SendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuramos el ViewModel
        viewModel = ViewModelProvider(this).get(SendMoneyViewModel::class.java)

        // Ir a Home
        binding.imgAtras.setOnClickListener {
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
            finish()
        }

        // Al abrir, posiciono el foco en el edit
        binding.editNumeroCuenta.requestFocus()

        // Enviar transferencia y volver al home
        binding.btnEnviarDinero.setOnClickListener {

            // Rescato valores ingresados
            val numeroCuentaStr = binding.editNumeroCuenta.text.toString()
            val montoStr        = binding.editMonto.text.toString()
            val nota            = binding.editNota.text.toString()

            // Valida información ingresada
            if (numeroCuentaStr.isEmpty() || montoStr.isEmpty())
            {
                //Toast.makeText(this, "Debe ingresar el número de cuenta destino y la cantidad. Valide y reintente", Toast.LENGTH_SHORT).show()
                showMessageBox("Error", "Debe ingresar el número de cuenta destino y la cantidad. Valide y reintente.")
                return@setOnClickListener // No continuar si el campo está vacío
            }

            // Convierto valores
            val numeroCuentaInt     = numeroCuentaStr.toInt()
            val montoInt            = montoStr.toInt()
            val localFecha          = LocalDateTime.now()
            //val fecha               = convertToLocalDateViaInstant(localFecha)
            val v_idCuentaEnvia     = AlkeWalletApp.vg_idCuentaUsuarioLogueado
            val v_idUsuario         = AlkeWalletApp.usuarioLogeado?.id

            // Genera la transacción en endpoint Transaction para el usuario que envía
            if (v_idUsuario != null && v_idCuentaEnvia != null)
            {
                viewModel.generaTransaccion(montoInt, nota, localFecha, "payment", v_idCuentaEnvia, v_idUsuario, numeroCuentaInt)

                // val firstResult = repository.fetchFirstData()
                // val secondResult = repository.fetchSecondData()

                // val transaccionCreaMe      = viewModel.generaTransaccion(montoInt, nota, fecha, "payment", v_idCuentaEnvia, v_idUsuario, v_idCuentaEnvia, "-")
                // val transaccionCreaTercero = viewModel.generaTransaccion(montoInt, nota, fecha, "payment", v_idCuentaEnvia, v_idUsuario, numeroCuentaInt, "+")
            }

            // Genera la transacción en endpoint Account para el usuario que envía para la actualización de saldo
            // Genera la transacción en endpoint Account para el usuario que recibe para la actualización de saldo

            // Volver a Home
            // Mensaje Transferencia realizada
            //Toast.makeText(this, "Envío de dinero realizado!.", Toast.LENGTH_LONG).show()
            //val abrirPantalla = Intent(this, HomeActivity::class.java)
            //startActivity(abrirPantalla)
        }

        //Se configura el observador que va a estar observando al sujeto "userList"
        viewModel.transaccionGeneradaLiveData.observe(this)
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

            // homeAdapter = HomeAdapter(listTx)
            // recyclerView.adapter = homeAdapter
            // Volver a Home
            // Mensaje Transferencia realizada
            Toast.makeText(this, "Envío de dinero realizado correctamente!.", Toast.LENGTH_LONG).show()
            val abrirPantalla = Intent(this, HomeActivity::class.java)
            startActivity(abrirPantalla)
            finish()

            // Log.d("DvTec-HomeActivity", "Transacciones obtenidas: $listTx")
        }
        // FIN Carga el saldo y los movimientos realizados



    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToLocalDateViaInstant(dateToConvert: LocalDate): Date {
        return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
    }


}


