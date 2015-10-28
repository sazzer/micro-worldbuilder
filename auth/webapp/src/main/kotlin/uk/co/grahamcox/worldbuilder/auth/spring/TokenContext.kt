package uk.co.grahamcox.worldbuilder.auth.spring

import io.jsonwebtoken.impl.crypto.MacProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenIssuer
import java.time.Clock

/**
 * Spring Context for working with Tokens
 */
open class TokenContext {
    /**
     * The Access Token Issuer
     */
    @Bean
    @Autowired
    fun accessTokenIssuer(clock: Clock) = AccessTokenIssuer(clock, MacProvider.generateKey())
}
