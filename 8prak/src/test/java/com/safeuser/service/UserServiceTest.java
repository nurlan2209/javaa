package com.safeuser.service;

import com.safeuser.dto.UserRegistrationRequest;
import com.safeuser.dto.UserRegistrationResponse;
import com.safeuser.exception.InvalidUserDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void register_withValidData_succeeds() {
        UserRegistrationRequest request = new UserRegistrationRequest("Aruzhan", 22, "aru@example.com");

        UserRegistrationResponse response = userService.register(request);

        assertEquals("Registration successful", response.getMessage());
    }

    @Test
    void register_withUnderageUser_throwsException() {
        UserRegistrationRequest request = new UserRegistrationRequest("Dana", 17, "dana@example.com");

        assertThrows(InvalidUserDataException.class, () -> userService.register(request));
    }

    @Test
    void register_withInvalidEmail_throwsException() {
        UserRegistrationRequest request = new UserRegistrationRequest("Nurlan", 25, "invalid-email");

        assertThrows(InvalidUserDataException.class, () -> userService.register(request));
    }
}
