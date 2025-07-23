package by.kabral.ordertrack.orderservice.migration

import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.time.LocalDateTime
import java.util.UUID

@ChangeUnit(id = "init-orders", order = "001", author = "Vladislav Kabral")
class InitOrders {
    @Execution
    fun execute(mongoTemplate: MongoTemplate) {
        val orders = listOf(
            Document("id", UUID.fromString("dfc45897-78a1-4941-90f2-6d5fd5e55cf5"))
                .append("productId", UUID.fromString("cdc075e0-dafc-450f-aeac-e418d8d70196"))
                .append("customerId", UUID.fromString("8cd0c7f9-914d-4bad-9fa0-2346e713487d"))
                .append("count", 1)
                .append("totalAmount", 29.99)
                .append("createdAt", LocalDateTime.now())
                .append("status", "PAID"),
            Document("id", UUID.fromString("1c7da15d-d470-4c58-930d-4a10e7744b60"))
                .append("productId", UUID.fromString("cdc075e0-dafc-450f-aeac-e418d8d70196"))
                .append("customerId", UUID.fromString("1aeba215-76ba-4bfd-bbb6-54d854a39793"))
                .append("count", 1)
                .append("totalAmount", 29.99)
                .append("createdAt", LocalDateTime.now())
                .append("status", "PAID"),
            Document("id", UUID.fromString("e7f5016c-7869-4723-a51c-49659afc2ae5"))
                .append("productId", UUID.fromString("cdc075e0-dafc-450f-aeac-e418d8d70196"))
                .append("customerId", UUID.fromString("845349df-d6fd-4b06-97cb-0ac0c6536ff9"))
                .append("count", 1)
                .append("totalAmount", 29.99)
                .append("createdAt", LocalDateTime.now())
                .append("status", "PAID"),
            Document("id", UUID.fromString("b3574e44-079d-4ff2-bafa-1e713ce2a832"))
                .append("productId", UUID.fromString("cdc075e0-dafc-450f-aeac-e418d8d70196"))
                .append("customerId", UUID.fromString("6c18907d-d57f-4212-89c3-d1be1f579617"))
                .append("count", 1)
                .append("totalAmount", 29.99)
                .append("createdAt", LocalDateTime.now())
                .append("status", "PAID"),
            Document("id", UUID.fromString("09549c7b-825c-4835-90f2-0a819c7cfd07"))
                .append("productId", UUID.fromString("cdc075e0-dafc-450f-aeac-e418d8d70196"))
                .append("customerId", UUID.fromString("4bb27784-1063-4912-ade8-022fab508caf"))
                .append("count", 1)
                .append("totalAmount", 29.99)
                .append("createdAt", LocalDateTime.now())
                .append("status", "PAID"),
            )
        mongoTemplate.insert(orders, "orders")
    }

    @RollbackExecution
    fun rollback(mongoTemplate: MongoTemplate) {
        mongoTemplate.remove(Query.query(Criteria.where("id")
            .`is`("dfc45897-78a1-4941-90f2-6d5fd5e55cf5")), "orders")
        mongoTemplate.remove(Query.query(Criteria.where("id")
            .`is`("1c7da15d-d470-4c58-930d-4a10e7744b60")), "orders")
        mongoTemplate.remove(Query.query(Criteria.where("id")
            .`is`("e7f5016c-7869-4723-a51c-49659afc2ae5")), "orders")
        mongoTemplate.remove(Query.query(Criteria.where("id")
            .`is`("b3574e44-079d-4ff2-bafa-1e713ce2a832")), "orders")
        mongoTemplate.remove(Query.query(Criteria.where("id")
            .`is`("09549c7b-825c-4835-90f2-0a819c7cfd07")), "orders")
    }
}