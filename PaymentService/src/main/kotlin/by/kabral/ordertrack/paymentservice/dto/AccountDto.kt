package by.kabral.ordertrack.paymentservice.dto

import by.kabral.ordertrack.paymentservice.model.AccountStatus
import java.math.BigDecimal
import java.util.*

data class AccountDto(
    var id: UUID?,
    val customerId: UUID,
    val balance: BigDecimal?,
    var status: AccountStatus?
)
