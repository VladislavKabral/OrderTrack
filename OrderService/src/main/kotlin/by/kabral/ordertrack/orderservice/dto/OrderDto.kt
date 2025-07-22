package by.kabral.ordertrack.orderservice.dto

import by.kabral.ordertrack.orderservice.model.OrderStatus
import by.kabral.ordertrack.orderservice.util.Message.ORDER_MIN_COUNT_IS_INVALID
import by.kabral.ordertrack.orderservice.util.Message.ORDER_MIN_TOTAL_AMOUNT_IS_INVALID
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDto (
    var id: UUID?,
    val productId: UUID,
    val customerId: UUID,
    @field:Min(value = 0, message = ORDER_MIN_COUNT_IS_INVALID)
    val count: Long,
    @field:DecimalMin(value = "0", message = ORDER_MIN_TOTAL_AMOUNT_IS_INVALID)
    val totalAmount: BigDecimal,
    val createdAt: LocalDateTime?,
    var status: OrderStatus?
)