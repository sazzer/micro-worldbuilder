package uk.co.grahamcox.worldbuilder.auth.oauth2.token

import io.jsonwebtoken.impl.crypto.MacProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uk.co.grahamcox.worldbuilder.auth.oauth2.Scopes
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientDetails
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientId
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientSecret
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.UserId
import uk.co.grahamcox.worldbuilder.auth.webapp.MutableClock
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.*

/**
 * Unit tests for the Access Token Issuer
 */
class AccessTokenIssuerTest {
    /** How long the access tokens are valid for */
    private val EXPIRY_TIME = 3600L

    lateinit var testSubject: AccessTokenIssuer

    lateinit var clock: MutableClock

    /**
     * Set up the test subject
     */
    @Before
    fun setup() {
        clock = MutableClock(Date().toInstant())

        testSubject = AccessTokenIssuer(clock,
                EXPIRY_TIME,
                MacProvider.generateKey())
    }

    /**
     * Test issuing a token
     */
    @Test
    fun issueToken() {
        val user = UserId("123")
        val scopes = Scopes("a b c")

        val client = ClientDetails(id = ClientId("abc"),
                created = clock.instant(),
                name = "Test Client",
                owner = user,
                secret = ClientSecret.hash("secret"),
                updated = clock.instant())

        val accessToken = testSubject.issue(client, scopes)

        Assert.assertNotNull(accessToken.refreshToken)
        Assert.assertEquals(clock.instant().truncatedTo(ChronoUnit.SECONDS), accessToken.issued)
        Assert.assertEquals(clock.instant().truncatedTo(ChronoUnit.SECONDS).plusSeconds(EXPIRY_TIME), accessToken.expires)
        Assert.assertEquals(scopes, accessToken.scopes)
        Assert.assertEquals(user, accessToken.userId)
    }

    /**
     * Test parsing a valid token
     */
    @Test
    fun parseToken() {
        val user = UserId("123")
        val scopes = Scopes("a b c")

        val client = ClientDetails(id = ClientId("abc"),
                created = clock.instant(),
                name = "Test Client",
                owner = user,
                secret = ClientSecret.hash("secret"),
                updated = clock.instant())

        val accessTokenId = testSubject.issue(client, scopes).id

        val accessToken = testSubject.parse(accessTokenId)

        Assert.assertEquals(accessTokenId, accessToken.id)
        Assert.assertNull(accessToken.refreshToken)
        Assert.assertEquals(clock.instant().truncatedTo(ChronoUnit.SECONDS), accessToken.issued)
        Assert.assertEquals(clock.instant().truncatedTo(ChronoUnit.SECONDS).plusSeconds(EXPIRY_TIME), accessToken.expires)
        Assert.assertEquals(scopes, accessToken.scopes)
        Assert.assertEquals(user, accessToken.userId)
    }

    /**
     * Test parsing an invalid token
     */
    @Test(expected = MalformedAccessTokenException::class)
    fun parseInvalidToken() {
        testSubject.parse(AccessTokenId("invalid"))
    }

    /**
     * Test parsing a valid but expired token
     */
    @Test(expected = ExpiredAccessTokenException::class)
    fun parseExpiredToken() {
        val user = UserId("123")
        val scopes = Scopes("a b c")

        val client = ClientDetails(id = ClientId("abc"),
                created = clock.instant(),
                name = "Test Client",
                owner = user,
                secret = ClientSecret.hash("secret"),
                updated = clock.instant())

        // For the brief instant that we issue the token, roll the clock back by an hour
        // We need to do this because JJWT works out the current time itself
        clock.setInstant(clock.instant().minusSeconds(EXPIRY_TIME + 1))
        val accessTokenId = testSubject.issue(client, scopes).id
        clock.setInstant(clock.instant().plusSeconds(EXPIRY_TIME + 1))

        testSubject.parse(accessTokenId)
    }
}