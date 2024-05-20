package org.example.sscmastertask.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(
            @PathVariable String id,
            @RequestBody @Valid User user
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUserById(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/delete-permanently/{id}")
    public ResponseEntity<Void> deletePermanently(@PathVariable String id) {
        userService.deleteUserByIdPermanently(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/deleted-users")
    public ResponseEntity<List<User>> getDeletedUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAllDeletedUsers());
    }
}
