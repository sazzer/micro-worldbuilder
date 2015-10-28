package uk.co.grahamcox.worldbuilder.auth.spring

import org.springframework.context.annotation.Bean
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientLoader

/**
 * Spring Context for working with Clients
 */
open class ClientContext {
    /**
     * The mechanism for loading client details
     */
    @Bean
    open fun clientLoader() = ClientLoader()
}
