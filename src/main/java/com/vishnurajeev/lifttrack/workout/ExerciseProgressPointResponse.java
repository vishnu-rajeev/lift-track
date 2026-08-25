package com.vishnurajeev.lifttrack.workout;

import java.math.BigDecimal;
import java.time.Instant;

public record ExerciseProgressPointResponse(
        Long workoutId,
        Instant performedAt,
        BigDecimal maxWeightKg,
        BigDecimal totalVolumeKg
) {
}
