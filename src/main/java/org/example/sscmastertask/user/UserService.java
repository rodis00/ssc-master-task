package org.example.sscmastertask.user;

import org.example.sscmastertask.action.Action;
import org.example.sscmastertask.exception.UserNotFoundException;
import org.example.sscmastertask.task.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskService taskService;

    public UserService(
            UserRepository userRepository, TaskService taskService
    ) {
        this.userRepository = userRepository;
        this.taskService = taskService;
    }

    public String getFormattedDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public String getAction(Action action) {
        return action.toString() + ": " + getFormattedDateAndTime();
    }

    public Integer ageInYears(LocalDate dateOfBirth) {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public void updateTasksForUser(User user) {
        user.setTasks(taskService.getAllUserTasks(user));
    }

    public User saveUser(User user) {
        User savedUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .creationDate(LocalDateTime.now())
                .organizationUnit(user.getOrganizationUnit())
                .team(user.getTeam())
                .experienceLevel(user.getExperienceLevel())
                .isActive(true)
                .modificationHistory(List.of(getAction(Action.created_at)))
                .build();

        updateTasksForUser(user);
        return userRepository.insert(savedUser);
    }

    public User findUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || !user.get().getIsActive()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    public User updateUserById(String id, User user) {
        User existedUser = findUserById(id);
        user.setId(id);
        user.setIsActive(true);
        user.setModificationHistory(existedUser.getModificationHistory());
        user.getModificationHistory().add(getAction(Action.updated_at));
        updateTasksForUser(user);
        return userRepository.save(user);
    }

    public void deleteUserById(String id) {
        User existedUser = findUserById(id);
        existedUser.setIsActive(false);
        existedUser.getModificationHistory().add(getAction(Action.deleted_at));
        userRepository.save(existedUser);
    }

    public void deleteUserByIdPermanently(String id) {
        User existedUser = findUserById(id);
        userRepository.delete(existedUser);
    }

    public List<User> findAllDeletedUsers() {
        return userRepository.findAllByIsActive(false);
    }
}
