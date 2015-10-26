package uk.co.grahamcox.worldbuilder.auth.webapp.oauth2

/**
 * Base class for OAuth2 Exceptions
 * @param errorCode The error code to return
 * @param message The error message
 */
open class OAuth2Exception(val errorCode: String, message: String? = null) : Exception(message)

/**
 * OAuth2 Invalid Request exception
 * @param message The error message
 */
class OAuth2InvalidRequestException(message: String? = null) : OAuth2Exception("invalid_request", message)

/**
 * OAuth2 Invalid Client exception
 * @param message The error message
 */
class OAuth2InvalidClientException(message: String? = null) : OAuth2Exception("invalid_client", message)

/**
 * OAuth2 Unsupported Grant Type exception
 * @param message The error message
 */
class OAuth2UnsupportedGrantTypeException(message: String? = null) : OAuth2Exception("unsupported_grant_type", message)
