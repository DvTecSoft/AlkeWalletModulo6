package vidal.daniel.alkewallet.model

/**
 * Clase que envía Transacción Cuenta a Api para actualización de saldo
*/
data class TransaccionCuentaRequest
(
     val type       : String
    ,val concept    : String
    ,val amount     : Int
)
