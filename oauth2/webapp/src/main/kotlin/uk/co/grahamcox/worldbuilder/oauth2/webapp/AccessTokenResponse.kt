package uk.co.grahamcox.worldbuilder.oauth2.webapp

import com.fasterxml.jackson.annotation.JsonProperty
import uk.co.grahamcox.worldbuilder.oauth2.Scopes

/**
 * Representation of an Access Token to respond with
 * @param token The actual Access Token
 * @param refreshToken The Refresh Token for the Access Token
 * @param type The type of the token
 * @param expires The number of seconds until the token expires
 * @param scope The scope of the token
 * @param state The state string for the token request
 */
data class AccessTokenResponse(@JsonProperty("access_token") val token: String,
                               @JsonProperty("refresh_token") val refreshToken: String? = null,
                               @JsonProperty("token_type") val type: String,
                               @JsonProperty("expires_in") val expires: Int,
                               @JsonProperty("scope") val scope: String? = null,
                               @JsonProperty("state") val state: String? = null)