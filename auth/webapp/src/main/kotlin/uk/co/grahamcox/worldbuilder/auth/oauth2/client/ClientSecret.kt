package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import java.util.*

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
            return ClientSecret(secret, salt)
        }
    }

    /**
     * Compare this secret to the provided plaintext version
     * @param secret The plaintext secret to compare against
     * @return True if the secrets match. False if not
     */
    fun compare(secret: String) = ClientSecret.hash(secret, salt).equals(this)
}