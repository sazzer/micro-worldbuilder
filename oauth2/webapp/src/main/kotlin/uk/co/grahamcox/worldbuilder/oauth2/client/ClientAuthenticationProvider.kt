package uk.co.grahamcox.worldbuilder.oauth2.client

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import uk.co.grahamcox.worldbuilder.oauth2.webapp.ClientCredentialsGrantController

/**
 * Authentication Provider for loading Client details
 */
class ClientAuthenticationProvider : AuthenticationProvider {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(ClientAuthenticationProvider::class.java)

    /**
     * Authenticate the provided token
     */
    override fun authenticate(authentication: Authentication): Authentication? {
        LOG.debug("Authenticating credentials: {}", authentication)

        val clientId = authentication.name
        val clientSecret = authentication.credentials.toString()

        if (clientId.equals("pete") && clientSecret.equals("password")) {
            return UsernamePasswordAuthenticationToken(clientId,
                clientSecret,
                listOf(SimpleGrantedAuthority("ROLE_USER")))
        } else {
            return AnonymousAuthenticationToken(clientId,
                clientSecret,
                listOf(SimpleGrantedAuthority("INVALID_CLIENT")))
        }
    }

    /**
     * Ensure that the authentication token to use us a Username/Password Token
     */
    override fun supports(authentication: Class<*>): Boolean {
        LOG.debug("Checking if we support authentication token: {}", authentication)
        return authentication.equals(UsernamePasswordAuthenticationToken::class.java)
    }
}