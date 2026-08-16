package com.vishnurajeev.lifttrack.workout;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout-exercises")
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;

    public WorkoutSetController(WorkoutSetService workoutSetService) {
        this.workoutSetService = workoutSetService;
    }

    @PostMapping("/{workoutExerciseId}/sets")
    public ResponseEntity<WorkoutSetResponse> addSet(@PathVariable Long workoutExerciseId,
                                             @Valid @RequestBody AddWorkoutSetRequest request) {
        WorkoutSetResponse response = workoutSetService.addSet(workoutExerciseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
