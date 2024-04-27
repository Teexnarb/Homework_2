package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(String name, String color) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }


    public Faculty getFacultyById(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Long id, String name, String color) {
        Faculty faculty = facultyRepository.findById(id).get();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Student> getStudentByFaculty(Long Id) {
        Faculty faculty = facultyRepository.findById(Id).orElseThrow(
                () -> new FacultyNotFoundException("Faculty not found with id: " + Id));
        return faculty.getStudents();
    }
}