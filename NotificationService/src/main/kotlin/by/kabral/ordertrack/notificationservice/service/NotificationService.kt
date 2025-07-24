package by.kabral.ordertrack.notificationservice.service

import by.kabral.ordertrack.dto.ProcessedOrderDto
import by.kabral.ordertrack.notificationservice.util.Constant.DATE_FORMAT
import by.kabral.ordertrack.notificationservice.util.Constant.MAIL_SUBJECT
import by.kabral.ordertrack.notificationservice.util.Template.NEW_ORDER_NOTIFICATION_TEMPLATE
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class NotificationService(
    private val mailSender: JavaMailSender,
    @Value("\${spring.mail.username}") val mailUsername: String
) {

    fun sendEmail(order: ProcessedOrderDto) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, "UTF-8")
        helper.setTo(order.customer.email)
        helper.setSubject(MAIL_SUBJECT)
        helper.setFrom(mailUsername)

        helper.setText(generateMail(order), true)
        mailSender.send(message)
    }

    private fun generateMail(order: ProcessedOrderDto) : String {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)

        return NEW_ORDER_NOTIFICATION_TEMPLATE
            .replace("\$lastname", order.customer.lastname)
            .replace("\$firstname", order.customer.firstname)
            .replace("\$orderId", order.orderId.toString())
            .replace("\$productName", order.productName)
            .replace("\$productCount", order.productCount.toString())
            .replace("\$totalAmount", order.totalAmount.toString())
            .replace("\$createdAt", order.createdAt.format(formatter))
    }
}