package by.kabral.ordertrack.productservice.dto

import java.math.BigDecimal
import java.util.*

data class ProductDto (
    var id: UUID?,
    var name: String,
    var description: String?,
    var price: BigDecimal,
    val quantity: ProductQuantityDto
)