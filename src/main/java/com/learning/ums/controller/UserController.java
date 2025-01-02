package com.learning.ums.controller;

import com.learning.ums.dto.*;
import com.learning.ums.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{userId}")
    public ResponseEntity<UserCreationResponse> updateUser(@PathVariable Long userId,
                                                           @RequestBody UserUpdateRequest userUpdateRequest) {

        return ResponseEntity.ok(userService.updateUser(userId, userUpdateRequest));
    }
}
