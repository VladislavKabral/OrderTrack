package by.kabral.ordertrack.productservice.rest.advice

import by.kabral.ordertrack.dto.ErrorResponseDto
import by.kabral.ordertrack.exception.BusinessException
import by.kabral.ordertrack.exception.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.ZonedDateTime

@ControllerAdvice
class ExceptionResolver {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException) : ResponseEntity<ErrorResponseDto> {
        return ResponseEntity(ErrorResponseDto(message = ex.message!!, timestamp = ZonedDateTime.now()),
            HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException) : ResponseEntity<ErrorResponseDto> {
        return ResponseEntity(ErrorResponseDto(message = ex.message!!, timestamp = ZonedDateTime.now()),
            HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleMessageNotReadableException(ex: HttpMessageNotReadableException) : ResponseEntity<ErrorResponseDto> {
        return ResponseEntity(ErrorResponseDto(message = ex.message!!, timestamp = ZonedDateTime.now()),
            HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException) : ResponseEntity<ErrorResponseDto> {
        val message = StringBuilder()
        ex.bindingResult.fieldErrors.forEach { error ->  message.append(error.defaultMessage)}

        return ResponseEntity(ErrorResponseDto(message = message.toString(), timestamp = ZonedDateTime.now()),
            HttpStatus.BAD_REQUEST)
    }
}