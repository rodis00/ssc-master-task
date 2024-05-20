package org.example.sscmastertask.task;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public ResponseEntity<Task> saveTask(@RequestBody @Valid Task task) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.saveTask(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(
            @PathVariable String id,
            @RequestBody @Valid Task task
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.updateTaskById(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable String id) {
        taskService.deleteTaskById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/delete-permanently/{id}")
    public ResponseEntity<Void> deleteTaskByIdPermanently(@PathVariable String id) {
        taskService.deleteTaskByIdPermanently(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/deleted-tasks")
    public ResponseEntity<List<Task>> findAllDeletedTasks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllDeletedTasks());
    }
}
