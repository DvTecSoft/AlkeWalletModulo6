package vidal.daniel.alkewallet

import android.app.Application
import vidal.daniel.alkewallet.model.CuentaContableModel
import vidal.daniel.alkewallet.model.LoginUser

class AlkeWalletApp : Application()
{
    companion object
    {
        // Objeco usuario global al proyecto
        var usuarioLogeado              : LoginUser? = null
        var tokenAcceso                 : String? = ""
        var vg_idCuentaUsuarioLogueado  : Int? = null
    }

    override fun onCreate()
    {
        super.onCreate()
        usuarioLogeado              = null
        tokenAcceso                 = ""
        vg_idCuentaUsuarioLogueado  = 0

    }
}
