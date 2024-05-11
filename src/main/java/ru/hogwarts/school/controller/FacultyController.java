package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long id) {
        Optional<Faculty> faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty.get());
    }

    @PostMapping("/createFaculty")
    public Faculty createFaculty(Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping("/editFaculty")
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {
        Faculty faculty1 = facultyService.editFaculty(faculty);
        if (faculty1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping("/deleteFaculty/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/findFacultyByColorOrName")
    public ResponseEntity<Faculty> findByNameOrColor(@RequestParam(required = false) String color, @RequestParam(required = false) String name) {
        Optional<Faculty> facultyToFind = facultyService.findByNameOrColor(color, name);
        if (facultyToFind == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.findByNameOrColor(color, name).get());

    }

    @GetMapping("/getAllStudentOfFaculty")
    public ResponseEntity<Collection<Student>> getAllStudentOfFaculty(@RequestParam long id) {
        Collection<Student> studentOfFaculty = facultyService.getAllStudentOfFaculty(id);
        if (studentOfFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentOfFaculty);

    }
    @GetMapping ("/getBiggestNameOfFaculty")
    public ResponseEntity <String> getBiggestNameOfFaculty() {
        String biggestName = String.valueOf(facultyService.getBiggestNameOfFaculty());
        if (biggestName.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(biggestName);
    }
}
