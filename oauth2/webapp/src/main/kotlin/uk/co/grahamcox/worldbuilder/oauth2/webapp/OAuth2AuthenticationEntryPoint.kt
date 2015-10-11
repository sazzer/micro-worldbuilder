package uk.co.grahamcox.worldbuilder.oauth2.webapp

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Authentication Entry Point for the OAuth2 Service, which will return a 401 with an appropriate payload
 */
class OAuth2AuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          authException: AuthenticationException) {
        response.writer.write("Go Away")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
    }
}