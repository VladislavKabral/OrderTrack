package by.kabral.ordertrack.orderservice.util

object Message {
    const val ORDER_NOT_FOUND = "The order with id '%s' was not found."
    const val CUSTOMER_NOT_FOUND = "The customer with id '%s' was not found."
    const val PRODUCT_NOT_FOUND = "The product with id '%s' was not found."
    const val ORDER_MIN_COUNT_IS_INVALID = "The count of products is invalid."
    const val ORDER_MIN_TOTAL_AMOUNT_IS_INVALID = "The value of total amount is invalid."
    const val FEIGN_CLIENT_ERROR = "Client error [%d]: %s"
    const val FEIGN_SERVER_ERROR = "Server error [%d]: %s"
}