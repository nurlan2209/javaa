package com.example.courseapp.service;

import com.example.courseapp.model.Course;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseServiceTest {

    private final CourseService service = new CourseService();

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, -5})
    void addCourse_parameterizedMaxStudents(int value) {
        Course course = new Course(null, "Test", value);

        if (value > 0) {
            assertDoesNotThrow(() -> service.addCourse(course));
        } else {
            assertThrows(IllegalArgumentException.class, () -> service.addCourse(course));
        }
    }
}
