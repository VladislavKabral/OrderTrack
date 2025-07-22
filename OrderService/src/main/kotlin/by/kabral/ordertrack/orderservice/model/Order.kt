package by.kabral.ordertrack.orderservice.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Document(collection = "orders")
data class Order(

    @Field("id")
    var id: UUID?,
    val productId: UUID,
    val customerId: UUID,
    val count: Long,
    val totalAmount: BigDecimal,
    var createdAt: LocalDateTime?,
    var status: OrderStatus?
)
