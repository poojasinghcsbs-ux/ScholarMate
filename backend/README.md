# ScholarMate Backend

Spring Boot + MySQL backend for the existing ScholarMate frontend.

## Planned stack

- Java 17
- Spring Boot 3.4
- Maven
- MySQL
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

## Member 1 APIs completed

These APIs are implemented without authentication for now. Member 2 can later secure them with JWT and replace `studentId` in the URL with the authenticated user.

| Module | Method | Endpoint |
|---|---|---|
| Student profile | POST | `/api/students/profile` |
| Student profile | GET | `/api/students/{studentId}/profile` |
| Student profile | PUT | `/api/students/{studentId}/profile` |
| Scholarship search | GET | `/api/scholarships?query=&educationLevel=&category=&maxFamilyIncome=` |
| Scholarship details | GET | `/api/scholarships/{scholarshipId}` |
| Apply for scholarship | POST | `/api/students/{studentId}/applications` |
| Application history | GET | `/api/students/{studentId}/applications` |
| Update application status | PATCH | `/api/applications/{applicationId}/status` |
| Student dashboard | GET | `/api/students/{studentId}/dashboard` |

Member 2 should use the existing `Scholarship` entity for admin scholarship CRUD rather than creating a duplicate entity.

## Local requirements before running

1. Install JDK 17.
2. Install Maven.
3. Install MySQL. The standard local URL will create the `scholarmate` database automatically.
4. Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` from `.env.example` in your local environment.

When implementation begins, start it from this folder with:

```bash
mvn spring-boot:run
```
