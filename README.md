<img src="https://github.com/SeniorityMeter/spring-sm-starter-bom/assets/36059306/ebfcb364-caea-48eb-972a-2d1ae63f4cdb" alt="logo" width="100"/>

# Seniority Meter
## Spring Security

### Description
This is a simple security SDK for Spring Boot applications. It provides a simple configuration of provides security for your applications. 

___

### How to use
#### 1. Add the following parent to your `pom.xml` file:

```xml
<parent>
    <groupId>br.com.senioritymeter</groupId>
    <artifactId>parent</artifactId>
    <version>1.0.3</version>
</parent>
```
___

#### 2. add scanBasePackages to your SpringBootApplication
```java
@SpringBootApplication(scanBasePackages = {"br.com.senioritymeter", "your.package.name.here"})
```
___

#### 3. Add the following dependency and repository to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>br.com.senioritymeter</groupId>
        <artifactId>security</artifactId>
        <version>1.0.2</version>
    </dependency>
</dependencies>
```
___

#### 4. Add the following properties to your `application.yaml` file:

```yaml
spring:
  security:
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

##### b - Implement the `SMUserDetails` interface:
```java
public class SMUserDetailsImpl implements SMUserDetails {
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

##### a - Provide authorization requests with implements the `SMAuthorizeRequest` interface
##### b - Provide cors configuration with implements the `SMCorsConfiguration` interface
##### c - Provide the password encoder with implements the `SMPasswordEncoder` interface

___

#### 7. Use the `AuthenticateUser` bean to authenticate the user:

```java
private final AuthenticateUser authenticateUser;

authenticateUser.authenticate("username", "password");
```
___

#### 8. Use the `GenerateToken` bean to generate a token:

```java
private final GenerateToken generateToken;

final var tokenInput = Input.builder()
    .subject("username")
    .expiresAt(Instant.now().plusSeconds(3600))
    .build();

final var token = generateToken.generateToken(tokenInput);
```
___

#### 9. Use the `LoggedUser` static to get the logged user:

```java
final var username = LoggedUser.getUsername();
```
