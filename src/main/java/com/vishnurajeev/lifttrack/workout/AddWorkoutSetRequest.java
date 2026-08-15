package com.vishnurajeev.lifttrack.workout;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AddWorkoutSetRequest (

        @NotNull (message = "Set number is required")
        @Positive(message = "Set number must be positive")
        Integer setNumber,

        @NotNull (message = "Weight is required")
        @DecimalMin(
                value = "0.0",
                inclusive = true,
                message = "Weight must be zero or greater"
        )
        BigDecimal weightKg,

        @NotNull (message = "Reps are required")
        @Positive (message = "Reps must be positive")
        Integer reps
) {

}
