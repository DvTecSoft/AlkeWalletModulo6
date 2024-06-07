package vidal.daniel.alkewallet.model

import java.util.Date

/**
 *  Api Service que contiene los métodos para de la cuenta contable
 */
data class TransaccionModel
(
     val id             : Int
    ,val amount         : Int
    ,val concept        : String    // "Pago de honorarios"
    ,val date           : Date      // "2022-10-26 15:00:00"
    ,val type           : String    // "topup|payment"
    ,val accountId      : Int
    ,val userId         : Int
    ,val to_account_id  : Int
)
