package com.example.boat;

import com.example.boat.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    @Value("${app.jwt.expiration-in-milliseconds}")
    private Integer EXPIRATION_IN_MILLISECONDS;

    private static final List<User> registeredUsers = List.of(
            new User("Anna", "save-password"),
            new User("Carla", "918"),
            new User("John", "12345678")
    );

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Username or password not present");
        }

        if (!registeredUsers.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password))) {
            return ResponseEntity.notFound().build();
        }
        
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String jwt = Jwts.builder()
                .setSubject(request.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_IN_MILLISECONDS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String json = "{\"token\": \"" + jwt + "\"}";
        return ResponseEntity.ok(json);
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }

}

