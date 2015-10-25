package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Representation of an Access token
 * @param accessTokenValue The actual access token
 * @param tokenTypeValue The type of the token
 * @param expiresValue The time till expiry of the token
 * @param refreshTokenValue The optional Refresh Token
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class AccessTokenResponse(accessTokenValue: String,
                               tokenTypeValue: String,
                               expiresValue: Int,
                               refreshTokenValue: String? = null) {

    @JsonProperty("access_token") var accessToken: String = accessTokenValue
    @JsonProperty("token_type") val tokenType: String = tokenTypeValue
    @JsonProperty("expires_in") val expires: Int = expiresValue
    @JsonProperty("refresh_token") val refreshToken: String? = refreshTokenValue
}