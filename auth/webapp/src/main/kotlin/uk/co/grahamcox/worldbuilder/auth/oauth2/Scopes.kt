package uk.co.grahamcox.worldbuilder.auth.oauth2

/**
 * Representation of a set of scopes
 * @param scopes The set of scopes
 */
data class Scopes(val scopes: Set<String>) {
    /**
     * Secondary constructor to construct the scopes from a single string, such as would be passed
     * across the OAuth2 API
     * @param scopes The string representing the scopes
     */
    constructor(scopes: String) : this(scopes.split("\\s".toRegex())
            .filter(String::isNotBlank)
            .toSet())

    /**
     * Convert the scopes back into a String such as would be passed across the OAuth2 API
     * @return a String of the scopes, which has each scope separated by a space
     */
    override fun toString(): String = scopes.sorted().joinToString(" ")
}