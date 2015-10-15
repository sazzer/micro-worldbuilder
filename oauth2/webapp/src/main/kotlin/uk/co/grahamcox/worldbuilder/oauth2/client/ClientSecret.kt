package uk.co.grahamcox.worldbuilder.oauth2.client

import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*


/**
 * Representation of the Client Secret to use
 * @param hash The hash of the secret
 * @param salt The salt of the secret
 */
data class ClientSecret(val hash: String, val salt: String) {
    /**
     * Compare this hashed secret to an unhashed version.
     * This will hash the provided secret using the same salt, and then compare the hashed
     * versions to each other to see if they match
     * @param secret The secret to compare
     * @return True if the secrets match. False if not
     */
    fun compare(secret: String) = equals(ClientSecret.hashSecret(secret, salt))

    companion object {
        /** The Algorithm to use for the hash */
        private val HASH_ALGORITHM = "SHA-256"
        /** The charset to use for the hash */
        private val HASH_CHARSET = Charset.forName("UTF-8")

        /**
         * Hash the given secret with the given salt
         * @param secret The secret to hash
         * @param salt The salt to hash the secret with
         * @return the hashed secret
         */
        fun hashSecret(secret: String,
                       salt: String = UUID.randomUUID().toString()) : ClientSecret {
            val preHash = "${secret}:${salt}"
            val md = MessageDigest.getInstance(HASH_ALGORITHM)
            md.update(preHash.toByteArray(HASH_CHARSET))
            val digest = md.digest()

            val hash = Base64.getEncoder().encodeToString(digest)
            return ClientSecret(hash, salt)
        }
    }
}