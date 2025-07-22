package by.kabral.ordertrack.orderservice.mapper

import by.kabral.ordertrack.orderservice.dto.OrderDto
import by.kabral.ordertrack.orderservice.model.Order
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface OrdersMapper {
    fun toDto(entity: Order) : OrderDto
    fun toEntity(dto: OrderDto) : Order
}