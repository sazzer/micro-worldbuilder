package uk.co.grahamcox.worldbuilder.oauth2

import org.junit.Assert
import org.junit.Test

/**
 * Unit tests for the Scopes class
 */
class ScopesTest {
    /**
     * Test constructing a Scopes object from a list of scopes
     */
    @Test
    fun testConstructFromList() {
        val scopes = Scopes(listOf("a", "b", "c"))
        Assert.assertEquals(listOf("a", "b", "c"), scopes.scopes)
    }

    /**
     * Test constructing a Scopes object from a string of scopes
     */
    @Test
    fun testConstructFromString() {
        val scopes = Scopes("a b    c")
        Assert.assertEquals(listOf("a", "b", "c"), scopes.scopes)
    }

    /**
     * Test building a string from a Scopes object
     */
    @Test
    fun testToString() {
        val scopes = Scopes(listOf("a", "b", "c"))
        Assert.assertEquals("a b c", scopes.toString())
    }
}