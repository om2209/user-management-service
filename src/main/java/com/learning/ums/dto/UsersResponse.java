package com.learning.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UsersResponse {

    private String status;
    private List<UserResponse> data;
}
