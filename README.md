# Public Library(Books)
Public Library for Books

## About the application
API servers as a backend for Public Library. It is a SpringBoot 3.0+ application making use
of JDK 21.

CRUD operations for books stored in an in-memory database.
- Please register as user on http://localhost:8000/api/v1/auth/signup with role admin, user or moderator.
- Then signin with a username and password at http://localhost:8000/api/v1/auth/signin
where you will receive a BEARER(jwt) token which you will use to access API operations.

### Prerequisites

- [JDK Version 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Maven 3](https://maven.apache.org/download.cgi)

