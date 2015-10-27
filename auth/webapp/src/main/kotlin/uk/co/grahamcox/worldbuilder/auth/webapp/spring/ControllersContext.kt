package uk.co.grahamcox.worldbuilder.auth.webapp.spring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientLoader
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenIssuer
import uk.co.grahamcox.worldbuilder.auth.webapp.DebugController
import uk.co.grahamcox.worldbuilder.auth.webapp.oauth2.BadTokenController
import uk.co.grahamcox.worldbuilder.auth.webapp.oauth2.ClientCredentialsController
import uk.co.grahamcox.worldbuilder.auth.webapp.oauth2.OAuth2ErrorController
import java.time.Clock

/**
 * Context for the actual controllers
 */
@Configuration
open class ControllersContext {
    /**
     * Construct the Debug Controller
     */
    @Bean
    @Autowired
    open fun debugController(clock: Clock) = DebugController(clock)

    /**
     * Construct the OAuth2 Error Controller
     */
    @Bean
    @Autowired
    open fun oauth2ErrorController() = OAuth2ErrorController()

    /**
     * Construct the Bad Token Controller
     */
    @Bean
    @Autowired
    open fun badTokenController() = BadTokenController()

    /**
     * Construct the Client Credentials Controller
     */
    @Bean
    @Autowired
    open fun clientCredentialsController(clock: Clock) = ClientCredentialsController(clock,
            ClientLoader(),
            AccessTokenIssuer())
}
