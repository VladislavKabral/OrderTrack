package by.kabral.ordertrack.notificationservice.config

import by.kabral.ordertrack.dto.ProcessedOrderDto
import by.kabral.ordertrack.notificationservice.config.prop.KafkaProperties
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConfig(private val kafkaProperties: KafkaProperties) {

    @Bean
    fun newOrderTopic() : NewTopic {
        return TopicBuilder
            .name(kafkaProperties.newOrderTopicName)
            .partitions(kafkaProperties.partitionsCount)
            .build()
    }

    @Bean
    fun consumerFactory() : ConsumerFactory<String, ProcessedOrderDto> {
        val props = HashMap<String, Any>()

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer::class.java)
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, kafkaProperties.valueDefaultType)
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false)
        props.put(JsonDeserializer.TRUSTED_PACKAGES, kafkaProperties.trustedPackages)

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun orderKafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String, ProcessedOrderDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, ProcessedOrderDto>();
        factory.consumerFactory = consumerFactory()
        return factory;
    }
}