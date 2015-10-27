package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

/**
 * Mechanism to load client details
 */
class ClientLoader {
    /**
     * Actually load a client with the given ID
     * @param id The ID of the Client to load
     * @return the client details, or null if a client wasn't found
     */
    fun loadClientById(id: ClientId) : ClientDetails? = when (id) {
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
