package uk.co.grahamcox.worldbuilder.oauth2.webapp

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.worldbuilder.oauth2.Scopes

/**
 * Spring Controller to handle the Client Credentials Grant of the OAuth2 API
 */
@Controller
open class ClientCredentialsGrantController {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(ClientCredentialsGrantController::class.java)

    /**
     * Handler for the Client Credentials Grant
     * @param scopes the scopes to request for the grant
     */
    @RequestMapping(value = "/token",
        method = arrayOf(RequestMethod.GET, RequestMethod.POST),
        params = arrayOf("grant_type=client_credentials"))
    @ResponseBody
    fun token(@RequestParam(value = "scopes", required = false, defaultValue = "*") scopes: Scopes) {
        LOG.debug("Performing Client Credentials grant for scopes {}", scopes)
    }
}