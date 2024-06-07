package vidal.daniel.alkewallet.model

import java.util.Date

data class TransaccionResponse
(
    /*
     val amount         : Int?
    ,val concept        : String?
    ,val date           : Date?
    ,val type           : String?
    ,val accountId      : Int?
    ,val userId         : Int?
    ,val to_account_id  : Int?
    ,val error          : String?
    ,val status         : Int?
    */
     val previousPage: Int?,
     val nextPage: Int?,
     val data: List<TransaccionModel>?,
     val error: String?,
     val status: String?

)
