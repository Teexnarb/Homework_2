package ru.hogwarts.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
//        return facultyRepository.findById(id).get();
//        return facultyRepository.getReferenceById(id);
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> findByNameContainingIgnoreCase(String name) {
        return facultyRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Faculty> findByColorContainingIgnoreCase(String color) {
        return facultyRepository.findByColorContainingIgnoreCase(color);
    }

    @Override
    public ResponseEntity<List<Faculty>> findByNameAndColorContainingIgnoreCase(String name, String color) {
        if (color.trim().isEmpty() && name.trim().isEmpty()) {
            return ResponseEntity.ok(facultyRepository.findAll());
        } else if (color.trim().isEmpty()) {
            return ResponseEntity.ok(facultyRepository.findByNameContainingIgnoreCase(name));
        } else if (name.trim().isEmpty()) {
            return ResponseEntity.ok(facultyRepository.findByColorContainingIgnoreCase(color));
        } else {
            return ResponseEntity.ok(facultyRepository.findByNameAndColorContainingIgnoreCase(name, color));
        }
    }

    @Override
    public List<Student> findByFacultyOfStudent(Long id, String name, String color) {
        if (Optional.ofNullable(facultyRepository.findByIdOrNameOrColorContainingIgnoreCase(id, name, color)).isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return facultyRepository.findByIdOrNameOrColorContainingIgnoreCase(id, name, color).getStudents();
    }
}