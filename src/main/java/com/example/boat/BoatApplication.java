package com.example.boat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BoatApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoatApplication.class, args);
    }

}
