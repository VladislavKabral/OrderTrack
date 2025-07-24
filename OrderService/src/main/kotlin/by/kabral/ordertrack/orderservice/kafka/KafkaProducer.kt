package by.kabral.ordertrack.orderservice.kafka

import by.kabral.ordertrack.dto.ProcessedOrderDto
import by.kabral.ordertrack.orderservice.config.prop.KafkaProperties
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, ProcessedOrderDto>,
    private val kafkaProperties: KafkaProperties
) {

    fun send(order: ProcessedOrderDto) {
        kafkaTemplate.send(kafkaProperties.newOrderTopicName, order)
    }
}