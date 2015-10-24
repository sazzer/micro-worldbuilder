package uk.co.grahamcox.worldbuilder.auth.webapp

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc

/**
 * Base class for integration testing
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ActiveProfiles("test")
@ContextConfiguration(classes = arrayOf(TestContext::class))
@TestExecutionListeners(
    DependencyInjectionTestExecutionListener::class,
    DirtiesContextTestExecutionListener::class)
@WebAppConfiguration
open class IntegrationTestBase {
    @Autowired
    lateinit var mockMvc: MockMvc

}