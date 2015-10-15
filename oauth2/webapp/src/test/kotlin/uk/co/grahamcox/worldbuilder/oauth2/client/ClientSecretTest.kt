package uk.co.grahamcox.worldbuilder.oauth2.client

import org.junit.Assert
import org.junit.Test

class ClientSecretTest {
    /**
     * Test hashing a secret with a provided salt
     */
    @Test
    fun testHashSecretWithSalt() {
        val clientSecret = ClientSecret.hashSecret("secret", "salt")
        Assert.assertEquals("salt", clientSecret.salt)
        Assert.assertNotEquals("secret", clientSecret.hash)
    }

    /**
     * Test hashing a secret with a generated salt
     */
    @Test
    fun testHashSecretWithoutSalt() {
        val clientSecret = ClientSecret.hashSecret("secret")
        val clientSecret2 = ClientSecret.hashSecret("secret")

        Assert.assertNotEquals("secret", clientSecret.hash)

        Assert.assertNotEquals(clientSecret.salt, clientSecret2.salt)
        Assert.assertNotEquals(clientSecret.hash, clientSecret2.hash)
    }

    /**
     * Test comparing a hashed secret to an unhashed one
     */
    @Test
    fun testCompare() {
        val clientSecret = ClientSecret.hashSecret("secret")

        Assert.assertTrue(clientSecret.compare("secret"))
        Assert.assertFalse(clientSecret.compare("Secret"))
        Assert.assertFalse(clientSecret.compare("secret1"))
        Assert.assertFalse(clientSecret.compare("different"))
    }
}