# Plan of work

* Auth
  * ~~Webapp~~
  * ~~Decode Client Credentials from Basic Auth~~
  * ~~Client Credentials Controller~~
    * ~~Return valid token on success~~
    * ~~Return errors on failure~~
    * ~~Mechanism to load client details from DAO~~
    * DAO to load client details from data store
  * ~~Support for verifying tokens~~
    * ~~Return details of a valid token~~
    * ~~Return errors on invalid tokens~~
    * (Future) Check for revoked tokens in the data store
  * Client Library for checking auth tokens
  * (Future) Ability to register new clients
  * (Future) Support for revoking tokens
  * (Future) Support for External Authentication
    * Google+
    * Facebook
    * Twitter
  * (Future) Support for other OAuth2 mechanisms
    * Authorization Code Grant
    * Implicit Grant
    * Resource Owner Password Credentials Grant
    * Refresh Token Grant
