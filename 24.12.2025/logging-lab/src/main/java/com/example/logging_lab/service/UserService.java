package com.example.logging_lab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public String getUserById(long id) {
        log.info("Operation started: getUserById");
        log.debug("Processing user data for id={}", id);

        if (id == 1) {
            return "User#1";
        }

        log.warn("User not found: id={}", id);
        throw new RuntimeException("User not found: id=" + id);
    }
}
