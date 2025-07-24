package by.kabral.ordertrack.orderservice.client

import by.kabral.ordertrack.dto.ProductAvailabilityDto
import by.kabral.ordertrack.orderservice.config.FeignConfig
import by.kabral.ordertrack.orderservice.dto.ProductDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal
import java.util.*

@FeignClient(
    name = "product-service",
    url = "http://localhost:8765/products",
    configuration = [FeignConfig::class]
)
interface ProductServiceClient {

    @GetMapping("/{id}/check")
    fun checkAvailability(
        @PathVariable("id") id: UUID,
        @RequestParam("count") count: Long,
        @RequestParam("totalAmount") totalAmount: BigDecimal) : ProductAvailabilityDto

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: UUID) : ProductDto
}