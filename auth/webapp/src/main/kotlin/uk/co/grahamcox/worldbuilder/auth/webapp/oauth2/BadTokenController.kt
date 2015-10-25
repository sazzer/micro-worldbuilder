package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Controller for handling Token requests that are invalid
 */
@Controller
@RequestMapping(value = "/api/oauth2",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST))
class BadTokenController {
    /**
     * Handle when no grant type was provided
     */
    @RequestMapping(value = "/token",
            params = arrayOf("!grant_type"))
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun noGrantType() = ErrorResponse("invalid_request")

    /**
     * Handle when an unknown grant type was provided
     */
    @RequestMapping(value = "/token",
            params = arrayOf("grant_type",
                    "grant_type!=client_credentials"))
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun unknownGrantType() = ErrorResponse("unsupported_grant_type")
}