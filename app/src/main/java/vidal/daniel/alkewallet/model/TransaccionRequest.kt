package vidal.daniel.alkewallet.model

import java.time.LocalDateTime
import java.util.Date

/**
 * Clase que envía transacción a Api
 */
data class TransaccionRequest(
       val amount: Int
     , val concept: String    // "Pago de honorarios"
     , val date: String      // "2022-10-26 15:00:00"
     , val type: String    // "topup|payment"
     , val accountId: Int
     , val userId: Int
     , val to_account_id: Int
)
