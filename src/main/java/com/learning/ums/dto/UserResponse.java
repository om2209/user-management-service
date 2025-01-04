package com.learning.ums.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserResponse {

    private long id;
    private String userName;
    private String name;
    private String email;
    private String bio;
    private String currentLocation;
    private String website;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isVerified;
    private boolean isActive;
    private boolean isPrivate;
}
