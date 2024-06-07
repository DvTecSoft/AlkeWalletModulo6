package vidal.daniel.alkewallet.model

import java.util.Date

data class HomeModel
(
     val amount         : Int
    ,val concept        : String
    ,val date           : Date
    ,val type           : String
    ,val accountId      : Int = 1
    ,val userId         : Int = 4
    ,val to_account_id  : Int = 5
    ,val url_imagen     : String?
)