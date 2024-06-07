package vidal.daniel.alkewallet.model

import java.util.Date

data class CuentaContableResponse
(
     val id             : Int?
    ,val creationDate   : Date?
    ,val money          : Int?
    ,val isBlocked      : Boolean?
    ,val userId         : Int?
    ,val error          : String?
    ,val status         : Int?
)
