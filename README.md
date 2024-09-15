<img src="https://github.com/user-attachments/assets/801ecb0c-455c-41a8-bb52-15d4318f2e78" alt="logo" width="100" style="border-radius: 50%;" />

# SDK Open
## Spring Security

### Description
A simple security SDK for Spring Boot applications.

___

### How to use
#### 1. Add the following parent to your `pom.xml` file:

```xml
<parent>
    <groupId>br.com.sdkopen</groupId>
    <artifactId>parent</artifactId>
    <version>1.0.0</version>
</parent>
```
___

#### 2. add scanBasePackages to your SpringBootApplication
```java
@SpringBootApplication(scanBasePackages = {"br.com.sdkopen", "your.package.name.here"})
```
___

#### 3. Add the following dependency and repository to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>br.com.sdkopen</groupId>
        <artifactId>security</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
___

#### 4. Add the following properties to your `application.yaml` file:

```yaml
sdkopen:
  security:
    enabled: true
    jwt:
      secret: ${SECURITY_JWT_SECRET:security-jwt-secret}
```
___

#### 5. Configurations mandatory for the library to work:

##### a - Save your user encoding the password with the `PasswordEncoder`:

```java
private final PasswordEncoder passwordEncoder;

passwordEncoder.encode(password);
```

##### b - Implement the `GetUserDetails` interface:
```java
public class GetUserDetailsImpl implements GetUserDetails {
  private final UserRepository userRepository; // 

  @Override
  public UserDetails loadUserDetails(String username) {
    var user =
        this.userRepository
            .findByEmail(username)
            .orElseThrow(() -> new RuntimeExeption("User not found"));

    return new UserDetails() {
      // TODO: Implement the UserDetails interface
      // 
    };
  }
}
```

___

#### 6. Miscellaneous configurations:

##### a - Provide authorization requests with implements the `AllAuthorizeRequest` interface
##### b - Provide cors configuration with implements the `AllCorsConfiguration` interface
##### c - Provide the password encoder with implements the `GetPasswordEncoder` interface

___

#### 7. Use the `AuthenticateUser` bean to authenticate the user:

```java
private final AuthenticateUser authenticateUser;

authenticateUser.authenticate("username", "password");
```
___

#### 8. Use the `GetToken` bean to generate a token:

```java
private final GetToken getToken;

final var input = GetToken.Input.builder()
    .subject("username")
    .expiresAt(Instant.now().plusSeconds(3600))
    .build();

final var token = getToken.execute(input);
```
___

#### 9. Use the `GetAuthenticatedUsername` static to get the logged user:

```java
final var username = GetAuthenticatedUsername.execute();
```
