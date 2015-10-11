package uk.co.grahamcox.worldbuilder.oauth2.spring

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.time.Clock

/**
 * Root Configuration for the whole application
 */
@Configuration
@Import(
    SecurityContext::class
)
open class CoreContext {
    /**
     * Configure the clock to use
     */
    @Bean
    open fun clock() = Clock.systemUTC()

    /**
     * Construct the Jackson Object Mapper to use
     * @return the Jackson Object Mapper
     */
    @Bean
    open fun objectMapper() : ObjectMapper {
        val objectMapper = ObjectMapper();
        objectMapper.registerModule(JavaTimeModule());
        objectMapper.registerModule(Jdk8Module());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
        return objectMapper;
    }
}