package uk.co.grahamcox.worldbuilder.auth.spring

import io.jsonwebtoken.impl.crypto.MacProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenIssuer
import java.time.Clock
import javax.crypto.SecretKey

/**
 * Spring Context for working with Tokens
 */
open class TokenContext {
    /**
     * The secret key used for signing Access Tokens
     */
    @Bean
    fun accessTokenKey() = MacProvider.generateKey()

    /**
     * The Access Token Issuer
     */
    @Bean
    @Autowired
    fun accessTokenIssuer(clock: Clock, key: SecretKey) = AccessTokenIssuer(clock, key)
}
