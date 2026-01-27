package com.example.courseapp.service;

import com.example.courseapp.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CourseService {
    private final List<Course> storage = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Course addCourse(Course course) {
        if (course.getMaxStudents() <= 0) {
            throw new IllegalArgumentException("maxStudents must be greater than 0");
        }
        if (course.getId() == null) {
            course.setId(idGenerator.getAndIncrement());
        }
        storage.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return Collections.unmodifiableList(storage);
    }

    public String registerStudent(String courseName) {
        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Course name is required");
        }
        return "Student registered to course: " + courseName;
    }
}
