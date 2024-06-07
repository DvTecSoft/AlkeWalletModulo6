package vidal.daniel.alkewallet.model

data class SignupResponse
    (
         val userId : Int?
        ,val first_name : String?
        ,val last_name : String?
        ,val email : String?
        ,val password : String?
        ,val roleId : Int?
        ,val points : Int?
        ,val error : String?
        ,val status : Int?
    )
