package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import java.security.MessageDigest
import java.util.*

/** The hash algorithm to use */
private val HASH_ALGORITHM = "SHA-256"

/** The charset to use for the hashed password */
private val CHARSET = Charsets.UTF_8

/**
 * Representation of a hashed secret
 * @param hash The hash of the secret
 * @param salt The salt that the hash was producd with
 */
data class ClientSecret(val hash: String, val salt: String) {
    companion object {
        /**
         * Hash a plaintext secret
         * @param secret The plaintext secret
         * @param salt The salt to use
         * @return the hashed secret
         */
        fun hash(secret: String, salt: String = UUID.randomUUID().toString()): ClientSecret {
            val md = MessageDigest.getInstance(HASH_ALGORITHM)
            md.update(salt.toByteArray(CHARSET))
            md.update(secret.toByteArray(CHARSET))
            val hash = md.digest()

            val encodedHash = Base64.getEncoder().encodeToString(hash)
            return ClientSecret(encodedHash, salt)
        }
    }

    /**
     * Compare this secret to the provided plaintext version
     * @param secret The plaintext secret to compare against
     * @return True if the secrets match. False if not
     */
    fun compare(secret: String) = ClientSecret.hash(secret, salt).equals(this)
}
