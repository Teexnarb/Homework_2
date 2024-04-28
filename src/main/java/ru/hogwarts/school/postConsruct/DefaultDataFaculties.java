package ru.hogwarts.school.postConsruct;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultDataFaculties {

    private final DefaultDataStudents defaultDataStudents;

    public DefaultDataFaculties(DefaultDataStudents defaultDataStudents) {
        this.defaultDataStudents = defaultDataStudents;
    }



    Faculty faculty1 = new Faculty(1, "Когневран (Ravenclaw)", "Красный (Red)", null);
    Faculty faculty2 = new Faculty(2, "Гриффиндор (Gryffindor)", "Жёлтый (Yellow)", null);
    Faculty faculty3 = new Faculty(3, "Пуффендуй (Hufflepuff)", "Зелёный (Green)", null);
    Faculty faculty4 = new Faculty(4, "Слизерин (Slytherin)", "Фиолетовый (Purple)", null);

    Map<Long, Faculty> facultyMap = new HashMap<>(Map.of(
            faculty1.getId(), faculty1,
            faculty2.getId(), faculty2,
            faculty3.getId(), faculty3,
            faculty4.getId(), faculty4));
    public void facultyRegistry() {
        faculty1.setStudents(defaultDataStudents.studentList(faculty1));
        faculty2.setStudents(defaultDataStudents.studentList(faculty2));
        faculty3.setStudents(defaultDataStudents.studentList(faculty3));
        faculty4.setStudents(defaultDataStudents.studentList(faculty4));

//        facultyMap.put(faculty1.getId(), faculty1);
//        facultyMap.put(faculty2.getId(), faculty2);
//        facultyMap.put(faculty3.getId(), faculty3);
//        facultyMap.put(faculty4.getId(), faculty4);
    }

    //        facultyRegistry();
    public Faculty facultyInfo(long id) {
//        faculty1.setStudents(defaultDataStudents.studentList(faculty1));
        return facultyMap.get(id);
    }
}