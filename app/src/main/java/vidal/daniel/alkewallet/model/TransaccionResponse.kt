package vidal.daniel.alkewallet.model

import java.util.Date

data class TransaccionResponse
(
     val previousPage: String?,
     val nextPage: String?,
     val data: List<TransaccionModel>?,
     val error: String?,
     val status: String?
)
