package org.example.sscmastertask.user;

import org.example.sscmastertask.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        User savedUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .creationDate(LocalDate.now())
                .organizationUnit(user.getOrganizationUnit())
                .team(user.getTeam())
                .experienceLevel(user.getExperienceLevel())
                .build();

        return userRepository.insert(savedUser);
    }

    public User findUserById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User updateUserById(String id, User user) {
        User existedUser = findUserById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(String id) {
        User existedUser = findUserById(id);
        userRepository.delete(existedUser);
    }
}
