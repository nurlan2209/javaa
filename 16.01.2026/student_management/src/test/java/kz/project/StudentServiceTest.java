package kz.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void shouldAddStudentIfAgeIs16OrMore() {
        Student student = new Student("Ali", 18, 3.2);
        studentService.addStudent(student);

        assertEquals(1, studentService.getTotalStudents());
    }

    @Test
    void shouldThrowExceptionIfAgeIsBelow16() {
        Student student = new Student("Aruzhan", 15, 3.8);

        assertThrows(IllegalArgumentException.class, () ->
                studentService.addStudent(student)
        );
    }

    @Test
    void totalStudentsShouldBeZeroInitially() {
        assertEquals(0, studentService.getTotalStudents());
    }

    @Test
    void totalStudentsShouldBeTwoAfterAddingTwoStudents() {
        studentService.addStudent(new Student("Ali", 18, 3.0));
        studentService.addStudent(new Student("Dana", 19, 4.0));

        assertEquals(2, studentService.getTotalStudents());
    }

    @Test
    void averageGpaShouldBeZeroIfNoStudents() {
        assertEquals(0, studentService.calculateAverageGpa());
    }

    @Test
    void averageGpaShouldBeCorrect() {
        studentService.addStudent(new Student("Ali", 18, 3.0));
        studentService.addStudent(new Student("Dana", 19, 4.0));

        assertEquals(3.5, studentService.calculateAverageGpa());
    }

    @Test
    void shouldReturnFalseIfNoExcellentStudent() {
        studentService.addStudent(new Student("Ali", 18, 3.2));

        assertFalse(studentService.hasExcellentStudent());
    }

    @Test
    void shouldReturnTrueIfExcellentStudentExists() {
        studentService.addStudent(new Student("Dana", 19, 3.7));

        assertTrue(studentService.hasExcellentStudent());
    }

    @Test
    void shouldReturnTopStudentWithHighestGpa() {
        Student s1 = new Student("Ali", 18, 3.0);
        Student s2 = new Student("Dana", 19, 4.0);
        Student s3 = new Student("Arman", 20, 3.5);

        studentService.addStudent(s1);
        studentService.addStudent(s2);
        studentService.addStudent(s3);

        Student topStudent = studentService.findTopStudent();

        assertEquals("Dana", topStudent.getName());
    }

    @Test
    void shouldReturnNullIfNoStudents() {
        assertNull(studentService.findTopStudent());
    }
}
