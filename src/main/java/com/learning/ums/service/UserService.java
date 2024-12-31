package com.learning.ums.service;

import com.learning.ums.dto.*;
import com.learning.ums.entity.User;
import com.learning.ums.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
                name(user.getDisplayName()).
                bio(user.getBio()).
                location(user.getLocation()).
                website(user.getWebsite()).
                createdAt(user.getCreatedAt()).
                updatedAt(user.getUpdatedAt()).
                isVerified(user.getIsVerified()).
                isActive(user.getIsActive()).
                isPrivate(user.getIsPrivate()).
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
                profilePictureUrl(userCreationRequest.getProfilePictureUrl()).
                coverPictureUrl(userCreationRequest.getCoverPictureUrl()).
                bio(userCreationRequest.getBio()).
                location(userCreationRequest.getLocation()).
                website(userCreationRequest.getWebsite()).
                isActive(true).
                isVerified(false).
                isPrivate(true).
                build();
    }

    public UserCreationResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {

        Optional<User> user = userRepository.findById(userId);
        User updatedUser = userRepository.save(getUpdatedUser(user, userUpdateRequest));
        return new UserCreationResponse("success", buildUserResponse(updatedUser));
    }

    private User getUpdatedUser(Optional<User> existingUser, UserUpdateRequest userUpdateRequest) {

        return existingUser.map(user -> getUser(userUpdateRequest, user)).
                orElseThrow(() -> new RuntimeException("User not found"));
    }

    private User getUser(UserUpdateRequest userUpdateRequest, User user) {

        updateIfPresent(userUpdateRequest::getUserName, user::setUserName);
        updateIfPresent(userUpdateRequest::getEmail, user::setEmail);
        updateIfPresent(userUpdateRequest::getDisplayName, user::setDisplayName);
        updateIfPresent(() -> userUpdateRequest.getPassword() != null ?
                        passwordEncoder.encode(userUpdateRequest.getPassword()) : null,
                user::setPasswordHash);
        updateIfPresent(userUpdateRequest::getProfilePictureUrl, user::setProfilePictureUrl);
        updateIfPresent(userUpdateRequest::getCoverPictureUrl, user::setCoverPictureUrl);
        updateIfPresent(userUpdateRequest::getBio, user::setBio);
        updateIfPresent(userUpdateRequest::getLocation, user::setLocation);
        updateIfPresent(userUpdateRequest::getWebsite, user::setWebsite);
        updateIfPresent(userUpdateRequest::getIsVerified, user::setIsVerified);
        updateIfPresent(userUpdateRequest::getIsActive, user::setIsActive);
        updateIfPresent(userUpdateRequest::getIsPrivate, user::setIsPrivate);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    private <T> void updateIfPresent(Supplier<T> getter, Consumer<T> setter) {
        T value = getter.get();
        if (value != null) {
            setter.accept(value);
        }
    }
}
