package by.kabral.ordertrack.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ProcessedOrderDto(
    val orderId: UUID,
    val customer: CustomerDto,
    val productName: String,
    val productCount: Long,
    val totalAmount: BigDecimal,
    val createdAt: LocalDateTime
)
