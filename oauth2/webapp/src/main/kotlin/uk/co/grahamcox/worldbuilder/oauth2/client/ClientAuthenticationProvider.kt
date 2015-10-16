package uk.co.grahamcox.worldbuilder.oauth2.client

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import uk.co.grahamcox.worldbuilder.oauth2.client.dao.ClientDao

/**
 * Authentication Provider for loading Client details
 */
class ClientAuthenticationProvider(private val clientDao: ClientDao) : AuthenticationProvider {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(ClientAuthenticationProvider::class.java)

    /**
     * Actually authenticate the provided token. This must be a Username/Password Token, where the
     * Username and Password are actually going to be treated as Client ID and Client Secret
     * @param authentication The Authentication token
     * @return the successful authentication
     */
    override fun authenticate(authentication: Authentication): Authentication =
        when(authentication) {
            is UsernamePasswordAuthenticationToken -> {
                val clientId = ClientId(authentication.principal.toString())
                LOG.debug("Attempting to authenticate Client ID {}", clientId)

                val client = clientDao.getById(clientId)
                    ?: throw UsernameNotFoundException("Unknown client: ${clientId}")
                LOG.debug("Retrieved client: {}", client)

                if (!client.secret.compare(authentication.credentials.toString())) {
                    LOG.debug("Client Secret was incorrect")
                    throw BadCredentialsException("Bad Password")
                }
                LOG.debug("Client Secret matched provided password")

                val result = UsernamePasswordAuthenticationToken(authentication.principal,
                    authentication.credentials,
                    client.grantedAuthorities)
                result.details = authentication.details
                result
            }
            else -> throw IllegalArgumentException("Only UsernamePasswordAuthenticationToken is supported")
        }

    /**
     * Check if the Authentication Token that we have been provided is compatible with the one
     * that we want to use
     * @param authentication The type of Authentication Token we are going to be provided
     * @return True if this is a Username/Password Token. False if not
     */
    override fun supports(authentication: Class<*>?) =
        UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
}
