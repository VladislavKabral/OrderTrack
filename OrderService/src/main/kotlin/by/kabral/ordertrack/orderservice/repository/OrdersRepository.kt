package by.kabral.ordertrack.orderservice.repository

import by.kabral.ordertrack.orderservice.model.Order
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface OrdersRepository : MongoRepository<Order, UUID> {
    @Query("{ 'id': ?0 }")
    fun findByOrderId(id: UUID): Optional<Order>
}