package uk.co.grahamcox.worldbuilder.auth.oauth2.token

import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes
import java.time.Instant

/**
 * Representation of an actual Access Token
 * @param id The ID of the Access Token
 * @param refreshToken The Refresh Token for the Access Token
 * @param expires When the Access Token expires
 * @param scopes The scopes of the Access Token
 */
data class AccessToken(val id: AccessTokenId,
                       val refreshToken: RefreshTokenId?,
                       val expires: Instant,
                       val scopes: Scopes)
