package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebArgumentResolver
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.nio.charset.Charset
import java.util.*

/**
 * Web Argument Resolver to load Client Credentials from the Basic Authentication header
 */
class ClientCredentialsResolver : HandlerMethodArgumentResolver {
    /** The Basic Authentication type */
    private val BASIC_AUTHENTICATION_TYPE = "Basic"

    /** The Character Set to use */
    private val CHARSET = Charset.forName("UTF-8")
    /**
     * Actually resolve the value of the argument
     * @param parameter The parameter to resolve
     * @param mavContainer The Model and View container
     * @param webRequest The web request
     * @param binderFactory The Web Binder factory
     * @return the resolved argument
     */
    override fun resolveArgument(parameter: MethodParameter,
                                 mavContainer: ModelAndViewContainer,
                                 webRequest: NativeWebRequest,
                                 binderFactory: WebDataBinderFactory): Any? {
        val authorization: String? = webRequest.getHeader("Authorization")

        return if (authorization != null && authorization.startsWith(BASIC_AUTHENTICATION_TYPE)) {
            val encodedCredentials = authorization.substring(BASIC_AUTHENTICATION_TYPE.length + 1)
            val decodedCredentials = String(Base64.getDecoder().decode(encodedCredentials), CHARSET)
            val credentials = decodedCredentials.split(":".toRegex(), 2)
            ClientCredentials(credentials[0], credentials[1])
        } else {
            null
        }
    }

    /**
     * Check if we support this particular parameter
     * @param parameter The parameter to check
     * @return True if it's a ClientCredentials type. False if not
     */
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.parameterType.isAssignableFrom(ClientCredentials::class.java)
}