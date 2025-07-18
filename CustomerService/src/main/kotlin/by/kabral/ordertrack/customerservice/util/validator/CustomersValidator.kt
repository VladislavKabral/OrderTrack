package by.kabral.ordertrack.customerservice.util.validator

import by.kabral.ordertrack.customerservice.dto.CustomerDto
import by.kabral.ordertrack.customerservice.repository.CustomersRepository
import by.kabral.ordertrack.customerservice.util.Message.USER_ALREADY_EXISTS
import by.kabral.ordertrack.exception.BusinessException
import org.springframework.stereotype.Component

@Component
class CustomersValidator(val repository: CustomersRepository) {

    fun validate(customer: CustomerDto) {
        val entity = customer.email?.let { repository.findByEmail(it) }

        if ((entity != null) && (customer.id != entity.id)) {
            throw BusinessException(String.format(USER_ALREADY_EXISTS, entity.email))
        }
    }
}