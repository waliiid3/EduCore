package com.walid.educore_api.service.impl;

import com.walid.educore_api.dto.request.CreateCourseRequest;
import com.walid.educore_api.dto.response.CourseResponse;
import com.walid.educore_api.entity.Course;
import com.walid.educore_api.exception.ResourceNotFoundException;
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

        return mapToCourseResponse(savedCourse);
    }

    @Override
    public List<CourseResponse> getAllCourses() {

        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(this::mapToCourseResponse)
                .toList();
    }

    @Override
    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID " + id + " not found"
                        )
                );

        return mapToCourseResponse(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, CreateCourseRequest request) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {

    }

    private CourseResponse mapToCourseResponse(Course course) {

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCategory(),
                course.getLevel(),
                course.getPrice(),
                course.getDurationHours(),
                course.getPublished(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
}