package by.kabral.ordertrack.productservice.dto

import by.kabral.ordertrack.productservice.util.Constant.PRODUCT_DESCRIPTION_MAX_LENGTH
import by.kabral.ordertrack.productservice.util.Constant.PRODUCT_DESCRIPTION_MIN_LENGTH
import by.kabral.ordertrack.productservice.util.Constant.PRODUCT_DESCRIPTION_REGEXP
import by.kabral.ordertrack.productservice.util.Constant.PRODUCT_NAME_MAX_LENGTH
import by.kabral.ordertrack.productservice.util.Constant.PRODUCT_NAME_MIN_LENGTH
import by.kabral.ordertrack.productservice.util.Constant.PRODUCT_NAME_REGEXP
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_DESCRIPTION_FORMAT_IS_WRONG
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_DESCRIPTION_IS_EMPTY
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_DESCRIPTION_SIZE_IS_INVALID
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_NAME_FORMAT_IS_WRONG
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_NAME_IS_EMPTY
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_NAME_SIZE_IS_INVALID
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_PRICE_IS_NULL
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.util.*

data class ProductDto (
    var id: UUID?,

    @field:NotBlank(message = PRODUCT_NAME_IS_EMPTY)
    @field:Size(min = PRODUCT_NAME_MIN_LENGTH, max = PRODUCT_NAME_MAX_LENGTH, message = PRODUCT_NAME_SIZE_IS_INVALID)
    @field:Pattern(regexp = PRODUCT_NAME_REGEXP, message = PRODUCT_NAME_FORMAT_IS_WRONG)
    var name: String,

    @field:NotBlank(message = PRODUCT_DESCRIPTION_IS_EMPTY)
    @field:Size(min = PRODUCT_DESCRIPTION_MIN_LENGTH, max = PRODUCT_DESCRIPTION_MAX_LENGTH, message = PRODUCT_DESCRIPTION_SIZE_IS_INVALID)
    @field:Pattern(regexp = PRODUCT_DESCRIPTION_REGEXP, message = PRODUCT_DESCRIPTION_FORMAT_IS_WRONG)
    var description: String?,

    @field:NotNull(message = PRODUCT_PRICE_IS_NULL)
    var price: BigDecimal,

    val quantity: ProductQuantityDto
)