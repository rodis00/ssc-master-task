package org.example.sscmastertask.user;

import org.example.sscmastertask.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
                .creationDate(LocalDateTime.now())
                .organizationUnit(user.getOrganizationUnit())
                .team(user.getTeam())
                .experienceLevel(user.getExperienceLevel())
                .isActive(true)
                .build();

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
        return userRepository.save(user);
    }

    public void deleteUserById(String id) {
        User existedUser = findUserById(id);
        existedUser.setIsActive(false);
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
