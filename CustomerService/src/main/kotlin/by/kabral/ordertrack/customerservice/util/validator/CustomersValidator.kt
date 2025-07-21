package by.kabral.ordertrack.customerservice.util.validator

import by.kabral.ordertrack.customerservice.dto.CustomerDto
import by.kabral.ordertrack.customerservice.repository.CustomersRepository
import by.kabral.ordertrack.customerservice.util.Message.USER_ALREADY_EXISTS
import by.kabral.ordertrack.exception.BusinessException
import by.kabral.ordertrack.validator.Validator
import org.springframework.stereotype.Component

@Component
class CustomersValidator(val repository: CustomersRepository) : Validator<CustomerDto> {

    override fun validate(dto: CustomerDto) {
        val entity = dto.email?.let { repository.findByEmail(it) }

        if ((entity != null) && (dto.id != entity.id)) {
            throw BusinessException(String.format(USER_ALREADY_EXISTS, entity.email))
        }
    }
}