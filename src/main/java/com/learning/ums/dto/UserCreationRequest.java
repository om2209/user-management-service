package com.learning.ums.dto;

import lombok.Data;

@Data
public class UserCreationRequest {

    private String userName;
    private String email;
    private String password;
    private String displayName;
    private String profilePictureUrl;
    private String coverPictureUrl;
    private String bio;
    private String location;
    private String website;
}
