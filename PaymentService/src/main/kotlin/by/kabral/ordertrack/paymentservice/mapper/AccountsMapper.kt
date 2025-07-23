package by.kabral.ordertrack.paymentservice.mapper

import by.kabral.ordertrack.dto.AccountDto
import by.kabral.ordertrack.paymentservice.model.Account
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AccountsMapper {
    fun toDto(entity: Account) : AccountDto
    fun toEntity(dto: AccountDto) : Account
}