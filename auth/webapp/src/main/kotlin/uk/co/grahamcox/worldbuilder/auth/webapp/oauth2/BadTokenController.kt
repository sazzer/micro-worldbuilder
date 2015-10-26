package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

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
    fun noGrantType() {
        throw OAuth2InvalidRequestException("No grant_type was specified")
    }

    /**
     * Handle when an unknown grant type was provided
     */
    @RequestMapping(value = "/token",
            params = arrayOf("grant_type",
                    "grant_type!=client_credentials"))
    fun unknownGrantType(@RequestParam("grant_type") grantType: String) {
        throw OAuth2UnsupportedGrantTypeException(grantType)
    }
}
