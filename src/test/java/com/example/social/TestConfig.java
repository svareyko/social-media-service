package com.example.social;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.social.controller",
        "com.example.social.service",
        "com.example.social.service.impl"
})
public class TestConfig {
}
