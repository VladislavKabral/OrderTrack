package by.kabral.ordertrack.customerservice.rest.advice

import by.kabral.ordertrack.customerservice.dto.ErrorResponseDto
import by.kabral.ordertrack.customerservice.exception.BusinessException
import by.kabral.ordertrack.customerservice.exception.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime

@RestControllerAdvice
class ExceptionResolver {

    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ErrorResponseDto? {
        return e.message?.let { ErrorResponseDto(it, ZonedDateTime.now(UTC)) }
    }

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBusinessException(e: BusinessException): ErrorResponseDto? {
        return e.message?.let { ErrorResponseDto(it, ZonedDateTime.now(UTC)) }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(e: MethodArgumentNotValidException): ErrorResponseDto? {
        val messageBuilder = StringBuilder()
        for (fieldError in e.bindingResult.fieldErrors) {
            messageBuilder.append(fieldError.defaultMessage)
        }
        return ErrorResponseDto(messageBuilder.toString(), ZonedDateTime.now(UTC))
    }
}