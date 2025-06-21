package by.kabral.customerservice.service

import by.kabral.customerservice.dto.CustomerDto
import by.kabral.customerservice.dto.CustomersDto
import by.kabral.customerservice.mapper.CustomersMapper
import by.kabral.customerservice.repository.CustomersRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomersService(
    private val customersRepository: CustomersRepository,
    private val customersMapper: CustomersMapper
) {

    fun findAll() : CustomersDto = CustomersDto(customersRepository.findAll().map { customersMapper.toDto(it) })

    fun findById(id: UUID) : CustomerDto = customersMapper.toDto(customersRepository.findById(id).orElseThrow())

    fun save(dto: CustomerDto) : CustomerDto {
        val customer = customersMapper.toEntity(dto)
        return customersMapper.toDto(customersRepository.save(customer))
    }

    fun update(id: UUID, dto: CustomerDto) : CustomerDto {
        val customer = customersMapper.toEntity(dto)
        customer.id = id

        return customersMapper.toDto(customersRepository.save(customer))
    }

    fun deleteById(id: UUID) : UUID {
        customersRepository.deleteById(id)

        return id
    }
}