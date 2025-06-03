package co.com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void shouldCreateTaskWithCorrectFields() {
        Task task = new Task(null, "Tarea 1", "Realizar la tarea #1");

        assertEquals("Tarea 1", task.getTitle());
        assertEquals("Realizar la tarea #1", task.getDescription());
        assertEquals(false, task.getCompleted());
        assertNull(task.getId());
    }
}