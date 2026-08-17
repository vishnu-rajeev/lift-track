package com.vishnurajeev.lifttrack.workout;

import java.time.Instant;
import java.util.List;

public record WorkoutSessionResponse(
        Long id,
        Instant startedAt,
        Instant finishedAt,
        List<WorkoutExerciseHistoryResponse> exercises
        ) {
}
