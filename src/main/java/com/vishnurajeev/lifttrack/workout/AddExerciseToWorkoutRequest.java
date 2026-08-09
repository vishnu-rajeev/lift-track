package com.vishnurajeev.lifttrack.workout;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddExerciseToWorkoutRequest(

    @NotNull(message = "Exercise id is required")
    @Positive(message = "Exercise id must be positive")
    Long exerciseId,

    @NotNull(message = "Position is required")
    @Positive(message = "Position must be positive")
    Integer position
) {
}
