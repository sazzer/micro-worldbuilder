package uk.co.grahamcox.worldbuilder.auth.oauth2.client

/**
 * Mechanism to load client details
 */
class ClientLoader {
    /**
     * Actually load a client with the given ID
     * @param id The ID of the Client to load
     * @return the client details, or null if a client wasn't found
     */
    fun loadClientById(id: ClientId) : ClientDetails? = null
}