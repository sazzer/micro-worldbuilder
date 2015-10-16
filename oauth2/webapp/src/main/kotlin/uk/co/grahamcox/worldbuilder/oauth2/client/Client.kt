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
                  val grantedAuthorities: Collection<GrantedAuthority>)
