package by.kabral.ordertrack.dto

import by.kabral.ordertrack.enums.AccountStatus
import java.math.BigDecimal
import java.util.*

data class AccountDto(
    var id: UUID?,
    val customerId: UUID,
    val balance: BigDecimal?,
    var status: AccountStatus?
)
