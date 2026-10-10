# ScholarMate Backend

Spring Boot backend scaffold for the existing ScholarMate frontend.

## Planned stack

- Java 17
- Spring Boot 3.4
- Maven
- PostgreSQL
- Spring Web, Validation, Spring Data JPA

## Folder layout

```text
src/main/java/com/scholarmate/
  config/          # CORS and security configuration
  controller/      # REST endpoints
  service/         # Business logic
  repository/      # Database access
  entity/          # Database models
  dto/             # Request/response objects
  exception/       # Error handling
src/main/resources/
  application.yml  # Server and database configuration
```

No API or authentication code has been added yet, as requested. The next step is to create the Spring Boot application entry point, then add authentication and database entities.

## Local requirements before running

1. Install JDK 17.
2. Install Maven.
3. Install PostgreSQL and create a database called `scholarmate`.
4. Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` from `.env.example` in your local environment.

When implementation begins, start it from this folder with:

```bash
mvn spring-boot:run
```
