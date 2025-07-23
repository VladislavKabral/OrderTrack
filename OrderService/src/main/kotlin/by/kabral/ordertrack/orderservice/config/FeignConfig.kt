package by.kabral.ordertrack.orderservice.config

import by.kabral.ordertrack.orderservice.client.FeignErrorDecoder
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {
    @Bean
    fun errorDecoder(): ErrorDecoder = FeignErrorDecoder()
}