package by.kabral.ordertrack.customerservice.kafka

import by.kabral.ordertrack.customerservice.config.prop.KafkaProperties
import by.kabral.ordertrack.dto.AccountDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, AccountDto>,
    private val kafkaProperties: KafkaProperties
) {

    fun send(newUser: AccountDto) {
        kafkaTemplate.send(kafkaProperties.newUserTopicName, newUser)
    }
}