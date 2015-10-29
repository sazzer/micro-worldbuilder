package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import uk.co.grahamcox.worldbuilder.auth.oauth2.client.dao.ClientDao

/**
 * Mechanism to load client details
 * @param dao The Client DAO to use
 */
class DaoClientLoader(private val dao: ClientDao) : ClientLoader {
    /**
     * Actually load a client with the given ID
     * @param id The ID of the Client to load
     * @return the client details
     * @throws UnknownClientException if the client doesn't exist
     */
    override fun loadClientById(id: ClientId) =
            dao.getClientById(id) ?: throw UnknownClientException(id)
}
