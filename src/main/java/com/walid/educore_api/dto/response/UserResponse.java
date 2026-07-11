package com.walid.educore_api.dto.response;

import com.walid.educore_api.enums.UserRole;

import java.time.LocalDateTime;

public record UserResponse(

        Long id,

        String fullName,

        String email,

        UserRole role,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}