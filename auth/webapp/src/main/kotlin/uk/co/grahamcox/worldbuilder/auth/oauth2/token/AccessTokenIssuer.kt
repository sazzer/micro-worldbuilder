package uk.co.grahamcox.worldbuilder.auth.oauth2.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.crypto.MacProvider
import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientDetails
import java.time.Clock
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*
import javax.crypto.SecretKey

/**
 * Mechanism to issue access tokens
 * @param clock The clock to use
 * @param key The secret key to use for signing the tokens
 */
class AccessTokenIssuer(private val clock: Clock,
                        private val key: SecretKey) {

    /**
     * Issue an Access Token suitable for the given Client, using the Owning User as the user the token applies to
     * @param client The client issuing the token
     * @param scopes The scopes of the token
     * @return the token
     */
    fun issue(client: ClientDetails, scopes: Scopes) : AccessToken {
        val tokenId = UUID.randomUUID().toString()
        val issuedAt = clock.instant()
        val expiresAt = issuedAt.plusSeconds(3600)

        val accessTokenId = Jwts.builder()
                .setIssuer(AccessTokenIssuer::class.qualifiedName)
                .setSubject(client.owner.id)
                .setAudience(client.id.id)
                .setExpiration(Date.from(expiresAt))
                .setNotBefore(Date.from(issuedAt))
                .setIssuedAt(Date.from(issuedAt))
                .setId(tokenId)
                .claim("scopes", scopes.scopes)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact()

        return AccessToken(
                id = AccessTokenId(accessTokenId),
                refreshToken = RefreshTokenId(UUID.randomUUID().toString()),
                expires = expiresAt,
                scopes = scopes
        )
    }
}
