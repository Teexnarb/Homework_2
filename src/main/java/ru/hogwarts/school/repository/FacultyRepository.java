package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameContainingIgnoreCase(String name);
    List<Faculty> findByColorContainingIgnoreCase(String color);
    List<Faculty> findByNameAndColorContainingIgnoreCase(String name, String color);
    Faculty findByIdOrNameOrColorContainingIgnoreCase(Long id, String name, String color);
}