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
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenIssuer
import java.time.Clock
import java.time.Duration

/**
 * Controller for issuing Client Credentials Grant tokens
 * @param clock The clock to use
 * @param loader Mechanism to load Client Details
 * @param accessTokenIssuer Mechanism to issue Access Tokens
 */
@Controller
@RequestMapping(value = "/api/oauth2",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST))
class ClientCredentialsController(private val clock: Clock,
                                  private val loader : ClientLoader,
                                  private val accessTokenIssuer: AccessTokenIssuer) {
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

        // 3. Generate the access token to use
        val accessToken = accessTokenIssuer.issue(client, scopes)

        // 4. Return it to the client
        val now = clock.instant()
        val expiryTime = Duration.between(now, accessToken.expires)

        return AccessTokenResponse(accessTokenValue = accessToken.id.id,
                tokenTypeValue = "Bearer",
                expiresValue = expiryTime.seconds,
                scopesValue = accessToken.scopes.toString())
    }
}
