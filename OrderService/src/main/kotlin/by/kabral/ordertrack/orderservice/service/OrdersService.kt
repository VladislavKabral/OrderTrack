package by.kabral.ordertrack.orderservice.service

import by.kabral.ordertrack.dto.PaymentDto
import by.kabral.ordertrack.enum.PaymentStatus
import by.kabral.ordertrack.exception.EntityNotFoundException
import by.kabral.ordertrack.orderservice.client.CustomerServiceClient
import by.kabral.ordertrack.orderservice.client.PaymentServiceClient
import by.kabral.ordertrack.orderservice.client.ProductServiceClient
import by.kabral.ordertrack.orderservice.dto.OrderDto
import by.kabral.ordertrack.orderservice.dto.OrdersDto
import by.kabral.ordertrack.orderservice.mapper.OrdersMapper
import by.kabral.ordertrack.orderservice.model.OrderStatus
import by.kabral.ordertrack.orderservice.repository.OrdersRepository
import by.kabral.ordertrack.orderservice.util.Message.CUSTOMER_NOT_FOUND
import by.kabral.ordertrack.orderservice.util.Message.ORDER_NOT_FOUND
import by.kabral.ordertrack.orderservice.util.Message.PRODUCT_NOT_FOUND
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class OrdersService(
    private val ordersRepository: OrdersRepository,
    private val ordersMapper: OrdersMapper,
    private val customerServiceClient: CustomerServiceClient,
    private val productServiceClient: ProductServiceClient,
    private val paymentServiceClient: PaymentServiceClient
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
        order.createdAt = LocalDateTime.now()

        val customerExistence = customerServiceClient.isCustomerPresent(order.customerId)
        if (!customerExistence.isPresent) {
            throw EntityNotFoundException(String.format(CUSTOMER_NOT_FOUND, order.customerId))
        }

        val productAvailability = productServiceClient.checkAvailability(
            id = order.productId,
            count = order.count,
            totalAmount = order.totalAmount
        )
        if (!productAvailability.isPresent) {
            throw EntityNotFoundException(String.format(PRODUCT_NOT_FOUND, order.productId))
        }

        if (!productAvailability.isEnough || !productAvailability.isOrderRequestValid) {
            order.status = OrderStatus.FAILED
            return ordersMapper.toDto(ordersRepository.save(order))
        }

        val payment = PaymentDto(
            customerId = dto.customerId,
            amount = dto.totalAmount,
            status = PaymentStatus.IN_PROGRESS
        )

        val paymentResponse = paymentServiceClient.makePayment(payment)

        when (paymentResponse.status) {
            PaymentStatus.SUCCESSFULLY -> order.status = OrderStatus.PAID
            PaymentStatus.FAILED -> order.status = OrderStatus.FAILED
            PaymentStatus.NOT_ENOUGH_MONEY -> order.status = OrderStatus.NOT_ENOUGH_MONEY
            else -> order.status = OrderStatus.FAILED
        }

        return ordersMapper.toDto(ordersRepository.save(order))
    }
}