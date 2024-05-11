package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.AvatarController;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SchoolApplicationFacultyControllerWithMVCTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private AvatarService avatarService;

    @InjectMocks
    private FacultyController facultyController;

    @InjectMocks
    private AvatarController avatarController;

    @Test
    public void testCreateFaculty() throws Exception {
        Long id = 20L;
        String name = "test";
        String color = "test";
        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("color", color);

        Faculty facultyTest = new Faculty();
        facultyTest.setId(id);
        facultyTest.setName(name);
        facultyTest.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty/createFaculty")
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testGetFacultyById() throws Exception {
        Long id = 20L;
        String name = "test";
        String color = "test";
        Faculty facultyTest = new Faculty(id, name, color, null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("color", color);
        when(facultyRepository.findById(id)).thenReturn(Optional.of(facultyTest));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findById/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testEditFaculty() throws Exception {
        Long id = 20L;
        String name = "test";
        String color = "test";
        String newColor = "vnb,jhbdl";
        Faculty facultyTest = new Faculty(id, name, color, null);
        Faculty facultyUpdate = new Faculty(id, name, newColor, null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("color", newColor);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyUpdate);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/editFaculty")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(newColor));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long id = 20L;
        String name = "test";
        String color = "test";
        Faculty facultyTest = new Faculty(id, name, color, null);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/deleteFaculty/" + facultyTest.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllStudentsFaculty() throws Exception {
        Faculty facultyTest = new Faculty();
        facultyTest.setId(1L);
        facultyTest.setName("test");
        facultyTest.setColor("test");
        Student student = new Student(10L, "test", 12, facultyTest);
        List<Faculty> actualFaculties = new ArrayList<>();
        actualFaculties.add(facultyTest);
        List<Faculty> expFaculties = new ArrayList<>();
        expFaculties.add(facultyTest);
        when(facultyRepository.findAll()).thenReturn(actualFaculties);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty")
                .accept(MediaType.APPLICATION_JSON));

        assertEquals(actualFaculties.size(), expFaculties.size());
        for (int i = 0; i < actualFaculties.size(); i++) {
            assertEquals(expFaculties.get(i).getName(), actualFaculties.get(i).getName());
        }
    }

    @Test
    public void testFindByNameOrColor() throws Exception {
        Long id = 20L;
        String name = "test1";
        String color = "brown";

        Faculty facultyTest1 = new Faculty(id, name, color, null);
        Faculty facultyTest2 = new Faculty(11L, "test2", "black", null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("color", color);

        when(facultyRepository.getByNameIgnoreCaseOrColorIgnoreCase(color, name)).thenReturn(Optional.of(facultyTest1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findFacultyByColorOrName?color=" + color + "&name=" + name)
                        .content(jsonObject.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));


    }
}
