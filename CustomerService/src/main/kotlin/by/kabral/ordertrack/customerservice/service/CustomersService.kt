package by.kabral.ordertrack.customerservice.service

import by.kabral.ordertrack.customerservice.dto.CustomerDto
import by.kabral.ordertrack.customerservice.dto.CustomersDto
import by.kabral.ordertrack.customerservice.exception.EntityNotFoundException
import by.kabral.ordertrack.customerservice.mapper.CustomersMapper
import by.kabral.ordertrack.customerservice.repository.CustomersRepository
import by.kabral.ordertrack.customerservice.util.Message
import by.kabral.ordertrack.customerservice.util.validator.CustomersValidator

import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomersService(
    private val customersRepository: CustomersRepository,
    private val customersMapper: CustomersMapper,
    private val customersValidator: CustomersValidator
) {

    fun findAll() : CustomersDto = CustomersDto(customersRepository.findAll().map { customersMapper.toDto(it) })

    fun findById(id: UUID) : CustomerDto = customersMapper.toDto(customersRepository.findById(id)
        .orElseThrow{ EntityNotFoundException(String.format(Message.USER_NOT_FOUND, id)) })

    fun save(dto: CustomerDto) : CustomerDto {
        customersValidator.validate(dto)
        val customer = customersMapper.toEntity(dto)
        return customersMapper.toDto(customersRepository.save(customer))
    }

    fun update(id: UUID, dto: CustomerDto) : CustomerDto {
        if (!customersRepository.existsById(id)) {
            throw EntityNotFoundException(String.format(Message.USER_NOT_FOUND, id))
        }

        customersValidator.validate(dto)
        val customer = customersMapper.toEntity(dto)
        customer.id = id

        return customersMapper.toDto(customersRepository.save(customer))
    }

    fun deleteById(id: UUID) : UUID {
        if (!customersRepository.existsById(id)) {
            throw EntityNotFoundException(String.format(Message.USER_NOT_FOUND, id))
        }

        customersRepository.deleteById(id)

        return id
    }
}