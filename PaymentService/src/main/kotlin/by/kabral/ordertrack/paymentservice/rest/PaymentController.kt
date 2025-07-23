package by.kabral.ordertrack.paymentservice.rest

import by.kabral.ordertrack.dto.AccountDto
import by.kabral.ordertrack.dto.PaymentDto
import by.kabral.ordertrack.paymentservice.dto.AccountBalanceDto
import by.kabral.ordertrack.paymentservice.dto.AccountStatusDto
import by.kabral.ordertrack.paymentservice.dto.AccountsDto
import by.kabral.ordertrack.paymentservice.service.PaymentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @GetMapping("/accounts")
    fun findAllAccounts() : ResponseEntity<AccountsDto> {
        return ResponseEntity.ok(paymentService.findAllAccounts())
    }

    @GetMapping("/accounts/{id}")
    fun getAccount(@PathVariable("id") id: UUID) : ResponseEntity<AccountDto> {
        return ResponseEntity.ok(paymentService.findAccountById(id))
    }

    @PostMapping("/accounts")
    fun createAccount(@RequestBody @Valid account: AccountDto) : ResponseEntity<AccountDto> {
        return ResponseEntity(paymentService.createAccount(account), HttpStatus.CREATED)
    }

    @PostMapping("/payment")
    fun makePayment(@RequestBody payment: PaymentDto) : ResponseEntity<PaymentDto> {
        return paymentService.makePayment(payment)
    }

    @PutMapping("/{id}/balance")
    fun updateBalance(@PathVariable("id") id: UUID,
                      @RequestBody balance: AccountBalanceDto) : ResponseEntity<AccountDto> {
        return ResponseEntity.ok(paymentService.updateBalance(id, balance))
    }

    @PutMapping("/{id}/status")
    fun updateStatus(@PathVariable("id") id: UUID,
                      @RequestBody status: AccountStatusDto) : ResponseEntity<AccountDto> {
        return ResponseEntity.ok(paymentService.changeStatus(id, status))
    }
}