package by.kabral.ordertrack.productservice.util

object Constant {
    const val PRODUCT_NAME_MIN_LENGTH = 3
    const val PRODUCT_NAME_MAX_LENGTH = 50
    const val PRODUCT_DESCRIPTION_MIN_LENGTH = 4
    const val PRODUCT_DESCRIPTION_MAX_LENGTH = 255

    const val PRODUCT_NAME_REGEXP = "^[a-zA-Z0-9- ]+$"
    const val PRODUCT_DESCRIPTION_REGEXP = "^[a-zA-Z0-9-(),.!? ]+$"
}