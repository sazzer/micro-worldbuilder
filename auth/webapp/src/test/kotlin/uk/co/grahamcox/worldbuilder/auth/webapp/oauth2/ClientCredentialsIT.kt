package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import uk.co.grahamcox.worldbuilder.auth.webapp.IntegrationTestBase

/**
 * Integration Tests for the Client Credentials Controller
 */
open class ClientCredentialsIT : IntegrationTestBase() {

    /**
     * Test requesting a token with a grant type of "client_credentials", but no credentials are provided.
     * This should return an HTTP 401 with an error of "invalid_client"
     */
    @Test
    fun testNoClientCredentials() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/oauth2/token")
                .param("grant_type", "client_credentials")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("error").value("invalid_client"))
    }
}
