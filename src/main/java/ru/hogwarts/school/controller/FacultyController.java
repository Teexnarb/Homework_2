package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getStudentInfo(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> editFaculty(@PathVariable long id, @RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(id, faculty);
        if (foundFaculty != null) {
            return ResponseEntity.ok(foundFaculty);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Faculty> getAllFaculties() {
        return facultyService.findAllFaculties();
    }

    @GetMapping("findByName/{findByName}")
    public List<Faculty> findByNameContainingIgnoreCase(@RequestParam String name) {
        return facultyService.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("findByColor/{findByColor}")
    public List<Faculty> findByColorContainingIgnoreCase(@RequestParam String color) {
        return facultyService.findByColorContainingIgnoreCase(color);
    }

    @GetMapping("findByNameAndColor/{findByNameAndColor}")
    public ResponseEntity<List<Faculty>> findByNameColorContainingIgnoreCase(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        return facultyService.findByNameAndColorContainingIgnoreCase(name, color);
    }

    @GetMapping("findByFacultyOfStudent/{findByFacultyOfStudent}")
    public List<Student> findByFacultyOfStudent(@RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        return facultyService.findByFacultyOfStudent(id, name, color);
    }
}