package com.vishnurajeev.lifttrack.workout;

import java.math.BigDecimal;

public record WorkoutSetResponse(
        Long setId,
        Long workoutExerciseId,
        Integer setNumber,
        BigDecimal weightKg,
        Integer reps
) {
}
