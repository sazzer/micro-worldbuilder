package uk.co.grahamcox.worldbuilder.oauth2.webapp

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Exception Handlers that span all of the controllers
 */
@ControllerAdvice
open class GlobalExceptionHandlers {
    /**
     * Handle when the Spring Security AccessDeniedException is raised
     */
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    open fun accessDenied() = mapOf("error" to "unauthorized_client")

}