package uk.co.grahamcox.worldbuilder.oauth2.spring

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import uk.co.grahamcox.worldbuilder.oauth2.client.ClientAuthenticationProvider
import uk.co.grahamcox.worldbuilder.oauth2.client.dao.ClientDao
import uk.co.grahamcox.worldbuilder.oauth2.webapp.OAuth2AuthenticationEntryPoint

/**
 * Configuration for Spring Security
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true)
open class SecurityContext : WebSecurityConfigurerAdapter() {
    /** The Object Mapper to use */
    @Autowired
    lateinit var objectMapper : ObjectMapper

    /**
     * Configure the Authentication Manager
     * @param auth the authentication manager builder to configure
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .authenticationProvider(ClientAuthenticationProvider(ClientDao()))
    }

    /**
     * Configure the HTTP Security
     * @param http the HTTP Security to configure
     */
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic()
                .authenticationEntryPoint(OAuth2AuthenticationEntryPoint(objectMapper))
    }

    /**
     * Expose the Authentication Manager as a Bean
     */
    @Bean
    override fun authenticationManager(): AuthenticationManager? {
        return super.authenticationManager()
    }
}
