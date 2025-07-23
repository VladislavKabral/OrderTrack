package by.kabral.ordertrack.orderservice.config

import io.mongock.driver.mongodb.springdata.v4.SpringDataMongoV4Driver
import io.mongock.runner.springboot.MongockSpringboot
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongockConfig(
    private val mongoTemplate: MongoTemplate,
    private val applicationContext: ApplicationContext
) {

    @Bean
    fun mongockRunner(): MongockInitializingBeanRunner {
        val driver = SpringDataMongoV4Driver.withDefaultLock(mongoTemplate)

        return MongockSpringboot.builder()
            .setDriver(driver)
            .setSpringContext(applicationContext)
            .addMigrationScanPackage("by.kabral.ordertrack.orderservice.migration")
            .buildInitializingBeanRunner()
    }
}