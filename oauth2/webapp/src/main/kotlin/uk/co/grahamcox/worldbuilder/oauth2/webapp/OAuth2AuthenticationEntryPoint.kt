package uk.co.grahamcox.worldbuilder.oauth2.webapp

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Authentication Entry Point for the OAuth2 Service, which will return a 401 with an appropriate payload
 * @param objectMapper The Object Mapper to use to generate the payload with
 */
class OAuth2AuthenticationEntryPoint(objectMapper : ObjectMapper) : AuthenticationEntryPoint {
    /** The payload to use */
    private val payload: String

    init {
        val payloadObject = mapOf("error" to "invalid_client")
        payload = objectMapper.writeValueAsString(payloadObject)
    }

    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          authException: AuthenticationException) {
        response.writer.write(payload)
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.addHeader("WWW-Authenticate", "Basic realm=\"worldbuilder-oauth2\"")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
    }
}