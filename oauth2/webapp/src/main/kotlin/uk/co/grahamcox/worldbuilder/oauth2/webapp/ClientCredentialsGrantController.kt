package uk.co.grahamcox.worldbuilder.oauth2.webapp

import org.slf4j.LoggerFactory
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.worldbuilder.oauth2.Scopes

/**
 * Spring Controller to handle the Client Credentials Grant of the OAuth2 API
 */
@Controller
open class ClientCredentialsGrantController {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(ClientCredentialsGrantController::class.java)

    /**
     * Handler for the Client Credentials Grant
     * @param scopes the scopes to request for the grant
     * @return the access token
     */
    @RequestMapping(value = "/token",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST),
        params = arrayOf("grant_type=client_credentials"))
    @ResponseBody
    @Secured("ROLE_USER")
    open fun token(@RequestParam(value = "scopes", required = false, defaultValue = "*") scopes: Scopes) : AccessTokenResponse {
        LOG.debug("Performing Client Credentials grant for scopes {} and client {}",
            scopes,
            SecurityContextHolder.getContext())

        return AccessTokenResponse(token = "abcdef",
            refreshToken = "123456",
            expires = 3600,
            scope = scopes.toString(),
            type = "Bearer")
    }
}