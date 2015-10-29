package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenId
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.AccessTokenIssuer
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.ExpiredAccessTokenException
import uk.co.grahamcox.worldbuilder.auth.oauth2.token.MalformedAccessTokenException

/**
 * Controller to verify that an Access Token is valid
 */
@Controller
@RequestMapping("/api/oauth2")
class VerifyTokenController(private val accessTokenIssuer: AccessTokenIssuer) {
    /**
     * Handle the fact that the Access Token being verified was malformed
     */
    @ExceptionHandler(MalformedAccessTokenException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleMalformedAccessToken() = mapOf("error" to "malformed_access_token")

    /**
     * Handle the fact that the Access Token being verified was expired
     */
    @ExceptionHandler(ExpiredAccessTokenException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleExpiredAccessToken() = mapOf("error" to "expired_access_token")

    /**
     * Verify the provided Access Token is indeed valid, and if so return the details of it
     * @param accessTokenId the Access Token to verify
     * @return the access token details
     */
    @RequestMapping("/verify")
    @ResponseBody
    fun verifyToken(@RequestParam("token") accessTokenId: AccessTokenId): AccessTokenDetailResponse {
        val accessToken = accessTokenIssuer.parse(accessTokenId)
        return AccessTokenDetailResponse(
                accessTokenValue = accessToken.id.id,
                clientIdValue = accessToken.clientId.id,
                issuedValue = accessToken.issued,
                expiresValue = accessToken.expires,
                scopesValue = accessToken.scopes.scopes,
                userValue = accessToken.userId.id
        )
    }
}
