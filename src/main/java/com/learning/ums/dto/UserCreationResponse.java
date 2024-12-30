package com.learning.ums.dto;

import com.learning.ums.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreationResponse {

    private String status;
    private UserResponse user;
}
