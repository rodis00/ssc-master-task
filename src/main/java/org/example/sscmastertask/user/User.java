package org.example.sscmastertask.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate creationDate;
    private String organizationUnit;
    private String team;
    private Integer experienceLevel;
}
