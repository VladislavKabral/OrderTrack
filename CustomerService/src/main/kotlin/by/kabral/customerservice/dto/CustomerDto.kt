package by.kabral.customerservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class CustomerDto (
    var id: UUID = UUID.randomUUID(),
    var lastname: String = "",
    var firstname: String = "",
    var email: String = "",
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String = ""
)
