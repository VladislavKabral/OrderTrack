package by.kabral.ordertrack.customerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CustomerServiceApplication

fun main(args: Array<String>) {
    runApplication<CustomerServiceApplication>(*args)
}
