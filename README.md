# Boat Application Backend

This is the backend server for the Boat Application, a simple web application for managing boats. The server is built
with Java and Spring Boot. It provides a RESTful API for creating, reading, updating, and deleting boats, and it handles
user authentication via JSON Web Tokens (JWT).

## Requirements

- Java 11+
- Maven
- Spring Boot 2.6+
- An SQL database (H2 database is used in the development environment)

## API Endpoints

- `/api/boats` - GET all boats, POST a new boat
- `/api/boats/{id}` - GET, PUT, DELETE a specific boat by ID
- `/api/login` - POST a login request to authenticate a user and receive a JWT

### Testing

- Download and import the [Postman collection file](OWT_Demo.postman_collection.json) into your Postman and start
  sending requests.
- Or check out the Test App via: https://boat-hero.azurewebsites.net/

### Demo Users

For testing and demonstration purposes, our application currently supports the following user accounts:

| Username | Password |
| --- | --- |
| Anna | save-password |
| Carla | 918 |
| John | 12345678 |

These accounts are pre-populated for convenience during the testing and demo phase.

### Future Updates

In the future, we plan to implement a robust and secure user management system. This system will provide a full suite of
features such as:

- User registration (Sign Up)
- Account verification
- Secure password reset mechanism
- Profile management
- Two-factor authentication (optional)

## Authentication

This application uses JSON Web Tokens (JWT) for authentication.

To authenticate, send a POST request to `/api/login` with a JSON body containing the username and password:

```json
{
  "username": "yourusername",
  "password": "yourpassword"
}
```

If the login is successful, you will receive a JWT in the response. Include this token in the `Authorization` header of
your requests to authenticate:

```
Authorization: Bearer your.jwt.token.here
```

## Testing

Tests are included in the project. To run the tests, use the following command:

```
./mvnw test
```
