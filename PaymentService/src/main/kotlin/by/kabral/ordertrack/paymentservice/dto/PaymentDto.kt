package by.kabral.ordertrack.paymentservice.dto

import by.kabral.ordertrack.enum.PaymentStatus
import java.math.BigDecimal
import java.util.*

data class PaymentDto(
    val accountId: UUID,
    val amount: BigDecimal,
    var status: PaymentStatus?
)
