package uk.co.grahamcox.worldbuilder.oauth2.webapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import uk.co.grahamcox.worldbuilder.oauth2.spring.CoreContext
import uk.co.grahamcox.worldbuilder.oauth2.webapp.spring.WebappContext

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
