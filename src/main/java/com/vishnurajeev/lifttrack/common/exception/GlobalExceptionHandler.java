package com.vishnurajeev.lifttrack.common.exception;

import com.vishnurajeev.lifttrack.exercise.DuplicateExerciseException;
import com.vishnurajeev.lifttrack.exercise.ExerciseNotFoundException;
import com.vishnurajeev.lifttrack.user.DuplicateEmailException;
import com.vishnurajeev.lifttrack.user.InvalidCredentialsException;
import com.vishnurajeev.lifttrack.workout.WorkoutExerciseNotFoundException;
import com.vishnurajeev.lifttrack.workout.WorkoutSessionNotFoundException;
import org.hibernate.jdbc.Work;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors (MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage()));

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("message", "Validation failed");
        responseBody.put("errors", errors);

        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(DuplicateExerciseException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateExercise(DuplicateExerciseException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.CONFLICT.value());
        responseBody.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }

    @ExceptionHandler(ExerciseNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleExerciseNotFound(ExerciseNotFoundException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(WorkoutSessionNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleWorkoutSessionNotFound(WorkoutSessionNotFoundException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(WorkoutExerciseNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleWorkoutExerciseNotFound(WorkoutExerciseNotFoundException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEmail(DuplicateEmailException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.CONFLICT.value());
        responseBody.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentials(InvalidCredentialsException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
        responseBody.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
