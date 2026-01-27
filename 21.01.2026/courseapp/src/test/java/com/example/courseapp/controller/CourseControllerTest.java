package com.example.courseapp.controller;

import com.example.courseapp.exception.GlobalExceptionHandler;
import com.example.courseapp.model.Course;
import com.example.courseapp.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
@Import(GlobalExceptionHandler.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @org.springframework.boot.test.mock.mockito.MockBean
    private CourseService courseService;

    @Test
    void postCourses_returns201() throws Exception {
        Course input = new Course(null, "Java", 20);
        Course saved = new Course(1L, "Java", 20);

        when(courseService.addCourse(any(Course.class))).thenReturn(saved);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated());
    }

    @Test
    void postCourses_responseContainsName() throws Exception {
        Course input = new Course(null, "Spring", 15);
        Course saved = new Course(1L, "Spring", 15);

        when(courseService.addCourse(any(Course.class))).thenReturn(saved);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Spring"));
    }

    @Test
    void postCourses_maxStudentsGreaterThanZero() throws Exception {
        Course input = new Course(null, "Docker", 5);
        Course saved = new Course(1L, "Docker", 5);

        when(courseService.addCourse(any(Course.class))).thenReturn(saved);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maxStudents").value(5));
    }

    @Test
    void postCourses_invalidMaxStudents_returns400() throws Exception {
        Course input = new Course(null, "Linux", 0);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void postCourses_illegalArgumentHandled() throws Exception {
        Course input = new Course(null, "Kotlin", 10);

        when(courseService.addCourse(any(Course.class)))
                .thenThrow(new IllegalArgumentException("Some error"));

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Some error"));
    }
}
