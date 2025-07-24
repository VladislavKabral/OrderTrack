package by.kabral.ordertrack.notificationservice.kafka

import by.kabral.ordertrack.dto.ProcessedOrderDto
import by.kabral.ordertrack.notificationservice.service.NotificationService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(private val notificationService: NotificationService) {

    @KafkaListener(
        topics = ["ordertrack.neworder"],
        groupId = "ordertrack.neworder.group",
        containerFactory = "orderKafkaListenerContainerFactory")
    fun handleNewOrderTopic(order: ProcessedOrderDto) {
        notificationService.sendEmail(order)
    }
}