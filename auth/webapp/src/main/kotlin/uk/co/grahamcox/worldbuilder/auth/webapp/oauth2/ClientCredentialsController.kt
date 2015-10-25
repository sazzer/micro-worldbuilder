package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controller for issuing Client Credentials Grant tokens
 */
@Controller
@RequestMapping(value = "/api/oauth2",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST))
class ClientCredentialsController {
    /**
     * Actually issue the Client Credentials Grant token
     */
    @RequestMapping(value = "/token",
            params = arrayOf("grant_type=client_credentials"))
    @ResponseBody
    fun token() = AccessTokenResponse("abcdef", "Bearer", 3600)
}