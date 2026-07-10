package com.walid.educore_api.controller;

import com.walid.educore_api.dto.request.CreateCourseRequest;
import com.walid.educore_api.dto.response.CourseResponse;
import com.walid.educore_api.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponse createCourse(@Valid @RequestBody CreateCourseRequest request) {

        return courseService.createCourse(request);

    }

}