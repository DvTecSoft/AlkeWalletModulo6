package vidal.daniel.alkewallet.model

data class LoginUser(
     val first_name : String?
    ,val last_name  : String?
    ,val email      : String?
    ,val password   : String?
    ,val roleID     : Long = 2
    ,val points     : Long = 0
)
