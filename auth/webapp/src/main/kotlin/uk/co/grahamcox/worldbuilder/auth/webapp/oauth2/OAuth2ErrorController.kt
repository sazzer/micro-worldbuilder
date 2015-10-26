package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Controller Advice for handling OAuth2 Errors
 */
@ControllerAdvice
class OAuth2ErrorController {
    /**
     * Handle the OAuth2 Exceptions that might occur
     * @param e The exception to handle
     */
    @ExceptionHandler(OAuth2Exception::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleOAuth2Error(e : OAuth2Exception) = mapOf("error" to e.errorCode,
            "message" to e.message)
}
