package by.kabral.ordertrack.customerservice.config.prop

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "spring.kafka")
data class KafkaProperties @ConstructorBinding constructor(
    val bootstrapServers: String,
    val newUserTopicName: String,
    val removedUserTopicName: String,
    val newUserGroupId: String,
    val valueDefaultType: String,
    val trustedPackages: String
)
