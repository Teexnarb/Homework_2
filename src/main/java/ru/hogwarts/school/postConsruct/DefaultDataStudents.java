package ru.hogwarts.school.postConsruct;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DefaultDataStudents {

    private DefaultDataStudents defaultDataStudents;
    //    public DefaultDataStudents(DefaultDataStudents defaultDataStudents) {
//        this.defaultDataStudents = defaultDataStudents;
//    }
    private final DefaultDataFaculties defaultDataFaculties = new DefaultDataFaculties(defaultDataStudents);

    Student student1 = new Student(1L, "Луна Лавгуд (Luna Lovegood)", 25, defaultDataFaculties.facultyInfo(1));
    Student student2 = new Student(2L, "Дин Томас (Dean Thomas)", 78, defaultDataFaculties.facultyInfo(1));
    Student student3 = new Student(3L, "Симус Финниган (Seamus Finnigan)", 43, defaultDataFaculties.facultyInfo(3));
    Student student4 = new Student(4L, "Блейз Забини (Blaise Zabini)", 67, defaultDataFaculties.facultyInfo(2));
    Student student5 = new Student(5L, "Миллисента Булстроуд (Millicent Bulstrode)", 89, defaultDataFaculties.facultyInfo(4));
    Student student6 = new Student(6L, "Панси Паркинсон (Pansy Parkinson)", 93, defaultDataFaculties.facultyInfo(4));
    Student student7 = new Student(7L, "Чо Чанг (Cho Chang)", 56, defaultDataFaculties.facultyInfo(4));
    Student student8 = new Student(8L, "Майкл Корнер (Michael Corner)", 34, defaultDataFaculties.facultyInfo(1));
    Student student9 = new Student(9L, "Роберт Хиллиард (Robert Hilliard)", 100, defaultDataFaculties.facultyInfo(1));
    Student student10 = new Student(10L, "Элеанор Брэнсток (Eleanor Branstock)", 18, defaultDataFaculties.facultyInfo(1));
    Student student11 = new Student(11L, "Кэти Белл (Katie Bell)", 73, defaultDataFaculties.facultyInfo(3));
    Student student12 = new Student(12L, "Алисия Спиннет (Alicia Spinnet)", 23, defaultDataFaculties.facultyInfo(2));
    Student student13 = new Student(13L, "Анджелина Джонсон (Angelina Johnson)", 64, defaultDataFaculties.facultyInfo(4));
    Student student14 = new Student(14L, "Кэтти Бел (Kathy Bell)", 83, defaultDataFaculties.facultyInfo(2));
    Student student15 = new Student(15L, "Колин Криви (Colin Creevey)", 49, defaultDataFaculties.facultyInfo(2));
    Student student16 = new Student(16L, "Гарри Поттер (Harry Potter)", 11, defaultDataFaculties.facultyInfo(2));
    Student student17 = new Student(17L, "Гермиона Грейнджер (Hermione Granger)", 11, defaultDataFaculties.facultyInfo(2));
    Student student18 = new Student(18L, "Рон Уизли (Ron Weasley)", 11, defaultDataFaculties.facultyInfo(2));
    Student student19 = new Student(19L, "Драко Малфой (Draco Malfoy)", 12, defaultDataFaculties.facultyInfo(2));
    Student student20 = new Student(20L, "Невилл Лонгботтом (Neville Longbottom)", 10, defaultDataFaculties.facultyInfo(4));
    Student student21 = new Student(21L, "Джинни Уизли (Ginny Weasley)", 7, defaultDataFaculties.facultyInfo(3));
    Student student22 = new Student(22L, "Альбус Дамблдор (Albus Dumbledore)", 115, defaultDataFaculties.facultyInfo(2));
    Student student23 = new Student(23L, "Северус Снегг (Severus Snape)", 38, defaultDataFaculties.facultyInfo(3));
    Student student24 = new Student(24L, "Филиус Флитвик (Filius Flitwick)", 75, defaultDataFaculties.facultyInfo(1));
    List<Student> studentList = new ArrayList<>(Arrays.asList
            (student1, student2, student3, student4, student5, student6,
                    student7, student8, student9, student10, student11,
                    student12, student13, student14, student15, student16,
                    student17, student18, student19, student20, student21,
                    student22, student23, student24));


    public List<Student> studentList(Faculty faculty) {
        List<Student> studentsFaculty = new ArrayList<>();

        for (Student student : studentList) {
            if (student.getFaculty().equals(faculty)) {
                studentList.add(student);
            }
        }
        defaultDataFaculties.facultyRegistry();
        return studentsFaculty;
    }
}