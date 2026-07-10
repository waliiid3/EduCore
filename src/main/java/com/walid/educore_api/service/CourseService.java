package com.walid.educore_api.service;

import com.walid.educore_api.dto.request.CreateCourseRequest;
import com.walid.educore_api.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long id);

    CourseResponse updateCourse(Long id, CreateCourseRequest request);

    void deleteCourse(Long id);

}