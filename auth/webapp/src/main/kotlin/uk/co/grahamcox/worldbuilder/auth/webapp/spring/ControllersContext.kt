package uk.co.grahamcox.worldbuilder.auth.webapp.spring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.worldbuilder.auth.webapp.DebugController
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
}