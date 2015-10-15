package uk.co.grahamcox.worldbuilder.oauth2.client.dao

import org.springframework.security.core.authority.SimpleGrantedAuthority
import uk.co.grahamcox.worldbuilder.oauth2.client.Client
import uk.co.grahamcox.worldbuilder.oauth2.client.ClientId
import uk.co.grahamcox.worldbuilder.oauth2.client.ClientSecret

/**
 * DAO for working with Client details
 */
class ClientDao {
    /**
     * Get the Client with the given Client ID
     * @param id The ID of the Client to get
     * @return the Client if found, or null if one wasn't found
     */
    fun getById(id: ClientId): Client? = when(id) {
        ClientId("bob") -> Client(id = id,
                secret = ClientSecret.hashSecret(secret = "password"),
                grantedAuthorities = listOf(SimpleGrantedAuthority("ROLE_USER")))
        else -> null
    }
}
