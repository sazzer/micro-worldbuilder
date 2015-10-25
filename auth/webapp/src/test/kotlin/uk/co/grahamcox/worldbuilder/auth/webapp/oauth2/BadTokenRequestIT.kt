package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import uk.co.grahamcox.worldbuilder.auth.webapp.IntegrationTestBase

/**
 * Integration Tests for the Debug Controller
 */
open class BadTokenRequestIT : IntegrationTestBase() {

    /**
     * Test requesting a token with a grant type of "incorrect".
     * This should return an HTTP 400 with an error of "unsupported_grant_type"
     */
    @Test
    fun testTokenInvalidGrantType() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/oauth2/token")
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
        mockMvc.perform(MockMvcRequestBuilders.post("/api/oauth2/token")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("error").value("invalid_request"))
    }
}