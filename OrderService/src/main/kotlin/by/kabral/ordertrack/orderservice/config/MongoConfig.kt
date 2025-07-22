package by.kabral.ordertrack.orderservice.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import org.bson.UuidRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class MongoConfig(
    @Value("\${spring.data.mongodb.host}") val dbHost: String,
    @Value("\${spring.data.mongodb.port}") val dbPort: String,
    @Value("\${spring.data.mongodb.database}") val dbName: String,
) {

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val settings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString("mongodb://${dbHost}:${dbPort}"))
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build()
        val mongoClient = MongoClients.create(settings)
        val databaseFactory = SimpleMongoClientDatabaseFactory(mongoClient, dbName)
        return MongoTemplate(databaseFactory)
    }

}
