package by.kabral.ordertrack.paymentservice.kafka

import by.kabral.ordertrack.dto.AccountDto
import by.kabral.ordertrack.paymentservice.service.PaymentService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(private val paymentService: PaymentService) {

    @KafkaListener(
        topics = ["ordertrack.newuser"],
        groupId = "ordertrack.newuser.group",
        containerFactory = "userKafkaListenerContainerFactory")
    fun handleNewUserTopic(account: AccountDto) {
        paymentService.createAccount(account)
    }

    @KafkaListener(
        topics = ["ordertrack.removeduser"],
        groupId = "ordertrack.removeduser.group",
        containerFactory = "userKafkaListenerContainerFactory")
    fun handleRemovedUserTopic(account: AccountDto) {
        paymentService.archiveAccount(account.customerId)
    }
}