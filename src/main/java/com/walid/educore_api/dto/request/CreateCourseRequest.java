package com.walid.educore_api.dto.request;

import com.walid.educore_api.enums.CourseCategory;
import com.walid.educore_api.enums.CourseLevel;

import java.math.BigDecimal;

public record CreateCourseRequest(

        String title,

        String description,

        CourseCategory category,

        CourseLevel level,

        BigDecimal price,

        Integer durationHours

) {
}