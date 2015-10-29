package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

/**
 * Response describing the details of a verified access token
 * @param accessTokenValue The actual access token
 * @param clientIdValue The Client ID that issued the token
 * @param issuedValue When the access token was issued
 * @param expiresValue When the access token expires
 * @param scopesValue The scopes of the access token
 * @param userValue The User ID of the Access Token
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class AccessTokenDetailResponse(accessTokenValue: String,
                                clientIdValue: String,
                                issuedValue: Instant,
                                expiresValue: Instant,
                                scopesValue: Set<String>,
                                userValue: String) {
    @JsonProperty("access_token") var accessToken: String = accessTokenValue
    @JsonProperty("client_id") var clientId: String = clientIdValue
    @JsonProperty("issued") val issued: Instant = issuedValue
    @JsonProperty("expires") val expires: Instant = expiresValue
    @JsonProperty("scopes") val scopes: Set<String> = scopesValue
    @JsonProperty("user_id") val user: String = userValue

}
