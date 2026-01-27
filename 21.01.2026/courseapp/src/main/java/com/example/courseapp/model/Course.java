package com.example.courseapp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Course {
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @Min(value = 1, message = "maxStudents must be greater than 0")
    private int maxStudents;

    public Course() {
    }

    public Course(Long id, String name, int maxStudents) {
        this.id = id;
        this.name = name;
        this.maxStudents = maxStudents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }
}
