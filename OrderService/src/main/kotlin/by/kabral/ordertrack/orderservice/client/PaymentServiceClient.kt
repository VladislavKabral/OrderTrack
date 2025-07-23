package by.kabral.ordertrack.orderservice.client

import by.kabral.ordertrack.dto.PaymentDto
import by.kabral.ordertrack.orderservice.config.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "payment-service",
    url = "http://localhost:8765/payments",
    configuration = [FeignConfig::class]
)
interface PaymentServiceClient {
    @PostMapping("/payment")
    fun makePayment(@RequestBody payment: PaymentDto) : PaymentDto
}