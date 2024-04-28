package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);

    List<Faculty> findAllFaculties();

    List<Faculty> findByNameContainingIgnoreCase(String name);

    List<Faculty> findByColorContainingIgnoreCase(String color);

    ResponseEntity<List<Faculty>> findByNameAndColorContainingIgnoreCase(String name, String color);

    List<Student> findByFacultyOfStudent(Long id, String name, String color);
}