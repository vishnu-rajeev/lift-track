package com.vishnurajeev.lifttrack.exercise;

public class DuplicateExerciseException extends RuntimeException {

    public DuplicateExerciseException(String name) {
        super("Exercise already exists: " + name);
    }
}