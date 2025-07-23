package by.kabral.ordertrack.orderservice.service

import by.kabral.ordertrack.dto.CustomerExistenceDto
import by.kabral.ordertrack.dto.ProductAvailabilityDto
import by.kabral.ordertrack.orderservice.util.Constant.DEFAULT_TTL
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.UUID

@Service
class CacheService(private val redisTemplate: RedisTemplate<String, Any>) {

    fun put(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value)
        redisTemplate.expire(key, Duration.ofHours(DEFAULT_TTL))
    }

    fun getCustomerExistence(key: String) : CustomerExistenceDto? {
        return redisTemplate.opsForValue().get(key) as? CustomerExistenceDto
    }

    fun getProductAvailability(key: String) : ProductAvailabilityDto? {
        return redisTemplate.opsForValue().get(key) as? ProductAvailabilityDto
    }

    fun getCacheKey(entityType: String, entityId: UUID) : String {
        return "${entityType}_${entityId}"
    }

}