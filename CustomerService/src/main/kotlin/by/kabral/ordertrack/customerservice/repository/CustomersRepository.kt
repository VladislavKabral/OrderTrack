package by.kabral.ordertrack.customerservice.repository

import by.kabral.ordertrack.customerservice.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface CustomersRepository : JpaRepository<Customer, UUID> {
    fun findByEmail(email: String): Customer?
}