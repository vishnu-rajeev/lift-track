package com.vishnurajeev.lifttrack.workout;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;
    private final WorkoutExerciseService workoutExerciseService;

    public WorkoutSessionController(WorkoutSessionService workoutSessionService, WorkoutExerciseService workoutExerciseService) {
        this.workoutSessionService = workoutSessionService;
        this.workoutExerciseService = workoutExerciseService;
    }

    @PostMapping
    public ResponseEntity<WorkoutSession> startWorkout() {
        WorkoutSession workoutSession = workoutSessionService.startWorkout();

        return ResponseEntity.status(HttpStatus.CREATED).body(workoutSession);
    }

    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<WorkoutExercise> addExerciseToWorkout(@PathVariable Long workoutId,
                                                                @Valid @RequestBody AddExerciseToWorkoutRequest request) {
        WorkoutExercise workoutExercise = workoutExerciseService.addExerciseToWorkout(workoutId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(workoutExercise);
    }

    @PostMapping("/{workoutId}/finish")
    public ResponseEntity<WorkoutSession> finishWorkout(@PathVariable Long workoutId) {
        WorkoutSession workoutSession = workoutSessionService.finishWorkout(workoutId);
        return ResponseEntity.ok(workoutSession);
    }

    @GetMapping
    public List<WorkoutSessionResponse> getWorkoutHistory() {
        return workoutSessionService.getWorkoutHistory();
    }
}
