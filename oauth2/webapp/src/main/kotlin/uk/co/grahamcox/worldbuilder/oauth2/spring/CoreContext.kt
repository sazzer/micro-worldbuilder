package uk.co.grahamcox.worldbuilder.oauth2.spring

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * Root Configuration for the whole application
 */
@Configuration
@Import(
    SecurityContext::class
)
open class CoreContext {

}