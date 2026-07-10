package com.walid.educore_api.dto.request;

import com.walid.educore_api.enums.CourseCategory;
import com.walid.educore_api.enums.CourseLevel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateCourseRequest(

        @NotBlank(message = "Course title is required")
        @Size(
                min = 5,
                max = 100,
                message = "Course title must be between 5 and 100 characters"
        )
        String title,

        @NotBlank(message = "Course description is required")
        @Size(
                min = 20,
                max = 1000,
                message = "Course description must be between 20 and 1000 characters"
        )
        String description,

        @NotNull(message = "Course category is required")
        CourseCategory category,

        @NotNull(message = "Course level is required")
        CourseLevel level,

        @NotNull(message = "Course price is required")
        @DecimalMin(
                value = "5.00",
                message = "Course price must be at least $5.00"
        )
        BigDecimal price,

        @NotNull(message = "Course duration is required")
        @Positive(message = "Course duration must be greater than 0 hours")
        Integer durationHours
) {

}