package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenId
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenIssuer

/**
 * Controller to verify that an Access Token is valid
 */
@Controller
@RequestMapping("/api/oauth2")
class VerifyTokenController(private val accessTokenIssuer: AccessTokenIssuer) {
    /**
     * Verify the provided Access Token is indeed valid, and if so return the details of it
     * @param accessToken the Access Token to verify
     * @return the access token details
     */
    @RequestMapping("/verify")
    @ResponseBody
    fun verifyToken(@RequestParam("token") accessToken: AccessTokenId)
        = accessTokenIssuer.parse(accessToken)
}
