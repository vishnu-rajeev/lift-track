package com.vishnurajeev.lifttrack.workout;

public class WorkoutExerciseNotFoundException extends RuntimeException{

    public WorkoutExerciseNotFoundException(Long id) {
        super("Workout exercise not found with id: " + id);
    }
}
