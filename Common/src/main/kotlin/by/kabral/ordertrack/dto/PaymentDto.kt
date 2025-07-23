package by.kabral.ordertrack.dto

import by.kabral.ordertrack.enums.PaymentStatus
import java.math.BigDecimal
import java.util.*

data class PaymentDto(
    val customerId: UUID,
    val amount: BigDecimal,
    var status: PaymentStatus?
)
