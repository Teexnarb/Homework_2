package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(long id, Student student);

    void deleteStudent(long id);

    List<Student> findAllStudents();

    List<Student> findByAgeBetween(Integer from, Integer to);

    List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer from, Integer to);

    Faculty findByStudentOfFaculty(Long id, String name);
}