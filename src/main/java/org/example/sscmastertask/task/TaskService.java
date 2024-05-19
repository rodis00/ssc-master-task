package org.example.sscmastertask.task;

import org.example.sscmastertask.action.Action;
import org.example.sscmastertask.exception.TaskNotFoundException;
import org.example.sscmastertask.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getFormattedDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public String getAction(Action action) {
        return action.toString() + ": " + getFormattedDateAndTime();
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
                .modificationHistory(List.of(getAction(Action.created_at)))
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
        task.setModificationHistory(existedTask.getModificationHistory());
        task.getModificationHistory().add(getAction(Action.updated_at));
        return taskRepository.save(task);
    }

    public void deleteTaskById(String id) {
        Task existedTask = findTaskById(id);
        existedTask.setIsActive(false);
        existedTask.getModificationHistory().add(getAction(Action.deleted_at));
        taskRepository.save(existedTask);
    }

    public void deleteTaskByIdPermanently(String id) {
        Task existedTask = findTaskById(id);
        taskRepository.delete(existedTask);
    }

    public List<Task> findAllDeletedTasks() {
        return taskRepository.findAllByIsActive(false);
    }

    public Boolean checkRequirements(User user, Task task) {
        boolean organizationUnit = task.getOrganizationUnit() == null || user.getOrganizationUnit().equals(task.getOrganizationUnit());
        boolean team = task.getTeam() == null || user.getTeam().equals(task.getTeam());
        boolean experience = task.getMinimumExperienceLevel() == null || user.getExperienceLevel() >= task.getMinimumExperienceLevel();
        boolean ageInYears = task.getMaximumAgeInYears() == null || user.ageInYears(user.getDateOfBirth()) <= task.getMaximumAgeInYears();
        boolean isActive = task.getIsActive() != null && task.getIsActive();

        if (isActive) {
            if (task.getAllConditionsMustBeSatisfied()) {
                return organizationUnit && team && experience && ageInYears;
            }
            return organizationUnit || team || experience || ageInYears;
        }
        return false;
    }

    public List<Task> getAllUserTasks(User user) {
        List<Task> allTasks = taskRepository.findAll();
        return allTasks.stream()
                .filter(task -> checkRequirements(user, task))
                .sorted((o1, o2) -> o2.getPriority().compareTo(o1.getPriority()))
                .toList();
    }
}
