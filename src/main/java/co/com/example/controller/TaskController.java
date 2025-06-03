package co.com.example.controller;

import co.com.example.model.Task;
import co.com.example.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task newTask){
        Task created = service.createTask(newTask);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


}
