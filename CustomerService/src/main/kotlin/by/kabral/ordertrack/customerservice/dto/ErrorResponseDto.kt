package by.kabral.ordertrack.customerservice.dto

import java.time.ZonedDateTime

data class ErrorResponseDto (
    var message: String,
    var timestamp: ZonedDateTime,
)