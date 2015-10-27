package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import java.time.Instant

/**
 * Representation of the details of an OAuth2 Client
 * @param id The Client ID
 * @param created When the Client was created
 * @param updated When the Client was last modified
 * @param secret The secret for the client
 * @param name The name of the client
 * @param owner The User that owns the Client Details
 */
class ClientDetails(val id: ClientId,
                    val created: Instant,
                    val updated: Instant,
                    val secret: ClientSecret,
                    val name: String,
                    val owner: UserId) {

}
