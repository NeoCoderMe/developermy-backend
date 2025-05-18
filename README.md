# DeveloperMy Base Project

Welcome to the DeveloperMy Spring Boot Base Project! This project provides a foundation for building Java applications using the Spring Boot framework.

## Project Information

- **Group ID:** com.developermy
- **Artifact ID:** developermy
- **Version:** 0.0.1-SNAPSHOT

## Project Description

This project serves as a starting point for Java developers, incorporating essential dependencies and configurations commonly used in Spring Boot applications.

## Project Structure

The project structure follows standard Maven conventions and is organized as follows:

- `src/main/java`: Java source code for the application.
- `src/main/resources`: Configuration files and static resources.
- `src/test`: Test source code.

## Dependencies

- **Spring Boot Starter Data JPA:** Provides support for data access using Java Persistence API (JPA).
- **Spring Boot Starter Web:** Enables the development of web applications.
- **H2 Database (Runtime Scope):** In-memory database for development and testing.
- **Spring Boot Starter Test (Test Scope):** Starter for testing Spring Boot applications.
- **Lombok:** Library simplifying Java code by generating boilerplate code.
- **Springdoc OpenAPI:** Automatically generates API documentation.
- **JWT (JSON Web Token):** Library for working with JSON web tokens.
- **Guava:** Google's core libraries for Java.
- **Checkstyle Plugin:** Enforces a consistent coding style.
- **Spring JavaFormat Plugin:** Enforces a consistent Java code format.
- **JaCoCo Plugin:** Generates code coverage reports.

## Build and Run

To build and run the project, use the following Maven command:

```bash
mvn clean install spring-boot:run
 
##Additional Plugins

    Checkstyle Plugin: Enforces coding style rules. Configuration file: config/checkstyle.xml.
    Spring JavaFormat Plugin: Enforces a consistent Java code format.

##Code Coverage

	Code coverage reports are generated using the JaCoCo Maven plugin. 
	The configuration includes a rule to enforce a minimum coverage of 70% for lines of code.