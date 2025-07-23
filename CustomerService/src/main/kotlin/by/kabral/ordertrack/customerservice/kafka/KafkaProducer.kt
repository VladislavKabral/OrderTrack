package by.kabral.ordertrack.customerservice.kafka

import by.kabral.ordertrack.dto.AccountDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, AccountDto>
) {

    fun send(topicName: String, newUser: AccountDto) {
        kafkaTemplate.send(topicName, newUser)
    }
}