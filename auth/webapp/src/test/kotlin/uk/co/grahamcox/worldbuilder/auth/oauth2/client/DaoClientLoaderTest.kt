package uk.co.grahamcox.worldbuilder.auth.oauth2.client

import org.easymock.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.dao.ClientDao
import java.time.Instant
import java.util.*

/**
 * Unit tests for the DAO Client Loader
 */
@RunWith(EasyMockRunner::class)
class DaoClientLoaderTest : EasyMockSupport() {
    /** The Mock DAO */
    @Mock
    lateinit var dao: ClientDao

    /** The test subject */
    lateinit var testSubject: DaoClientLoader

    /**
     * Set up the mocks
     */
    @Before
    fun setup() {
        testSubject = DaoClientLoader(dao)
    }

    /**
     * Verify all of the mocks
     */
    @After
    fun verify() {
        verifyAll()
    }

    /**
     * Test loading a known client
     */
    @Test
    fun testClientFound() {
        val id = ClientId(UUID.randomUUID().toString())
        val client = ClientDetails(id = id,
                created = Instant.EPOCH,
                updated = Instant.EPOCH,
                name = "Test",
                owner = UserId("user"),
                secret = ClientSecret.hash("password"))

        EasyMock.expect(dao.getClientById(id))
                .andReturn(client)
        replayAll()

        val loaded = testSubject.loadClientById(id)
        Assert.assertEquals(client, loaded)
    }

    /**
     * Test loading an unknown client
     */
    @Test(expected = UnknownClientException::class)
    fun testClientNotFound() {
        val id = ClientId(UUID.randomUUID().toString())
        EasyMock.expect(dao.getClientById(id))
            .andReturn(null)
        replayAll()

        testSubject.loadClientById(id)
    }
}
