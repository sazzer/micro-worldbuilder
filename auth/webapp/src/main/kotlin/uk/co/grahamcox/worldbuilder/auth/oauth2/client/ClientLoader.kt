package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import uk.co.grahamcox.worldbuilder.auth.oauth2.client.dao.ClientDao
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

/**
 * Mechanism to load client details
 */
interface ClientLoader {
    /**
     * Actually load a client with the given ID
     * @param id The ID of the Client to load
     * @return the client details
     * @throws UnknownClientException if the client doesn't exist
     */
    fun loadClientById(id: ClientId): ClientDetails
}
