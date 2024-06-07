package vidal.daniel.alkewallet.model

/**
 * Clase usada para enviar los datos a la api para la creación del usuario
 */
data class SignupRequest
(
     val first_name : String
    ,val last_name : String
    ,val email : String
    ,val password : String
    ,val roleId : Int?
    ,val points : Int?
)