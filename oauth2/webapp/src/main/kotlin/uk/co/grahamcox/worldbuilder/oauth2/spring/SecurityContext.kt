package uk.co.grahamcox.worldbuilder.oauth2.spring

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Configuration for Spring Security
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true)
open class SecurityContext : WebSecurityConfigurerAdapter() {

    /**
     * Configure the Authentication Manager
     * @param auth the authentication manager builder to configure
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .inMemoryAuthentication()
                .withUser("bob").password("password").roles("USER")
    }

    /**
     * Configure the HTTP Security
     * @param http the HTTP Security to configure
     */
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic()
            .and().antMatcher("/**")
    }

    /**
     * Expose the Authentication Manager as a Bean
     */
    @Bean
    override fun authenticationManager(): AuthenticationManager? {
        return super.authenticationManager()
    }
}