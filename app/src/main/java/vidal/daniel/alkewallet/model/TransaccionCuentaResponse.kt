package vidal.daniel.alkewallet.model

/**
 * Clase que obtendrá la respuesta desde la Api después de generar una transacción en Account para Actualizar el Saldo
 */
data class TransaccionCuentaResponse
(
     val message    : String?
    ,val error      : String?
    ,val status     : Int?
)
