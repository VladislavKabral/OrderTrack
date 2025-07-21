package by.kabral.ordertrack.customerservice.mapper

import by.kabral.ordertrack.customerservice.dto.CustomerDto
import by.kabral.ordertrack.customerservice.model.Customer
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class CustomersMapper(private val mapper: ModelMapper) {

    fun toDto(entity: Customer): CustomerDto = mapper.map(entity, CustomerDto::class.java)

    fun toEntity(dto: CustomerDto): Customer = mapper.map(dto, Customer::class.java)
}