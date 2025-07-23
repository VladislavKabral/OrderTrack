package by.kabral.ordertrack.paymentservice.util.validator

import by.kabral.ordertrack.exception.BusinessException
import by.kabral.ordertrack.paymentservice.dto.AccountBalanceDto
import by.kabral.ordertrack.paymentservice.util.Message.BALANCE_VALUE_IS_INVALID
import by.kabral.ordertrack.validator.Validator
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class BalanceValidator : Validator<AccountBalanceDto> {

    override fun validate(dto: AccountBalanceDto) {
        if (dto.value < BigDecimal.ZERO) {
            throw BusinessException(BALANCE_VALUE_IS_INVALID)
        }
    }
}