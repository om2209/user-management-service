package com.learning.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementServiceApplication {

    public static final String APP_ROOT = "/ums";

    public static void main(String[] args) {

        SpringApplication.run(UserManagementServiceApplication.class, args);
    }
}
