package by.kabral.ordertrack.productservice.service

import by.kabral.ordertrack.dto.RemovedEntityDto
import by.kabral.ordertrack.exception.EntityNotFoundException
import by.kabral.ordertrack.productservice.dto.ProductDto
import by.kabral.ordertrack.productservice.dto.ProductsDto
import by.kabral.ordertrack.productservice.mapper.ProductsMapper
import by.kabral.ordertrack.productservice.model.ProductQuantity
import by.kabral.ordertrack.productservice.repository.ProductsRepository
import by.kabral.ordertrack.productservice.util.Message.PRODUCT_NOT_FOUND
import by.kabral.ordertrack.productservice.util.validator.ProductsValidator
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductsService(
    private val productsRepository: ProductsRepository,
    private val productsMapper: ProductsMapper,
    private val productsValidator: ProductsValidator
) {

    fun findAll() : ProductsDto {
        return ProductsDto(productsRepository.findAll().map { productsMapper.toDto(it) })
    }

    fun isEnoughQuantity(id: UUID) : Boolean {
        val product = findById(id)

        return product.quantity.quantity > 0
    }

    fun findById(id: UUID) : ProductDto {
        return productsMapper.toDto(productsRepository
            .findById(id)
            .orElseThrow { EntityNotFoundException(String.format(PRODUCT_NOT_FOUND, id)) })
    }

    fun save(dto: ProductDto) : ProductDto {
        productsValidator.validate(dto)

        val entity = productsMapper.toEntity(dto)
        val quantity = ProductQuantity(id = null, product = entity, quantity = dto.quantity.quantity)
        entity.quantity = quantity
        return productsMapper.toDto(productsRepository.save(entity))
    }

    fun update(id: UUID, dto: ProductDto) : ProductDto {
        if (!productsRepository.existsById(id)) {
            throw EntityNotFoundException(String.format(PRODUCT_NOT_FOUND, id))
        }

        productsValidator.validate(dto)

        val entity = productsRepository.findById(id).get()
        entity.name = dto.name
        entity.description = dto.description
        entity.price = dto.price
        entity.quantity.quantity = dto.quantity.quantity

        return productsMapper.toDto(productsRepository.save(entity))
    }

    fun delete(id: UUID) : RemovedEntityDto {
        if (!productsRepository.existsById(id)) {
            throw EntityNotFoundException(String.format(PRODUCT_NOT_FOUND, id))
        }

        productsRepository.deleteById(id)

        return RemovedEntityDto(id)
    }
}