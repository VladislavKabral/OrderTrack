package by.kabral.ordertrack.customerservice.dto

import by.kabral.ordertrack.customerservice.util.Constant.USER_FIRSTNAME_MAX_LENGTH
import by.kabral.ordertrack.customerservice.util.Constant.USER_FIRSTNAME_MIN_LENGTH
import by.kabral.ordertrack.customerservice.util.Constant.USER_LASTNAME_MAX_LENGTH
import by.kabral.ordertrack.customerservice.util.Constant.USER_LASTNAME_MIN_LENGTH
import by.kabral.ordertrack.customerservice.util.Constant.USER_LASTNAME_REGEXP
import by.kabral.ordertrack.customerservice.util.Constant.USER_FIRSTNAME_REGEXP
import by.kabral.ordertrack.customerservice.util.Message.USER_EMAIL_FORMAT_IS_WRONG
import by.kabral.ordertrack.customerservice.util.Message.USER_EMAIL_IS_EMPTY
import by.kabral.ordertrack.customerservice.util.Message.USER_FIRSTNAME_FORMAT_IS_WRONG
import by.kabral.ordertrack.customerservice.util.Message.USER_FIRSTNAME_IS_EMPTY
import by.kabral.ordertrack.customerservice.util.Message.USER_FIRSTNAME_SIZE_IS_INVALID
import by.kabral.ordertrack.customerservice.util.Message.USER_LASTNAME_FORMAT_IS_WRONG
import by.kabral.ordertrack.customerservice.util.Message.USER_LASTNAME_IS_EMPTY
import by.kabral.ordertrack.customerservice.util.Message.USER_LASTNAME_SIZE_IS_INVALID
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.util.*

data class CustomerDto(
    var id: UUID? = null,

    @field:NotBlank(message = USER_LASTNAME_IS_EMPTY)
    @field:Size(min = USER_LASTNAME_MIN_LENGTH, max = USER_LASTNAME_MAX_LENGTH, message = USER_LASTNAME_SIZE_IS_INVALID)
    @field:Pattern(regexp = USER_LASTNAME_REGEXP, message = USER_LASTNAME_FORMAT_IS_WRONG)
    var lastname: String? = null,

    @field:NotBlank(message = USER_FIRSTNAME_IS_EMPTY)
    @field:Size(min = USER_FIRSTNAME_MIN_LENGTH, max = USER_FIRSTNAME_MAX_LENGTH, message = USER_FIRSTNAME_SIZE_IS_INVALID)
    @field:Pattern(regexp = USER_FIRSTNAME_REGEXP, message = USER_FIRSTNAME_FORMAT_IS_WRONG)
    var firstname: String? = null,

    @field:NotBlank(message = USER_EMAIL_IS_EMPTY)
    @field:Email(message = USER_EMAIL_FORMAT_IS_WRONG)
    var email: String? = null,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String? = null
)
