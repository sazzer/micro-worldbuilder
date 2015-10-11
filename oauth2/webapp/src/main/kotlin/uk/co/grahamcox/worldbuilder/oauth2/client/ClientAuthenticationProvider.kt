package uk.co.grahamcox.worldbuilder.oauth2.client

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * Authentication Provider for loading Client details
 */
class ClientAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(ClientAuthenticationProvider::class.java)

    override fun retrieveUser(username: String,
                              authentication: UsernamePasswordAuthenticationToken): UserDetails {
        if (username.equals("bob")) {
            return User("bob",
                "password",
                listOf(SimpleGrantedAuthority("ROLE_USER")))
        } else {
            throw UsernameNotFoundException(username)
        }
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails,
                                                authentication: UsernamePasswordAuthenticationToken) {
        if (!userDetails.password.equals(authentication.credentials)) {
            throw BadCredentialsException("Bad Password")
        }
    }
}