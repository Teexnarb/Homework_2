package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(String name, int age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, String name, int age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        studentRepository.save(student);
        return student;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByAge(int age) {
        return studentRepository.getByAge(age);
    }

    public Faculty getFacultyByStudentId(Long Id) {
        Student student = studentRepository.findById(Id).orElseThrow(
                () -> new StudentNotFoundException("Student not found with id: " + Id));
        return student.getFaculty();
    }
}