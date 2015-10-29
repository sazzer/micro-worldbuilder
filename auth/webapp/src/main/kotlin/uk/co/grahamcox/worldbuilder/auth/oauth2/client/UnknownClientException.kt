package uk.co.grahamcox.worldbuilder.auth.oauth2.client

/**
 * Exception thrown when the client that was to be loaded didn't exist
 * @param id The ID of the client
 */
class UnknownClientException(val id: ClientId) : Exception()
