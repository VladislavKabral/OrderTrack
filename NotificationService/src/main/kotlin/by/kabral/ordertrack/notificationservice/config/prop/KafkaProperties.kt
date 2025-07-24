package by.kabral.ordertrack.notificationservice.config.prop

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "spring.kafka")
data class KafkaProperties @ConstructorBinding constructor(
    val bootstrapServers: String,
    val newOrderTopicName: String,
    val newOrderGroupId: String,
    val valueDefaultType: String,
    val trustedPackages: String,
    val partitionsCount: Int
)
