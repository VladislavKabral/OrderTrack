package by.kabral.ordertrack.productservice.repository

import by.kabral.ordertrack.productservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductsRepository : JpaRepository<Product, UUID> {
}