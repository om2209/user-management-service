package com.learning.ums.dto;

import lombok.Data;

@Data
public class UserCreationRequest {

    private String userName;
    private String email;
    private String password;
    private String countryCode;
    private String mobileNumber;
    private String displayName;
    private String profilePictureUrl;
    private String coverPictureUrl;
    private String bio;
    private String currentLocation;
    private String website;
}
