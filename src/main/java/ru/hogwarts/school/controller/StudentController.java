package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable long id) {
        Student foundStudent = studentService.editStudent(id, student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("findByAgeBetween/{findByAgeBetween}")
    public List<Student> findByAgeBetween(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to) {
        return studentService.findByAgeBetween(from, to);
    }

    @GetMapping("findByAgeInclusive/{findByAgeInclusive}")
    public List<Student> findByAgeLessThanEqualAndGreaterThanEqual(@RequestParam(required = false) Integer lowerBound, @RequestParam(required = false) Integer upperBound) {
        return studentService.findByAgeLessThanEqualAndGreaterThanEqual(lowerBound, upperBound);
    }
    @GetMapping("findByStudentOfFaculty/{findByStudentOfFaculty}")
    public Faculty findByStudentOfFaculty(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        return studentService.findByStudentOfFaculty(id, name);
    }
}