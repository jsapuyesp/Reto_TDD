package co.com.example.service;
import co.com.example.model.Task;
import co.com.example.repository.TaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TaskServiceTest {

    @Test
    void shouldCreateTaskWithCompletedFalse(){
        TaskRepository mockRepo = mock(TaskRepository.class);
        TaskService service = new TaskService(mockRepo);

        Task newTask = new Task(null, "Tarea 1", "Tarea 1");
        Task newTaskSaved = new Task(1L, "Tarea 1", "Tarea 1");

        when(mockRepo.save(any(Task.class))).thenReturn(newTaskSaved);

        Task result = service.createTask(newTask);

        assertNotNull(result);
        assertEquals("Tarea 1", result.getTitle());
        assertFalse(result.getCompleted());
        verify(mockRepo).save(newTask);
    }

    @Test
    void shouldThrowExceptionWhenTaskIsNull(){
        TaskRepository mockRepo = mock(TaskRepository.class);
        TaskService service = new TaskService(mockRepo);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
           service.createTask(null);
        });

        assertEquals("La tarea no puede ser nula", exception.getMessage());
    }
}
