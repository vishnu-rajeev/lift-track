package com.vishnurajeev.lifttrack.workout;

public class WorkoutSessionNotFoundException extends RuntimeException{

    public WorkoutSessionNotFoundException(Long id) {
        super("Workout session not found with id " + id);
    }
}
