package com.walid.educore_api.service.impl;

import com.walid.educore_api.dto.request.CreateCourseRequest;
import com.walid.educore_api.dto.response.CourseResponse;
import com.walid.educore_api.entity.Course;
import com.walid.educore_api.exception.ResourceNotFoundException;
import com.walid.educore_api.repository.CourseRepository;
import com.walid.educore_api.service.CourseService;
import com.walid.educore_api.entity.User;
import com.walid.educore_api.enums.UserRole;
import com.walid.educore_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.walid.educore_api.dto.response.InstructorSummaryResponse;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(
            CourseRepository courseRepository,
            UserRepository userRepository
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        User instructor = userRepository.findById(request.instructorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Instructor with ID "
                                        + request.instructorId()
                                        + " not found"
                        )
                );

        if (instructor.getRole() != UserRole.INSTRUCTOR) {
            throw new IllegalArgumentException("Selected user is not an instructor");
        }

        Course course = new Course();


        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setCategory(request.category());
        course.setLevel(request.level());
        course.setPrice(request.price());
        course.setDurationHours(request.durationHours());
        course.setInstructor(instructor);

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

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID " + id + " not found"
                        )
                );

        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setCategory(request.category());
        course.setLevel(request.level());
        course.setPrice(request.price());
        course.setDurationHours(request.durationHours());

        Course updatedCourse = courseRepository.save(course);

        return mapToCourseResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID " + id + " not found"
                        )
                );

        courseRepository.delete(course);
    }

    private CourseResponse mapToCourseResponse(Course course) {

        InstructorSummaryResponse instructorResponse = null;

        if (course.getInstructor() != null) {
            instructorResponse = new InstructorSummaryResponse(
                    course.getInstructor().getFullName()
            );
        }

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getCategory(),
                course.getLevel(),
                course.getPrice(),
                course.getDurationHours(),
                course.getPublished(),
                instructorResponse,
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
}