package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int max, int min);


    List<Student> findAll();

    @Query(value = "select count(*) from student s", nativeQuery = true)
    Integer amountOfStudents();

    @Query(value = "select avg(age) from student s ", nativeQuery = true)
    Integer averageAge();

    @Query(value = "select *from student s order by id desc limit 5", nativeQuery = true)
    List<Student> getLastStudents();

}
