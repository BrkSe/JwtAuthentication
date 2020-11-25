# JwtAuthentication
How to implement a Spring Boot Application that makes use of JWT authentication for securing an exposed REST API. This is for the article - https://medium.com/javarevisited/authentication-using-json-web-tokens-part-ii-b21455692a0b
### WebsecurityConfig
It is the place where all the security configurations are defined. This class is implemented by Spring Security’s WebSecurityConfigurer interface. It provides default security configurations and allows other classes to extend it and customize the security configurations by overriding its methods.
### UserDetailsService
UserDetailsService is an interface that has a single method that loads a user based on username.
### JwtAuthenticationEntryPoint
JwtAuthenticationEntryPoint is used to return a 401 unauthorized error to clients that try to access a protected resource without proper authentication.
Only permitted request in which the token is returning is defined and all the other requests are authenticated. Used stateless sessions that won’t be used to store the user’s state. And then JwtRequestFilter is added to validate the tokens in requests headers.
### JwtRequestFilter
JwtRequestFilter is the class responsible for reads JWT authentication token from the Authorization header of all the requests. It loads the user details associated with the token using JwtUserDetailsService. Token generation and validation are done using the JwtTokenService class.
### JwtTokenService
Here define claims of the token, like Issuer, Expiration, Subject, and the ID and sign the JWT using the HS512 algorithm and secret key which is defined in the application.properties file. And also the validation of tokens is done here.
### AuthController & Hello controller
AuthController is the controller that exposes a POST API for authenticating. The POST API gets username and password in the body and authenticates the username and password by the AuthService. If the credentials are valid, a JWT token is created using the JWTTokenService and provided to the client.
HelloController is the controller class for exposing a GET REST API which can be accessed by the client if the request has a valid token.
