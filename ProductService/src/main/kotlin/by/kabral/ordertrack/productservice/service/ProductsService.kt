package by.kabral.ordertrack.productservice.service

import by.kabral.ordertrack.productservice.dto.ProductsDto
import by.kabral.ordertrack.productservice.mapper.ProductsMapper
import by.kabral.ordertrack.productservice.repository.ProductsRepository
import org.springframework.stereotype.Service

@Service
class ProductsService(
    private val productsRepository: ProductsRepository,
    private val productsMapper: ProductsMapper
) {

    fun findAll() : ProductsDto {
        return ProductsDto(productsRepository.findAll().map { productsMapper.toDto(it) })
    }
}