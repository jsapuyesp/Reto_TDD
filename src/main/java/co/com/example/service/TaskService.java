package co.com.example.service;

import co.com.example.model.Task;
import co.com.example.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository){
        this.repository = repository;
    }

    public Task createTask(Task newTask) {
        if (newTask == null){
            throw new IllegalArgumentException("La tarea no puede ser nula");
        }
        return repository.save(newTask);
    }

}
