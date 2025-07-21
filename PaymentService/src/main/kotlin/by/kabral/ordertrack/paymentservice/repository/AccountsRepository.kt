package by.kabral.ordertrack.paymentservice.repository

import by.kabral.ordertrack.paymentservice.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountsRepository : JpaRepository<Account, UUID> {
    fun existsByCustomerId(customerId: UUID) : Boolean
}