package uk.co.grahamcox.worldbuilder.auth.webapp

import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * Integration Tests for the Debug Controller
 */
open class DebugControllerIT : IntegrationTestBase() {
    /**
     * Test getting the current time
     */
    @Test
    open fun testNow() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/debug/now"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.content().string("2015-10-24T22:38:00Z"))
    }
}