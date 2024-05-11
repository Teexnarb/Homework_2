package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationStudentControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }


    @Test
    public void testFindStudentById() throws Exception {
        Student testStudent = new Student();
        testStudent.setName("test");
        studentController.createStudent(testStudent);

        Student actual = this.testRestTemplate.getForObject("http://localhost:" + port + "/student/findStudentById/" + testStudent.getId(), Student.class);

        assertThat(actual).isEqualTo(testStudent);
        studentController.deleteStudent(testStudent.getId());
    }

    @Test
    public void testFindStudentByAge() throws Exception {
        Student testStudent2 = new Student();
        testStudent2.setAge(4);
        testStudent2.setName("Ginny");
        studentController.createStudent(testStudent2);

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/findStudentByAge/" + testStudent2.getAge(), String.class))
                .isNotNull();
        assertThat(testStudent2.getName()).isEqualTo("Ginny");
        studentController.deleteStudent(testStudent2.getId());
    }

    @Test
    public void testFindStudentByAgeBetween() throws Exception {
        Student student1 = new Student(10L, "Lucius", 40, null);
        studentController.createStudent(student1);
        Student student2 = new Student(11L, "Damblodor", 88, null);
        studentController.createStudent(student2);
        Student student3 = new Student(12L, "Flitwik", 60, null);
        studentController.createStudent(student3);


        var result = testRestTemplate.getForObject("http://localhost:" + port + "/student/findStudentByAgeBetween?min= 50&max=70", String.class);
        assertThat(result).isNotNull();


        studentController.deleteStudent(student1.getId());
        studentController.deleteStudent(student2.getId());
        studentController.deleteStudent(student3.getId());

    }

    @Test
    public void testFindFacultyOfStudent() throws Exception {
        Faculty faculty1 = new Faculty(20L, "Test", "fhgdbd", null);
        facultyController.createFaculty(faculty1);
        Student student1 = new Student(100L, "Lucius", 40, faculty1);
        studentController.createStudent(student1);

        Faculty actual = this.testRestTemplate.getForObject("http://localhost:" + port + "/student/facultyOfStudent?id=" + student1.getId(), Faculty.class);
        assertThat(actual.getId()).isEqualTo(faculty1.getId());

        studentController.deleteStudent(student1.getId());
        facultyController.deleteFaculty(faculty1.getId());

    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setAge(13);
        student.setName("Polumna");
        student.setFaculty(null);

        assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student/createStudent", student, Student.class))
                .isNotNull();
        assertThat(student.getName()).isEqualTo("Polumna");

        studentController.deleteStudent(student.getId());
    }

    @Test
    public void testEditStudent() throws Exception {
        Student student1 = new Student();
        student1.setAge(64);
        student1.setName("Minerva");
        student1.setFaculty(null);
        studentController.createStudent(student1);
        Student student2 = new Student();
        student2.setId(student1.getId());
        student2.setAge(78);
        student2.setName("Hagrid");
        student2.setFaculty(null);

        ResponseEntity<Student> response = testRestTemplate.exchange("http://localhost:" + port + "/student/editStudent",
                HttpMethod.PUT,
                new HttpEntity<>(student2),
                Student.class);

        Assertions
                .assertThat(response.getBody().getName()).isEqualTo("Hagrid");

        studentController.deleteStudent(student2.getId());

    }

    @Test
    void testDeleteStudent() throws Exception {
        Student student1 = new Student();
        student1.setId(20L);
        student1.setName("test");
        studentController.createStudent(student1);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:" + port + "/student/deleteStudent/" + student1.getId());
    }
}