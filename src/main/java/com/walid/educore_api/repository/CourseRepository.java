package com.walid.educore_api.repository;

import com.walid.educore_api.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import com.walid.educore_api.enums.CourseCategory;
import com.walid.educore_api.enums.CourseLevel;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitle(String title);

    List<Course> findByCategory(CourseCategory category);

    List<Course> findByLevel(CourseLevel level);
    


}