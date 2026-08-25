package com.vishnurajeev.lifttrack.workout;

import java.util.List;

public record ExerciseProgressResponse(
        Long exerciseId,
        String exerciseName,
        List<ExerciseProgressPointResponse> history
) {
}
