package co.com.example.controller;

import co.com.example.model.Task;
import co.com.example.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldCreateTaskAndReturn201() throws Exception {
        Task newTask = new Task(null, "Tarea 1", "Tarea 1");
        Task newTaskSaved = new Task(1L, "Tarea 1", "Tarea 1");

        when(service.createTask(any(Task.class))).thenReturn(newTaskSaved);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Tarea 1"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void shouldReturn400NullTask() throws Exception {
        String newTaskEmpty = "{}";
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTaskEmpty))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400EmptyTitle() throws Exception{
        String newTaskWithoutTitle = """
                {
                    "title": "",
                    "description": "Tarea 1"
                }
                """;

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTaskWithoutTitle))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400EmptyDescription() throws Exception{
        String newTaskWithoutTitle = """
                {
                    "title": "Tarea 1",
                    "description": ""
                }
                """;

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskWithoutTitle))
                .andExpect(status().isBadRequest());
    }

}
