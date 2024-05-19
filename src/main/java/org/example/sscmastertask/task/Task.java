package org.example.sscmastertask.task;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;

    @Pattern(regexp = "^[a-zA-Z\\s]*$",
            message = "Title must contain only letters and spaces")
    @NotNull(message = "Title can not be null")
    private String title;

    @Positive(message = "Number of subtasks must be a positive integer")
    @NotNull(message = "Number of subtasks can not be null")
    private Integer numberOfSubtasks;

    @Min(value = 0, message = "Priority must be greater or equal 0")
    @Max(value = 10, message = "Priority must be lower or equal 10")
    @NotNull(message = "Priority can not be null")
    private Integer priority;

    @NotNull(message = "Creation date can not be null")
    private LocalDate creationDate;

    @NotNull(message = "All conditions must be satisfied can not be null")
    private Boolean allConditionsMustBeSatisfied;

    @Pattern(regexp = "organization_A|organization_B|organization_C",
            message = "Organization unit must be one of [organization_A, organization_B, organization_C]")
    private String organizationUnit;

    @Pattern(regexp = "^[a-zA-Z\\s]*$",
            message = "Team must contain only letters and spaces")
    private String team;

    @Min(value = 1, message = "Minimum experience level must be greater or equal 1")
    @Max(value = 3, message = "Minimum experience level must be lower or equal 3")
    private Integer minimumExperienceLevel;

    @Positive(message = "Maximum age in years must be a positive integer")
    private Integer maximumAgeInYears;

    private Boolean isActive;
}
