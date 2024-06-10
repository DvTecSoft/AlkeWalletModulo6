package vidal.daniel.alkewallet.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import vidal.daniel.alkewallet.AlkeWalletApp
import vidal.daniel.alkewallet.AlkeWalletApp.Companion.showMessageBox
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.RememberPasswordBinding
import vidal.daniel.alkewallet.databinding.RequestMoneyBinding
import java.time.LocalDateTime

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

            /*
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



*/











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