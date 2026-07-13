package com.walid.educore_api.dto.response;

import com.walid.educore_api.enums.CourseCategory;
import com.walid.educore_api.enums.CourseLevel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CourseResponse(

        Long id,

        String title,

        String description,

        CourseCategory category,

        CourseLevel level,

        BigDecimal price,

        Integer durationHours,

        Boolean published,

        InstructorSummaryResponse instructor,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}