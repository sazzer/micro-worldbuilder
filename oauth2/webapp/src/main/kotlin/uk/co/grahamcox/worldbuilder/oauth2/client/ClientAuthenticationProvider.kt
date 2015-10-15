package uk.co.grahamcox.worldbuilder.oauth2.client

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import uk.co.grahamcox.worldbuilder.oauth2.client.dao.ClientDao

/**
 * Authentication Provider for loading Client details
 */
class ClientAuthenticationProvider(private val clientDao: ClientDao) : AbstractUserDetailsAuthenticationProvider() {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(ClientAuthenticationProvider::class.java)

    /**
     * Retrieve the Client that has a ClientID of the given Username
     * @param username The username to retrieve
     * @param authentication The authentication token. Not used
     * @return the client
     */
    override fun retrieveUser(username: String,
                              authentication: UsernamePasswordAuthenticationToken): UserDetails =
            clientDao.getById(ClientId(username)) ?: throw UsernameNotFoundException("Unknown client: ${username}")

    /**
     * Actually check if the password we've got matches the password of the client
     * @param userDetails The user details to compare
     * @param authentication The authentication token to compare against
     * @throws InternalAuthenticationServiceException if the user details are not a Client
     * @throws BadCredentialsException if the Client Secret is wrong
     */
    override fun additionalAuthenticationChecks(userDetails: UserDetails,
                                                authentication: UsernamePasswordAuthenticationToken) {
        when (userDetails) {
            is Client -> {
                if (!userDetails.secret.compare(authentication.credentials.toString())) {
                    throw BadCredentialsException("Bad Password")
                }
            }
            else -> throw InternalAuthenticationServiceException("User Details were not of the correct type")
        }
    }
}
