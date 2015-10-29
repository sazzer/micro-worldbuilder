package uk.co.grahamcox.worldbuilder.auth.oauth2.client.dao

import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientDetails
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientId
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientSecret
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.UserId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

/**
 * The DAO used for loading and saving Client data
 */
interface ClientDao {
    /**
     * Load the Client that has the given Client ID
     * @param id The ID of the Client
     * @return the Client details, or null if they didn't exist
     */
    fun getClientById(id: ClientId) : ClientDetails?
}

class MockClientDao : ClientDao {
    /**
     * Load the Client that has the given Client ID
     * @param id The ID of the Client
     * @return the Client details, or null if they didn't exist
     */
    override fun getClientById(id: ClientId) : ClientDetails? = when (id) {
        ClientId("graham") -> {
            ClientDetails(id = id,
                    created = ZonedDateTime.of(2015, 10, 27, 12, 18, 0, 0, ZoneOffset.UTC).toInstant(),
                    updated = ZonedDateTime.of(2015, 10, 27, 12, 18, 0, 0, ZoneOffset.UTC).toInstant(),
                    secret = ClientSecret.hash("password"),
                    name = "Mock Client",
                    owner = UserId(UUID.randomUUID().toString()))
        }
        else -> null
    }
}
