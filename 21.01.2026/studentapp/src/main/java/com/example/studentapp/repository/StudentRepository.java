package com.example.studentapp.repository;

import com.example.studentapp.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {
    private final List<Student> storage = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(idGenerator.getAndIncrement());
        }
        storage.add(student);
        return student;
    }

    public List<Student> findAll() {
        return Collections.unmodifiableList(storage);
    }
}
