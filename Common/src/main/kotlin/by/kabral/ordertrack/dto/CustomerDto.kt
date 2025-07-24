package by.kabral.ordertrack.dto

import java.util.*

data class CustomerDto(
    val id: UUID,
    var lastname: String,
    var firstname: String,
    var email: String
)
