package uk.co.grahamcox.worldbuilder.auth.webapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import uk.co.grahamcox.worldbuilder.auth.spring.CoreContext
import uk.co.grahamcox.worldbuilder.auth.webapp.spring.WebappContext

/**
 * The Spring context to use for integration testing the app
 */
@Configuration
@Import(CoreContext::class, WebappContext::class)
open class TestContext {
    /** The Webapp Context */
    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    /** The MockMVC bean */
    @Bean
    open fun mockMvc() = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
}