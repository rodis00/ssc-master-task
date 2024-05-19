package org.example.sscmastertask.user;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.example.sscmastertask.validator.date.DateAfter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*$",
            message = "First name must start with a capital letter and contain only letters.")
    private String firstName;

    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*$",
            message = "Last name must start with a capital letter and contain only letters.")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    @DateAfter(message = "Date of birth should be after 1900-01-01", date = "1900-01-01")
    private LocalDate dateOfBirth;

    @NotNull(message = "Creation date can not be null")
    private LocalDateTime creationDate;

    @Pattern(regexp = "organization_A|organization_B|organization_C",
            message = "Organization unit must be one of [organization_A, organization_B, organization_C]")
    private String organizationUnit;

    @Pattern(regexp = "^[a-zA-Z\\s]*$",
            message = "Team must contain only letters and spaces")
    private String team;

    @Min(value = 1, message = "Experience level should be greater or equal 1")
    @Max(value = 3, message = "Experience level should be lower or equal 3")
    private Integer experienceLevel;
}
