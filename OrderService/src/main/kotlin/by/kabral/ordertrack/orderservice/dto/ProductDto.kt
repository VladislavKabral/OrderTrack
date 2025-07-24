package by.kabral.ordertrack.orderservice.dto

import java.math.BigDecimal
import java.util.*

data class ProductDto(
    val id: UUID,
    val name: String,
    val description: String?,
    var price: BigDecimal,
    val quantity: ProductQuantityDto
)
