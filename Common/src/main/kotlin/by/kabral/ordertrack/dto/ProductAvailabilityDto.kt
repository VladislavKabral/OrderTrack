package by.kabral.ordertrack.dto

data class ProductAvailabilityDto(
    val isPresent: Boolean,
    val isEnough: Boolean,
    val isOrderRequestValid: Boolean
)
