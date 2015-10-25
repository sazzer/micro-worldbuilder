package uk.co.grahamcox.worldbuilder.auth.oauth2

import org.junit.Assert
import org.junit.Test

/**
 * Unit Tests for the Scopes class
 */
class ScopesTest {
    /**
     * Test constructing with a set works correctly
     */
    @Test
    fun testConstructSet() {
        val scopes = Scopes(setOf("a", "b", "c"))
        Assert.assertEquals(3, scopes.scopes.size)
        Assert.assertTrue(scopes.scopes.contains("a"))
        Assert.assertTrue(scopes.scopes.contains("b"))
        Assert.assertTrue(scopes.scopes.contains("c"))
    }

    /**
     * Test constructing with a string works correctly
     */
    @Test
    fun testConstructString() {
        val scopes = Scopes("a b\tc     d")
        Assert.assertEquals(4, scopes.scopes.size)
        Assert.assertTrue(scopes.scopes.contains("a"))
        Assert.assertTrue(scopes.scopes.contains("b"))
        Assert.assertTrue(scopes.scopes.contains("c"))
        Assert.assertTrue(scopes.scopes.contains("d"))
    }

    /**
     * Test converting to a string works correctly
     */
    @Test
    fun testToString() {
        val scopes = Scopes("c b a")
        Assert.assertEquals("a b c", scopes.toString())
    }
}