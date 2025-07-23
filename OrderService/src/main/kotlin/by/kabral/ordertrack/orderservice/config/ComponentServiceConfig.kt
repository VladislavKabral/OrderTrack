package by.kabral.ordertrack.orderservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class ComponentServiceConfig {

    @Bean
    fun restClient() : RestClient {
        return RestClient.create()
    }
}