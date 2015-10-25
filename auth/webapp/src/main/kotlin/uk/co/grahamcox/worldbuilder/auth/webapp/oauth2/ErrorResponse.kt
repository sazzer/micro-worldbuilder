package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Representation of an OAuth2 Error
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(val error: String)