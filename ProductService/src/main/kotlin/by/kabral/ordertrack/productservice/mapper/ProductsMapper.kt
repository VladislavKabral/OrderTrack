package by.kabral.ordertrack.productservice.mapper

import by.kabral.ordertrack.productservice.dto.ProductDto
import by.kabral.ordertrack.productservice.model.Product
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProductsMapper {
    fun toDto(entity: Product) : ProductDto
    fun toEntity(dto: ProductDto) : Product
}