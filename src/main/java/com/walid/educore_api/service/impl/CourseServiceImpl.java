package com.walid.educore_api.service.impl;

import com.walid.educore_api.dto.request.CreateCourseRequest;
import com.walid.educore_api.dto.response.CourseResponse;
import com.walid.educore_api.entity.Course;
import com.walid.educore_api.repository.CourseRepository;
import com.walid.educore_api.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        Course course = new Course();

        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setCategory(request.category());
        course.setLevel(request.level());
        course.setPrice(request.price());
        course.setDurationHours(request.durationHours());

        Course savedCourse = courseRepository.save(course);

        CourseResponse response = new CourseResponse(
                savedCourse.getId(),
                savedCourse.getTitle(),
                savedCourse.getDescription(),
                savedCourse.getCategory(),
                savedCourse.getLevel(),
                savedCourse.getPrice(),
                savedCourse.getDurationHours(),
                savedCourse.getPublished(),
                savedCourse.getCreatedAt(),
                savedCourse.getUpdatedAt()
        );

        return response;
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return List.of();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        return null;
    }

    @Override
    public CourseResponse updateCourse(Long id, CreateCourseRequest request) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {

    }

}