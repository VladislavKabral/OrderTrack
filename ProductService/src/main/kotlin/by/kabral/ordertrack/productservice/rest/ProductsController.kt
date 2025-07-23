package by.kabral.ordertrack.productservice.rest

import by.kabral.ordertrack.dto.ProductAvailabilityDto
import by.kabral.ordertrack.dto.RemovedEntityDto
import by.kabral.ordertrack.dto.SoldProductDto
import by.kabral.ordertrack.productservice.dto.ProductDto
import by.kabral.ordertrack.productservice.dto.ProductsDto
import by.kabral.ordertrack.productservice.service.ProductsService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("/products")
class ProductsController(private val productsService: ProductsService) {

    @GetMapping
    fun getProducts() : ResponseEntity<ProductsDto> {
        return ResponseEntity.ok(productsService.findAll())
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: UUID) : ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productsService.findById(id))
    }

    @GetMapping("/{id}/check")
    fun checkAvailability(
        @PathVariable("id") id: UUID,
        @RequestParam("count") count: Long,
        @RequestParam("totalAmount") totalAmount: BigDecimal
    ) : ResponseEntity<ProductAvailabilityDto> {
        return ResponseEntity.ok(productsService.isProductAvailable(id, count, totalAmount))
    }

    @PostMapping
    fun saveProduct(@RequestBody @Valid product: ProductDto) : ResponseEntity<ProductDto> {
        return ResponseEntity(productsService.save(product), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable("id") id: UUID,
                      @RequestBody @Valid product: ProductDto) : ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productsService.update(id, product))
    }

    @PutMapping
    fun updateProductCount(@RequestBody product: SoldProductDto) : ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productsService.updateCount(product))
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable("id") id: UUID) : ResponseEntity<RemovedEntityDto> {
        return ResponseEntity.ok(productsService.delete(id))
    }
}