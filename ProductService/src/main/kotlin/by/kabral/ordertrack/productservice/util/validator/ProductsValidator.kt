package by.kabral.ordertrack.productservice.util.validator

import by.kabral.ordertrack.exception.BusinessException
import by.kabral.ordertrack.productservice.dto.ProductDto
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_PRICE_IS_INVALID
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_QUANTITY_IS_INVALID
import by.kabral.ordertrack.validator.Validator
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ProductsValidator : Validator<ProductDto> {
    override fun validate(dto: ProductDto) {
        if (dto.price <= BigDecimal.ZERO) {
            throw BusinessException(PRODUCT_PRICE_IS_INVALID)
        }

        if (dto.quantity.quantity <= 0) {
            throw BusinessException(PRODUCT_QUANTITY_IS_INVALID)
        }
    }
}