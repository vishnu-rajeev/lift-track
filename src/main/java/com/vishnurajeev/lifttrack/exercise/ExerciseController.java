package com.vishnurajeev.lifttrack.exercise;

import com.vishnurajeev.lifttrack.workout.ExerciseProgressResponse;
import com.vishnurajeev.lifttrack.workout.ExerciseProgressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseProgressService exerciseProgressService;

    public ExerciseController(ExerciseService exerciseService, ExerciseProgressService exerciseProgressService) {
        this.exerciseService = exerciseService;
        this.exerciseProgressService = exerciseProgressService;
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@Valid @RequestBody CreateExerciseRequest request) {
        Exercise createdExercise = exerciseService.createExercise(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    @GetMapping("/{exerciseId}/progress")
    public ExerciseProgressResponse getExerciseProgress(@PathVariable Long exerciseId) {
        return exerciseProgressService.getExerciseProgress(exerciseId);
    }
}
