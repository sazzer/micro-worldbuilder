package uk.co.grahamcox.worldbuilder.auth.oauth2.token

import io.jsonwebtoken.*
import io.jsonwebtoken.impl.crypto.MacProvider
import org.slf4j.LoggerFactory
import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientDetails
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientId
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.UserId
import java.time.Clock
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.SecretKey

/**
 * Mechanism to issue access tokens
 * @param clock The clock to use
 * @param expiryTime How long until access tokens expire
 * @param key The secret key to use for signing the tokens
 */
class AccessTokenIssuer(private val clock: Clock,
                        private val expiryTime: Long,
                        private val key: SecretKey) {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(AccessTokenIssuer::class.java)
    /**
     * Issue an Access Token suitable for the given Client, using the Owning User as the user the token applies to
     * @param client The client issuing the token
     * @param scopes The scopes of the token
     * @return the token
     */
    fun issue(client: ClientDetails, scopes: Scopes) : AccessToken {
        val tokenId = UUID.randomUUID().toString()
        val issuedAt = clock.instant().truncatedTo(ChronoUnit.SECONDS)
        val expiresAt = issuedAt.plusSeconds(expiryTime)
        LOG.debug("Issuing an access token for a client. Client={}, Expiry={}", client, expiresAt)

        val accessTokenId = Jwts.builder()
                .setIssuer(AccessTokenIssuer::class.qualifiedName)
                .setSubject(client.owner.id)
                .setAudience(client.id.id)
                .setExpiration(Date.from(expiresAt))
                .setNotBefore(Date.from(issuedAt))
                .setIssuedAt(Date.from(issuedAt))
                .setId(tokenId)
                .claim("scopes", scopes.toString())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact()

        return AccessToken(
                id = AccessTokenId(accessTokenId),
                clientId = client.id,
                refreshToken = RefreshTokenId(UUID.randomUUID().toString()),
                issued = issuedAt,
                expires = expiresAt,
                scopes = scopes,
                userId = client.owner
        )
    }

    /**
     * Rebuild an Access Token from the provided ID.
     * Note that it is not possible to get the Refresh Token back this way
     * @param accessToken The Access Token to parse
     * @return the token
     */
    fun parse(accessToken: AccessTokenId) : AccessToken {
        try {
            LOG.debug("Parsing an access token: {}", accessToken)
            val jwt = Jwts.parser()
                    .setSigningKey(key)
                    .requireIssuer(AccessTokenIssuer::class.qualifiedName)
                    .parseClaimsJws(accessToken.id)

            val user = UserId(jwt.body.subject)
            val expiresAt = jwt.body.expiration.toInstant()
            val issuedAt = jwt.body.issuedAt.toInstant()
            val scopes = Scopes(jwt.body.get("scopes", String::class.java))
            val clientId = ClientId(jwt.body.audience)

            return AccessToken(
                    id = accessToken,
                    clientId = clientId,
                    refreshToken = null,
                    expires = expiresAt,
                    issued = issuedAt,
                    scopes = scopes,
                    userId = user
            )
        } catch (e: MalformedJwtException) {
            LOG.warn("Access token was malformed: {}", accessToken, e)
            throw MalformedAccessTokenException()
        } catch (e: SignatureException) {
            LOG.warn("Signature was invalid: {}", accessToken, e)
            throw MalformedAccessTokenException()
        } catch (e: UnsupportedJwtException) {
            LOG.warn("Access token was unsupported: {}", accessToken, e)
            throw MalformedAccessTokenException()
        } catch (e: ExpiredJwtException) {
            LOG.warn("Access token was expired: {}", accessToken, e)
            throw ExpiredAccessTokenException()
        }
    }
}
