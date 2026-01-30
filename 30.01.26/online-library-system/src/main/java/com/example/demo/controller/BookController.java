package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @GetMapping("/books")
    public List<String> getBooks() {
        return List.of(
                "Clean Code",
                "Java Fundamentals",
                "Spring Boot in Action"
        );
    }
}
