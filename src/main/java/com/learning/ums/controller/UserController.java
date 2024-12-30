package com.learning.ums.controller;

import com.learning.ums.dto.UserCreationRequest;
import com.learning.ums.dto.UserCreationResponse;
import com.learning.ums.dto.UserResponse;
import com.learning.ums.dto.UsersResponse;
import com.learning.ums.entity.User;
import com.learning.ums.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<UsersResponse> getUsers() {

        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping()
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody UserCreationRequest userCreationRequest) {

        return ResponseEntity.ok(userService.createUser(userCreationRequest));
    }
}
