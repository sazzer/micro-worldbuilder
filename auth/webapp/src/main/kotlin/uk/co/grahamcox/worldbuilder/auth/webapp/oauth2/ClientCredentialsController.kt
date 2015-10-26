package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes

/**
 * Controller for issuing Client Credentials Grant tokens
 */
@Controller
@RequestMapping(value = "/api/oauth2",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST))
class ClientCredentialsController {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(ClientCredentialsController::class.java)

    /**
     * Actually issue the Client Credentials Grant token
     * @param scopes The scopes to request for the token
     * @param clientCredentials The client credentials to use
     * @return the actual Access Token
     */
    @RequestMapping(value = "/token",
            params = arrayOf("grant_type=client_credentials"))
    @ResponseBody
    fun token(@RequestParam(value = "scopes", required = false, defaultValue = "*") scopes: Scopes,
              clientCredentials: ClientCredentials?): AccessTokenResponse {
        if (clientCredentials == null) {
            throw OAuth2InvalidClientException()
        }

        LOG.debug("Client Credentials: {}", clientCredentials)
        LOG.debug("Scopes: {}", scopes)

        return AccessTokenResponse(accessTokenValue = "abcdef",
                tokenTypeValue = "Bearer",
                expiresValue = 3600,
                scopesValue = scopes.toString())
    }
}
