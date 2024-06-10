package vidal.daniel.alkewallet

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AlertDialog
import vidal.daniel.alkewallet.model.CuentaContableModel
import vidal.daniel.alkewallet.model.LoginUser

class AlkeWalletApp : Application()
{
    companion object
    {
        // Variables globales
        var usuarioLogeado              : LoginUser? = null
        var tokenAcceso                 : String? = ""
        var vg_idCuentaUsuarioLogueado  : Int? = 0

        /**
         * Función global para AlertDialog
         */
        fun Context.showMessageBox(title: String, message: String)
        {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    override fun onCreate()
    {
        super.onCreate()
        usuarioLogeado              = null
        tokenAcceso                 = ""
        vg_idCuentaUsuarioLogueado  = 0
    }


}
