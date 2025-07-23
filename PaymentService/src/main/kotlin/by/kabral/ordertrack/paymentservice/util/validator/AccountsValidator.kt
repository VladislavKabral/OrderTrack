package by.kabral.ordertrack.paymentservice.util.validator

import by.kabral.ordertrack.dto.AccountDto
import by.kabral.ordertrack.exception.BusinessException
import by.kabral.ordertrack.paymentservice.repository.AccountsRepository
import by.kabral.ordertrack.paymentservice.util.Message.ACCOUNT_ALREADY_EXISTS
import by.kabral.ordertrack.validator.Validator
import org.springframework.stereotype.Component

@Component
class AccountsValidator(
    private val accountsRepository: AccountsRepository
) : Validator<AccountDto> {
    override fun validate(dto: AccountDto) {
        if (accountsRepository.existsByCustomerId(dto.customerId)) {
            throw BusinessException(String.format(ACCOUNT_ALREADY_EXISTS, dto.customerId))
        }
    }
}