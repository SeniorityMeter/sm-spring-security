<img src="https://github.com/SeniorityMeter/spring-sm-starter-bom/assets/36059306/ebfcb364-caea-48eb-972a-2d1ae63f4cdb" alt="logo" width="100"/>

# Seniority Meter
## Open Source Library - Spring Security

### Description
This library is a simple security library for Spring Boot applications. It provides a simple configuration of provides authentication for your applications. 

___

### How to use
#### 1. Add the following dependency and repository to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>com.opensourcelibrary.spring</groupId>
        <artifactId>security</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>

<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/SeniorityMeter/*</url>
    </repository>
</repositories>
```
___

#### 2. add scanBasePackages to your SpringBootApplication
```java
@SpringBootApplication(scanBasePackages = {"com.opensourcelibrary", "your.package.name.here"})
```
___

#### 3. Add the following properties to your `application.yaml` file:

##### a - Configuration for AWS S3:

```yaml
spring:
  security:
    jwt:
      secret: ${JWT_SECRET:os-spring-security-jwt-secret}
```
___

#### 4. Configurations mandatory for the library to work:

##### a - Save your user encoding the password with the `PasswordEncoder`:

```java
private final PasswordEncoder passwordEncoder;

passwordEncoder.encode(password);
```

##### b - Implement the `OSLUserDetails` interface:
```java
public class OSLUserDetailsImpl implements OSLUserDetails {
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

##### a - Provide authorization requests with implements the `OSLAuthorizeRequest` interface
##### b - Provide cors configuration with implements the `OSLCorsConfiguration` interface
##### c - Provide the password encoder with implements the `OSLPasswordEncoder` interface
