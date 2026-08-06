package com.vishnurajeev.lifttrack.workout;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    public WorkoutSessionController(WorkoutSessionService workoutSessionService) {
        this.workoutSessionService = workoutSessionService;
    }

    @PostMapping
    public ResponseEntity<WorkoutSession> startWorkout() {
        WorkoutSession workoutSession = workoutSessionService.startWorkout();

        return ResponseEntity.status(HttpStatus.CREATED).body(workoutSession);
    }
}
