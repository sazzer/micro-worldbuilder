package uk.co.grahamcox.worldbuilder.oauth2.webapp.spring

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Configuration for the Spring Web MVC component
 */
@Configuration
@EnableWebMvc
open class WebMvcContext : WebMvcConfigurerAdapter() {

}