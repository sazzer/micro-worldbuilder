package uk.co.grahamcox.worldbuilder.oauth2.webapp.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.worldbuilder.oauth2.webapp.GlobalExceptionHandlers
import uk.co.grahamcox.worldbuilder.oauth2.webapp.ClientCredentialsGrantController
import uk.co.grahamcox.worldbuilder.oauth2.webapp.UnsupportedGrantController

/**
 * Context for the actual controllers
 */
@Configuration
open class ControllersContext {
    /**
     * Build the bean to manage exception handlers that span all controllers
     */
    @Bean
    open fun globalExceptionHandlers() = GlobalExceptionHandlers()

    /**
     * Build the Client Credentials Controller
     */
    @Bean
    open fun clientCredentialsController() = ClientCredentialsGrantController()

    /**
     * Build the Unsupported Grant Controller
     */
    @Bean
    open fun unsupportedGrantController() = UnsupportedGrantController()
}