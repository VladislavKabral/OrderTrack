package by.kabral.ordertrack.dto

import java.time.ZonedDateTime

data class ErrorResponseDto (
    var message: String,
    var timestamp: ZonedDateTime,
)