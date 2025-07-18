package by.kabral.ordertrack.productservice.rest

import by.kabral.ordertrack.productservice.dto.ProductsDto
import by.kabral.ordertrack.productservice.service.ProductsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductsController(private val productsService: ProductsService) {

    @GetMapping
    fun getProducts() : ResponseEntity<ProductsDto> {
        return ResponseEntity.ok(productsService.findAll())
    }
}