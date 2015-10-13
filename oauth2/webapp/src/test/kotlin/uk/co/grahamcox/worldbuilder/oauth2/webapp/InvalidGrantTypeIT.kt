package uk.co.grahamcox.worldbuilder.oauth2.webapp

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * Integration Test of what happens when we try to use a missing or invalid Grant Type
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = arrayOf(TestContext::class))
@TestExecutionListeners(
    DependencyInjectionTestExecutionListener::class,
    DirtiesContextTestExecutionListener::class)
@WebAppConfiguration
open class InvalidGrantTypeIT {
    @Autowired
    lateinit var mockMvc: MockMvc

    /**
     * Test requesting a token with a grant type of "incorrect".
     * This should return an HTTP 400 with an error of "unsupported_grant_type"
     */
    @Test
    fun testTokenInvalidGrantType() {
        mockMvc.perform(MockMvcRequestBuilders.post("/token")
            .param("grant_type", "incorrect")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("error").value("unsupported_grant_type"))
    }

    /**
     * Test requesting a token with no grant type at all
     * This should return an HTTP 400 with an error of "invalid_request"
     */
    @Test
    fun testTokenMissingGrantType() {
        mockMvc.perform(MockMvcRequestBuilders.post("/token")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("error").value("invalid_request"))
    }
}
