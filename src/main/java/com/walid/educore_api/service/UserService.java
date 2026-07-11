package com.walid.educore_api.service;

import com.walid.educore_api.dto.request.CreateUserRequest;
import com.walid.educore_api.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

}