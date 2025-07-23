package by.kabral.ordertrack.orderservice.client

import by.kabral.ordertrack.dto.ProductAvailabilityDto
import by.kabral.ordertrack.orderservice.config.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
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
}