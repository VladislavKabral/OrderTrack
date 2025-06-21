package by.kabral.customerservice.repository

import by.kabral.customerservice.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CustomersRepository : JpaRepository<Customer, UUID> {
}