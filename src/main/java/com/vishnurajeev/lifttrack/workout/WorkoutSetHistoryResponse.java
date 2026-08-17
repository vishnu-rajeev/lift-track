package com.vishnurajeev.lifttrack.workout;

import java.math.BigDecimal;

public record WorkoutSetHistoryResponse(

        Integer setNumber,
        BigDecimal weightKg,
        Integer reps
) {
}
