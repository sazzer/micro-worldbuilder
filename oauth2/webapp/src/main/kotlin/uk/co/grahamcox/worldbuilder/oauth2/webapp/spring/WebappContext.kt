package uk.co.grahamcox.worldbuilder.oauth2.webapp.spring

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

/**
 * Root Configuration for the main servlet
 */
@Configuration
@Import(
    WebMvcContext::class,
    ControllersContext::class
)
@EnableGlobalMethodSecurity(prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true)
open class WebappContext {

}