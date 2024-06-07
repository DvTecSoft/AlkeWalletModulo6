package vidal.daniel.alkewallet.model

import java.util.Date

/**
 * Clase que contiene las transacciones del usuario logeado
 */
data class CuentaContableModel
(
     val id             : Int
    ,val creationDate   : Date
    ,val money          : Int
    ,val isBlocked      : Boolean
    ,val userId         : Int

)
