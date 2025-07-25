package by.kabral.ordertrack.orderservice.config

import by.kabral.ordertrack.dto.ProcessedOrderDto
import by.kabral.ordertrack.orderservice.config.prop.KafkaProperties
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig(
    private val kafkaProperties: KafkaProperties
) {
    @Bean
    fun producerFactory() : ProducerFactory<String, ProcessedOrderDto> {
        val props = HashMap<String, Any>()

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    fun kafkaTemplate() : KafkaTemplate<String, ProcessedOrderDto> {
        return KafkaTemplate(producerFactory())
    }
}