package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

/**
 * Representation of the Credentials of a Client making an OAuth2 request
 * @param clientId The ID of the Client
 * @param clientSecret The Secret of the Client
 */
data class ClientCredentials(val clientId: String, val clientSecret: String)