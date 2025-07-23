package by.kabral.ordertrack.productservice.service

import by.kabral.ordertrack.util.Constant.PRODUCT_AVAILABILITY_CACHE
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class CacheService(private val redisTemplate: RedisTemplate<String, Any>) {
    fun deleteCachedValue(productId: UUID) {
        val key = "${PRODUCT_AVAILABILITY_CACHE}_${productId}"
        redisTemplate.delete(key)
    }
}