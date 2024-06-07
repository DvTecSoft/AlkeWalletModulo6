package vidal.daniel.alkewallet.model

import java.util.Date

data class CuentaContableRequest
(
      val creationDate  : Date
     ,val money         : Int
     ,val isBlocked     : Boolean
     ,val userId        : Int
)
