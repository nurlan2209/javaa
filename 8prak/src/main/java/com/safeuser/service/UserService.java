package com.safeuser.service;

import com.safeuser.dto.UserRegistrationRequest;
import com.safeuser.dto.UserRegistrationResponse;
import com.safeuser.exception.InvalidUserDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public UserRegistrationResponse register(UserRegistrationRequest request) {
        log.info("User registration started");

        Integer age = request.getAge();
        if (age == null || age < 18) {
            log.warn("User age validation failed: age={}", age);
            throw new InvalidUserDataException("Age must be at least 18");
        }

        String email = request.getEmail();
        if (email == null || email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
            log.warn("User email validation failed: email={}", email);
            throw new InvalidUserDataException("Email format is invalid");
        }

        log.info("User registration completed successfully");
        return new UserRegistrationResponse("Registration successful");
    }
}
