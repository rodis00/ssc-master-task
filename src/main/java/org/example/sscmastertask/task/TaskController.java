package org.example.sscmastertask.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
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
            @RequestBody Task task
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
}
