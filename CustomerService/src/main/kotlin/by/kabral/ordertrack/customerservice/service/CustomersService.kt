package by.kabral.ordertrack.customerservice.service

import by.kabral.ordertrack.customerservice.config.prop.KafkaProperties
import by.kabral.ordertrack.customerservice.dto.CustomerDto
import by.kabral.ordertrack.customerservice.dto.CustomersDto
import by.kabral.ordertrack.customerservice.kafka.KafkaProducer
import by.kabral.ordertrack.customerservice.mapper.CustomersMapper
import by.kabral.ordertrack.customerservice.repository.CustomersRepository
import by.kabral.ordertrack.customerservice.util.Message
import by.kabral.ordertrack.customerservice.util.validator.CustomersValidator
import by.kabral.ordertrack.dto.AccountDto
import by.kabral.ordertrack.dto.RemovedEntityDto
import by.kabral.ordertrack.dto.CustomerExistenceDto
import by.kabral.ordertrack.exception.EntityNotFoundException

import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomersService(
    private val customersRepository: CustomersRepository,
    private val customersMapper: CustomersMapper,
    private val customersValidator: CustomersValidator,
    private val kafkaProducer: KafkaProducer,
    private val cacheService: CacheService,
    private val kafkaProperties: KafkaProperties
) {

    fun findAll() : CustomersDto = CustomersDto(customersRepository.findAll().map { customersMapper.toDto(it) })

    fun findById(id: UUID) : CustomerDto = customersMapper.toDto(customersRepository.findById(id)
        .orElseThrow{ EntityNotFoundException(String.format(Message.USER_NOT_FOUND, id)) })

    fun customerExists(id: UUID) : CustomerExistenceDto {
        return CustomerExistenceDto(customersRepository.existsById(id))
    }

    fun save(dto: CustomerDto) : CustomerDto {
        customersValidator.validate(dto)
        val customer = customersMapper.toEntity(dto)
        val newCustomer = customersMapper.toDto(customersRepository.save(customer))

        val account = AccountDto(
            id = null,
            customerId = newCustomer.id!!,
            balance = null,
            status = null
        )
        kafkaProducer.send(kafkaProperties.newUserTopicName, account)

        return newCustomer
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

    fun deleteById(id: UUID) : RemovedEntityDto {
        if (!customersRepository.existsById(id)) {
            throw EntityNotFoundException(String.format(Message.USER_NOT_FOUND, id))
        }

        customersRepository.deleteById(id)
        cacheService.deleteCachedValue(id)

        val removedUserAccount = AccountDto(customerId = id)
        kafkaProducer.send(kafkaProperties.removedUserTopicName, removedUserAccount)

        return RemovedEntityDto(id)
    }
}