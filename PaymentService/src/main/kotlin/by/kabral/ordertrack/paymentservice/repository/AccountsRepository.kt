package by.kabral.ordertrack.paymentservice.repository

import by.kabral.ordertrack.paymentservice.model.Account
import by.kabral.ordertrack.paymentservice.model.AccountStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface AccountsRepository : JpaRepository<Account, UUID> {
    fun existsByCustomerId(customerId: UUID) : Boolean
    fun existsByCustomerIdAndStatus(customerId: UUID, status: AccountStatus) : Boolean
    fun findByCustomerIdAndStatus(customerId: UUID, status: AccountStatus) : Optional<Account>
}