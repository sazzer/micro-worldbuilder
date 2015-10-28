package uk.co.grahamcox.worldbuilder.auth.oauth2.token

import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.UserId
import java.time.Instant

/**
 * Representation of an actual Access Token
 * @param id The ID of the Access Token
 * @param refreshToken The Refresh Token for the Access Token
 * @param issued When the Access Token was issued
 * @param expires When the Access Token expires
 * @param scopes The scopes of the Access Token
 * @param userId The User ID that the Access Token is for
 */
data class AccessToken(val id: AccessTokenId,
                       val refreshToken: RefreshTokenId?,
                       val issued: Instant,
                       val expires: Instant,
                       val scopes: Scopes,
                       val userId: UserId)
