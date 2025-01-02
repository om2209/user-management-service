package com.learning.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDeletionResponse {

    private String message;
    private UserResponse user;
}
