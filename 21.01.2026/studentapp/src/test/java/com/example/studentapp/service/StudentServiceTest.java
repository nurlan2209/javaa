package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    @Test
    void addStudent_validAge_savesStudent() {
        Student student = new Student(null, "Aruzhan", 18);
        Student saved = new Student(1L, "Aruzhan", 18);

        when(repository.save(student)).thenReturn(saved);

        Student result = service.addStudent(student);

        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(student);
    }

    @Test
    void addStudent_under16_throwsException() {
        Student student = new Student(null, "Dana", 15);

        assertThrows(IllegalArgumentException.class, () -> service.addStudent(student));
        verify(repository, times(0)).save(student);
    }

    @Test
    void getAllStudents_returnsNonEmptyList() {
        List<Student> students = List.of(
                new Student(1L, "Aruzhan", 19),
                new Student(2L, "Nurlan", 20)
        );

        when(repository.findAll()).thenReturn(students);

        List<Student> result = service.getAllStudents();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void addStudent_boundaryAges() {
        Student age15 = new Student(null, "Age15", 15);
        Student age16 = new Student(null, "Age16", 16);
        Student age30 = new Student(null, "Age30", 30);

        when(repository.save(age16)).thenReturn(new Student(1L, "Age16", 16));
        when(repository.save(age30)).thenReturn(new Student(2L, "Age30", 30));

        assertThrows(IllegalArgumentException.class, () -> service.addStudent(age15));
        assertEquals(1L, service.addStudent(age16).getId());
        assertEquals(2L, service.addStudent(age30).getId());
    }
}
