package by.kabral.ordertrack.apigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
class ApiGatewayApplication

fun main(args: Array<String>) {
    runApplication<ApiGatewayApplication>(*args)
}
