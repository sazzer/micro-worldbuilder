package uk.co.grahamcox.worldbuilder.oauth2.client

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * The representation of a Client
 * @param id The ID of the Client
 * @param secret The Secret of the Client
 * @param grantedAuthorities The Authorities granted to the Client
 */
data class Client(val id: ClientId,
                  val secret: ClientSecret,
                  val grantedAuthorities: Collection<GrantedAuthority>) : UserDetails {
    /**
     * Get the Username of the Client. This is the Client ID
     * @return the Username
     */
    override fun getUsername(): String = id.id

    /**
     * Get if the credentials are expired or not. For now this is always true
     * @return true if the credentials are not expired.
     */
    override fun isCredentialsNonExpired(): Boolean = true

    /**
     * Get if the account is expired or not. For now this is always true
     * @return true if the account is not expired.
     */
    override fun isAccountNonExpired(): Boolean = true

    /**
     * Get if the account is locked or not. For now this is always true
     * @return true if the account is not locked.
     */
    override fun isAccountNonLocked(): Boolean = true

    /**
     * Get the Authorities of the client
     * @return the authorities
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities.toMutableSet()

    /**
     * Get if the client is enabled or not. For now this is always true
     * @return true if the client is enabled
     */
    override fun isEnabled(): Boolean = true

    /**
     * Get the password of the account. For now this returns the hash, which is meaningless
     * @return the hashed password
     */
    override fun getPassword(): String = secret.hash
}
