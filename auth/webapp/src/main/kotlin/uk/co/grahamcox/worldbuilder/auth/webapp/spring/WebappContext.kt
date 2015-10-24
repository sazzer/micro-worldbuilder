package uk.co.grahamcox.worldbuilder.auth.webapp.spring

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * The root of the main servlet context
 */
@Configuration
@Import(
    WebMvcContext::class,
    ControllersContext::class
)
open class WebappContext {

}