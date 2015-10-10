package uk.co.grahamcox.worldbuilder.oauth2.webapp.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.worldbuilder.oauth2.webapp.ClientCredentialsGrantController

/**
 * Context for the actual controllers
 */
@Configuration
open class ControllersContext {
    /**
     * Build the Client Credentials Controller
     */
    @Bean
    open fun clientCredentialsController() = ClientCredentialsGrantController()
}