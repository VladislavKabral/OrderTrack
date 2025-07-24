package by.kabral.ordertrack.orderservice.client

import by.kabral.ordertrack.dto.CustomerDto
import by.kabral.ordertrack.dto.CustomerExistenceDto
import by.kabral.ordertrack.orderservice.config.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@FeignClient(
    name = "customer-service",
    url = "http://localhost:8765/customers",
    configuration = [FeignConfig::class]
)
interface CustomerServiceClient {

    @GetMapping("/{id}/isPresent")
     fun isCustomerPresent(@PathVariable("id") id: UUID) : CustomerExistenceDto

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable("id") id: UUID): CustomerDto
}