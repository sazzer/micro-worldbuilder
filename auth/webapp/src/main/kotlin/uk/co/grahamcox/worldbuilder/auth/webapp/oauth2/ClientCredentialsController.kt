package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientId
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientLoader

/**
 * Controller for issuing Client Credentials Grant tokens
 * @param loader Mechanism to load Client Details
 */
@Controller
@RequestMapping(value = "/api/oauth2",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST))
class ClientCredentialsController(private val loader : ClientLoader) {
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
        // 1. Ensure that Client Credentials have been provided
        if (clientCredentials == null) {
            throw OAuth2InvalidClientException("No client credentials were provided")
        }

        // 2. Ensure that the credentials refer to a client that is valid and allowed to request this token
        val client = loader.loadClientById(ClientId(clientCredentials.clientId))

        if (client == null || !client.secret.compare(clientCredentials.clientSecret)) {
            throw OAuth2InvalidClientException("The provided client credentials were invalid")
        }
        LOG.debug("Client: {}", client)

        // 3. Get the user details of the user to act on behalf of
        // 4. Generate the access token to use
        // 5. Return it to the client
        LOG.debug("Scopes: {}", scopes)

        return AccessTokenResponse(accessTokenValue = client.id.id,
                tokenTypeValue = "Bearer",
                expiresValue = 3600,
                scopesValue = scopes.toString())
    }
}
