package uk.co.grahamcox.worldbuilder.auth.oauth2.token

import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientDetails
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

/**
 * Mechanism to issue access tokens
 */
class AccessTokenIssuer {
    /**
     * Issue an Access Token suitable for the given Client, using the Owning User as the user the token applies to
     * @param client The client issuing the token
     * @param scopes The scopes of the token
     * @return the token
     */
    fun issue(client: ClientDetails, scopes: Scopes) : AccessToken = AccessToken(
            id = AccessTokenId(UUID.randomUUID().toString()),
            refreshToken = RefreshTokenId(UUID.randomUUID().toString()),
            expires = ZonedDateTime.of(2015, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC).toInstant(),
            scopes = scopes
    )
}
