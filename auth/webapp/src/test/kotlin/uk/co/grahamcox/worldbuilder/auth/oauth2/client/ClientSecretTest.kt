package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import org.junit.Assert
import org.junit.Test

/**
 * Unit Tests for the Client Secret
 */
class ClientSecretTest {
    /**
     * Test constructing the secret from a hash and salt
     */
    @Test
    fun testConstructHashedSecret() {
        val secret = ClientSecret(hash = "secret", salt = "salt")
        Assert.assertEquals("secret", secret.hash)
        Assert.assertEquals("salt", secret.salt)
    }

    /**
     * Test hashing a plaintext secret without providing salt
     */
    @Test
    fun testHashSecretWithoutSalt() {
        val secret = ClientSecret.hash("password")
        Assert.assertNotEquals("secret", secret.hash)
        Assert.assertNotEquals("salt", secret.salt)
    }

    /**
     * Test hashing a plaintext secret whilst providing salt
     */
    @Test
    fun testHashSecretWithSalt() {
        val secret = ClientSecret.hash("password", "salt")
        Assert.assertEquals("E2Ab2k6njlWge5iGbSvmvgdE44ZvE8AMgRyrYIoo8yI=", secret.hash)
        Assert.assertEquals("salt", secret.salt)
    }

    @Test
    fun testCompareSecrets() {
        val secret = ClientSecret.hash("password", "salt")

        Assert.assertTrue(secret.compare("password"))
        Assert.assertFalse(secret.compare("wrong"))
        Assert.assertFalse(secret.compare("salt"))
        Assert.assertFalse(secret.compare("Password"))
    }
}
