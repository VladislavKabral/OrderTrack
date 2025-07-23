package by.kabral.ordertrack.orderservice.client

import by.kabral.ordertrack.exception.FeignRequestException
import by.kabral.ordertrack.orderservice.util.Constant.CLIENT_ERROR_MAX_STATUS
import by.kabral.ordertrack.orderservice.util.Constant.CLIENT_ERROR_MIN_STATUS
import by.kabral.ordertrack.orderservice.util.Constant.MESSAGE_NODE_NAME
import by.kabral.ordertrack.orderservice.util.Constant.SERVER_ERROR_MAX_STATUS
import by.kabral.ordertrack.orderservice.util.Constant.SERVER_ERROR_MIN_STATUS
import by.kabral.ordertrack.orderservice.util.Message.FEIGN_CLIENT_ERROR
import by.kabral.ordertrack.orderservice.util.Message.FEIGN_SERVER_ERROR
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception? {
        val body = response.body().asInputStream().bufferedReader(Charsets.UTF_8).readText()
        val status = response.status()

        val mapper = jacksonObjectMapper()
        val node = mapper.readTree(body)
        val message = node.get(MESSAGE_NODE_NAME).asText()

        val defaultDecoder = ErrorDecoder.Default()
        return when (status) {
            in CLIENT_ERROR_MIN_STATUS..CLIENT_ERROR_MAX_STATUS ->
                FeignRequestException(String.format(FEIGN_CLIENT_ERROR, status, message))
            in SERVER_ERROR_MIN_STATUS..SERVER_ERROR_MAX_STATUS ->
                FeignRequestException(String.format(FEIGN_SERVER_ERROR, status, body))
            else -> defaultDecoder.decode(methodKey, response)
        }
    }
}