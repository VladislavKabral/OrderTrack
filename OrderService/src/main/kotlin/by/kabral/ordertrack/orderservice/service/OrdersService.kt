package by.kabral.ordertrack.orderservice.service

import by.kabral.ordertrack.exception.EntityNotFoundException
import by.kabral.ordertrack.orderservice.dto.OrderDto
import by.kabral.ordertrack.orderservice.dto.OrdersDto
import by.kabral.ordertrack.orderservice.mapper.OrdersMapper
import by.kabral.ordertrack.orderservice.model.OrderStatus
import by.kabral.ordertrack.orderservice.repository.OrdersRepository
import by.kabral.ordertrack.orderservice.util.Message.ORDER_NOT_FOUND
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class OrdersService(
    private val ordersRepository: OrdersRepository,
    private val ordersMapper: OrdersMapper
) {

    fun findAll() : OrdersDto {
        return OrdersDto(ordersRepository.findAll().map { ordersMapper.toDto(it) })
    }

    fun findById(id: UUID) : OrderDto {
        return ordersMapper.toDto(ordersRepository
            .findByOrderId(id)
            .orElseThrow { EntityNotFoundException(String.format(ORDER_NOT_FOUND, id)) })
    }

    fun save(dto: OrderDto) : OrderDto {
        val order = ordersMapper.toEntity(dto)
        order.id = UUID.randomUUID()
        order.status = OrderStatus.PAID
        order.createdAt = LocalDateTime.now()

        return ordersMapper.toDto(ordersRepository.save(order))
    }
}