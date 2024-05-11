package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private Integer count = 1;


    public Student createStudent(Student student) {
        logger.info("был вызван метод, чтобы создать студента");
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(long id) {

        logger.info("был вызван метод, чтобы найти студента по идентификатору");
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        logger.warn("был вызван метод, чтобы редактировать студента");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {

        logger.warn("был вызван метод, чтобы удалить студента");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("был вызван метод, чтобы найти студента по возрасту");
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int max, int min) {
        logger.info("был вызван метод, чтобы найти студентов в диапозоне разных возрастов");
        return studentRepository.findByAgeBetween(max, min);
    }

    public Faculty findFacultyOfStudent(long id) {
        logger.info("был вызван метод, чтобы найти факультет студента");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get().getFaculty();
        } else {
            return null;
        }
    }

    public List<Student> getAllStudents() {
        logger.info("был вызван метод, чтобы найти всех студентов");
        return studentRepository.findAll();
    }

    public int getAmountOfStudents() {
        logger.info("был вызван метод, чтобы получить количество всех студентов");
        return studentRepository.amountOfStudents();
    }

    public int getAverageAge() {
        logger.info("был вызван метод, чтобы получить средний возраст студентов");
        return studentRepository.averageAge();
    }

    public List<Student> getLastStudents() {
        logger.info("был вызван метод, чтобы получить последних добавленных студентов");
        return studentRepository.getLastStudents();
    }

    public List<String> getStudentsNameStartsWith(String letter) {
        List<Student> exp = studentRepository.findAll();

        List<String> namesExp = exp
                .stream()
                .map(Student::getName)
                .filter(name -> name.toUpperCase().startsWith(letter))
                .sorted()
                .toList();

        return namesExp;
    }

    public double getAverageAgeWithStream() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
    }

    public void printParallel() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();

    }


    public void printSynchronized() {
        List<Student> students = studentRepository.findAll();

        printStudentName(students);
        printStudentName(students);

        new Thread(() -> {
            printStudentName(students);
            printStudentName(students);
        }).start();

        new Thread(() -> {
            printStudentName(students);
            printStudentName(students);
        }).start();
    }

    private synchronized void printStudentName(List<Student> students) {
        System.out.println(count + students.get(count).getName());
        count++;
    }
}
