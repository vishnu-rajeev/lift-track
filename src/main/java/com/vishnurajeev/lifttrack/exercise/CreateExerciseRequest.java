package com.vishnurajeev.lifttrack.exercise;

import jakarta.validation.constraints.*;

public record CreateExerciseRequest (
        @NotBlank(message = "Exercise name is required")
        @Size(max = 100, message = "Exercise name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Muscle group is required")
        @Size(max = 50, message = "Muscle group must not exceed 50 characters")
        String muscleGroup
) {
}
