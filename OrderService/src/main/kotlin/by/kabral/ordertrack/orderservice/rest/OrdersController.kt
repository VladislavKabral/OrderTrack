package by.kabral.ordertrack.orderservice.rest

import by.kabral.ordertrack.orderservice.dto.OrderDto
import by.kabral.ordertrack.orderservice.dto.OrdersDto
import by.kabral.ordertrack.orderservice.service.OrdersService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/orders")
class OrdersController(
    private val ordersService: OrdersService
) {

    @GetMapping
    fun getOrders() : ResponseEntity<OrdersDto> {
        return ResponseEntity.ok(ordersService.findAll())
    }

    @GetMapping("/{id}")
    fun getOrder(@PathVariable("id") id: UUID) : ResponseEntity<OrderDto> {
        return ResponseEntity.ok(ordersService.findById(id))
    }

    @PostMapping
    fun saveOrder(@RequestBody @Valid order: OrderDto) : ResponseEntity<OrderDto> {
        return ResponseEntity(ordersService.save(order), HttpStatus.CREATED)
    }
}