package org.example.sscmastertask.task;

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
    private String title;
    private Integer numberOfSubtasks;
    private Integer priority;
    private LocalDate creationDate;
    private String organizationUnit;
    private String team;
    private Integer minimumExperienceLevel;
    private Integer maximumAgeInYears;
}
