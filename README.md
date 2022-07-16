# User Contact APIs

This application provide APIs for storing users contacts information in an in-memory H2 database.

The project uses Spring Boot, Spring Data, JDK 17, Maven and JUnit 5 + Mockito for testing.

# Build
## What you’ll need
* Any IDE with Maven 3.8+ support
* JDK 17

## API Usage
1. Run main method in `ContactsApplication.java` to start the application. API documentation will be available at `http://localhost:8000/swagger-ui.html`
2. Run tests using `./mvnw clean test`. 
3. Standalone jar for the application can be generated with `./mvnw clean package`. The jar file can be executed using `java -jar target/contacts-0.0.1-SNAPSHOT.jar` 

The `./mvnw ...` commands should be executed in the root directory of the project.

## CI Pipeline
A simple CI pipeline for running the tests and packaging the application can be found at https://github.com/don4rolex/contacts/actions