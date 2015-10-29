package uk.co.grahamcox.worldbuilder.auth.spring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.ClientLoader
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.DaoClientLoader
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.dao.ClientDao
import uk.co.grahamcox.worldbuilder.auth.oauth2.client.dao.MockClientDao

/**
 * Spring Context for working with Clients
 */
open class ClientContext {
    /**
     * The DAO for accessing clients
     */
    @Bean
    open fun clientDao() = MockClientDao()

    /**
     * The mechanism for loading client details
     */
    @Bean
    @Autowired
    open fun clientLoader(dao: ClientDao): ClientLoader = DaoClientLoader(dao)
}
