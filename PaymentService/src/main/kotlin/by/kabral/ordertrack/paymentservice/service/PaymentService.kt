package by.kabral.ordertrack.paymentservice.service

import by.kabral.ordertrack.dto.AccountDto
import by.kabral.ordertrack.dto.PaymentDto
import by.kabral.ordertrack.enums.PaymentStatus
import by.kabral.ordertrack.exception.EntityNotFoundException
import by.kabral.ordertrack.paymentservice.dto.AccountBalanceDto
import by.kabral.ordertrack.paymentservice.dto.AccountStatusDto
import by.kabral.ordertrack.paymentservice.dto.AccountsDto
import by.kabral.ordertrack.paymentservice.mapper.AccountsMapper
import by.kabral.ordertrack.paymentservice.model.Account
import by.kabral.ordertrack.paymentservice.model.AccountStatus
import by.kabral.ordertrack.paymentservice.repository.AccountsRepository
import by.kabral.ordertrack.paymentservice.util.Message.ACCOUNT_NOT_FOUND
import by.kabral.ordertrack.paymentservice.util.Message.CUSTOMER_DOES_NOT_HAVE_ACCOUNT
import by.kabral.ordertrack.paymentservice.util.validator.AccountsValidator
import by.kabral.ordertrack.paymentservice.util.validator.BalanceValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class PaymentService(
    private val accountsRepository: AccountsRepository,
    private val accountsMapper: AccountsMapper,
    private val accountsValidator: AccountsValidator,
    private val balanceValidator: BalanceValidator
) {

    fun findAllAccounts() : AccountsDto {
        return AccountsDto(accountsRepository.findAll().map { accountsMapper.toDto(it) } )
    }

    fun findAccountById(id: UUID) : AccountDto {
        return accountsMapper.toDto(accountsRepository
            .findById(id)
            .orElseThrow { EntityNotFoundException(String.format(ACCOUNT_NOT_FOUND, id)) })
    }

    fun makePayment(payment: PaymentDto) : ResponseEntity<PaymentDto> {
        if (!accountsRepository.existsByCustomerIdAndStatus(payment.customerId, AccountStatus.ACTIVE)) {
            throw EntityNotFoundException(String.format(CUSTOMER_DOES_NOT_HAVE_ACCOUNT, payment.customerId))
        }

        val account = accountsRepository.findByCustomerIdAndStatus(
            customerId = payment.customerId,
            status = AccountStatus.ACTIVE
        ).get()

        if (account.balance!!.minus(payment.amount) < BigDecimal.ZERO) {
            payment.status = PaymentStatus.NOT_ENOUGH_MONEY
            return ResponseEntity(payment, HttpStatus.OK)
        }

        account.balance = account.balance!!.minus(payment.amount)
        payment.status = PaymentStatus.SUCCESSFULLY

        accountsRepository.save(account)

        return ResponseEntity.ok(payment)
    }

    fun createAccount(dto: AccountDto) : AccountDto {
        accountsValidator.validate(dto)

        val entity = Account(
            id = null,
            customerId = dto.customerId,
            balance = BigDecimal.ZERO,
            status = AccountStatus.ACTIVE
        )

        return accountsMapper.toDto(accountsRepository.save(entity))
    }

    fun updateBalance(id: UUID, dto: AccountBalanceDto) : AccountDto {
        balanceValidator.validate(dto)

        if (!accountsRepository.existsById(id)) {
            throw EntityNotFoundException(String.format(ACCOUNT_NOT_FOUND, id))
        }

        val account = accountsRepository.findById(id).get()
        account.balance = account.balance!!.plus(dto.value)

        return accountsMapper.toDto(accountsRepository.save(account))
    }

    fun changeStatus(id: UUID, dto: AccountStatusDto) : AccountDto {
        if (!accountsRepository.existsById(id)) {
            throw EntityNotFoundException(String.format(ACCOUNT_NOT_FOUND, id))
        }

        val account = accountsRepository.findById(id).get()
        account.status = dto.value

        return accountsMapper.toDto(accountsRepository.save(account))
    }

    fun archiveAccount(customerId: UUID) : AccountDto {
        if (!accountsRepository.existsByCustomerId(customerId)) {
            throw EntityNotFoundException(String.format(CUSTOMER_DOES_NOT_HAVE_ACCOUNT, customerId))
        }

        val account = accountsRepository.findByCustomerId(customerId).get()
        account.status = AccountStatus.ARCHIVED

        return accountsMapper.toDto(accountsRepository.save(account))
    }

}