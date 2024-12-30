package com.learning.ums.service;

import com.learning.ums.dto.UserCreationRequest;
import com.learning.ums.dto.UserCreationResponse;
import com.learning.ums.dto.UserResponse;
import com.learning.ums.dto.UsersResponse;
import com.learning.ums.entity.User;
import com.learning.ums.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UsersResponse getUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> data = new ArrayList<>();
        for (User user : users) {
            data.add(buildUserResponse(user));
        }
        return new UsersResponse("success", data);
    }

    private static UserResponse buildUserResponse(User user) {

        return UserResponse.builder().
                id(user.getId()).
                userName(user.getUserName()).
                email(user.getEmail()).
                bio(user.getBio()).
                location(user.getLocation()).
                website(user.getWebsite()).
                createdAt(user.getCreatedAt()).
                updatedAt(user.getUpdatedAt()).
                isVerified(user.isVerified()).
                isActive(user.isActive()).
                isPrivate(user.isPrivate()).
                build();
    }

    public UserCreationResponse createUser(UserCreationRequest userCreationRequest) {

        User addedUser = userRepository.save(getNewUser(userCreationRequest));
        return new UserCreationResponse("success", buildUserResponse(addedUser));
    }

    private User getNewUser(UserCreationRequest userCreationRequest) {

        return User.builder().
                userName(userCreationRequest.getUserName()).
                email(userCreationRequest.getEmail()).
                passwordHash(passwordEncoder.encode(userCreationRequest.getPassword())).
                displayName(userCreationRequest.getDisplayName()).
                isActive(true).
                isVerified(false).
                isPrivate(true).
                build();
    }
}
