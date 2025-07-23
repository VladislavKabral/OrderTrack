package by.kabral.ordertrack.customerservice.service

import by.kabral.ordertrack.util.Constant.CUSTOMER_EXISTENCE_CACHE
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CacheService(private val redisTemplate: RedisTemplate<String, Any>) {
    fun deleteCachedValue(customerId: UUID) {
        val key = "${CUSTOMER_EXISTENCE_CACHE}_${customerId}"
        redisTemplate.delete(key)
    }
}