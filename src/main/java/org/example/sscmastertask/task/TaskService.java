package org.example.sscmastertask.task;

import org.example.sscmastertask.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void checkNonNullFields(Task task) {
        if (task.getOrganizationUnit() == null &&
                task.getTeam() == null &&
                task.getMinimumExperienceLevel() == null &&
                task.getMaximumAgeInYears() == null
        ) {
            throw new IllegalArgumentException("At least one of the fields [organizationUnit, team, " +
                    "minimumExperienceLevel, maximumAgeInYears] must be non-null.");
        }
    }

    public Task saveTask(Task task) {
        checkNonNullFields(task);

        Task savedTask = Task.builder()
                .title(task.getTitle())
                .numberOfSubtasks(task.getNumberOfSubtasks())
                .priority(task.getPriority())
                .creationDate(LocalDate.now())
                .allConditionsMustBeSatisfied(task.getAllConditionsMustBeSatisfied())
                .organizationUnit(task.getOrganizationUnit())
                .team(task.getTeam())
                .minimumExperienceLevel(task.getMinimumExperienceLevel())
                .maximumAgeInYears(task.getMaximumAgeInYears())
                .isActive(true)
                .build();

        return taskRepository.insert(savedTask);
    }

    public Task findTaskById(String id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty() || !task.get().getIsActive()) {
            throw new TaskNotFoundException("Task not found.");
        }
        return task.get();
    }

    public Task updateTaskById(String id, Task task) {
        Task existedTask = findTaskById(id);
        checkNonNullFields(task);
        task.setId(id);
        task.setIsActive(true);
        return taskRepository.save(task);
    }

    public void deleteTaskById(String id) {
        Task existedTask = findTaskById(id);
        existedTask.setIsActive(false);
        taskRepository.save(existedTask);
    }

    public void deleteTaskByIdPermanently(String id) {
        Task existedTask = findTaskById(id);
        taskRepository.delete(existedTask);
    }

    public List<Task> findAllDeletedTasks() {
        return taskRepository.findAllByIsActive(false);
    }
}
