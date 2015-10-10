package uk.co.grahamcox.worldbuilder.oauth2

/**
 * Representation of the Scopes of an OAuth2 Token
 * @param scopes the list of scopes
 */
class Scopes(val scopes: List<String>) {
    /**
     * Construct from a String by splitting it into individual scopes
     * @param scopesString The string to parse as the list of scopes
     */
    constructor(scopesString: String) : this(scopesString.split(" ").filterNot { s -> s.isBlank() })

    /**
     * Combine the scopes together into a single string
     * @return the string form of the scopes
     */
    override fun toString() = scopes.join(" ")
}