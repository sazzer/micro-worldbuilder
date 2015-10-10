package uk.co.grahamcox.worldbuilder.oauth2.webapp.spring

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * Root Configuration for the main servlet
 */
@Configuration
@Import(
    WebMvcContext::class,
    ControllersContext::class
)
open class WebappContext {

}