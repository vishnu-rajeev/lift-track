package com.vishnurajeev.lifttrack.workout;

import java.util.List;

public record WorkoutExerciseHistoryResponse(
        Long exerciseId,
        String name,
        Integer position,
        List<WorkoutSetHistoryResponse> sets
) {
}
