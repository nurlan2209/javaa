package kz.project;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student.getAge() < 16) {
            throw new IllegalArgumentException("Возраст студента должен быть 16 лет или старше");
        }
        students.add(student);
    }

    public int getTotalStudents() {
        return students.size();
    }

    public double calculateAverageGpa() {
        if (students.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getGpa();
        }
        return sum / students.size();
    }

    public boolean hasExcellentStudent() {
        for (Student s : students) {
            if (s.getGpa() >= 3.5) {
                return true;
            }
        }
        return false;
    }

    public Student findTopStudent() {
        if (students.isEmpty()) {
            return null;
        }

        Student top = students.get(0);
        for (Student s : students) {
            if (s.getGpa() > top.getGpa()) {
                top = s;
            }
        }
        return top;
    }
}
