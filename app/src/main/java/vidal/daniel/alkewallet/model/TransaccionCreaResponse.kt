package vidal.daniel.alkewallet.model

import java.util.Date

data class TransaccionCreaResponse
(
     val id : Int?
    ,val amount: Int?
    ,val concept: String?
    ,val date: Date?
    ,val type: String?
    ,val accountId: Int?
    ,val userId: Int?
    ,val to_account_id: Int?
    ,val error : String?
    ,val status : Int?
)
