package com.vishnurajeev.lifttrack.workout;

import java.time.Instant;

public record WorkoutSessionResponse(
        Long id,
        Instant startedAt,
        Instant finishedAt
) {
}
