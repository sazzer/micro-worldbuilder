package uk.co.grahamcox.worldbuilder.oauth2.webapp

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Controller to handle when an unsupported grant type is requested
 */
@Controller
class UnsupportedGrantController {
    /**
     * Handler for when the grant_type is present but not supported
     */
    @RequestMapping(value = "/token",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST),
        params = arrayOf("grant_type", "grant_type!=client_credentials"))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    open fun tokenUnsupportedGrant() = mapOf("error" to "unsupported_grant_type")
    /**
     * Handler for when the grant_type is not present
     */
    @RequestMapping(value = "/token",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST),
        params = arrayOf("!grant_type"))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    open fun tokenNoGrant() = mapOf("error" to "invalid_request")
}