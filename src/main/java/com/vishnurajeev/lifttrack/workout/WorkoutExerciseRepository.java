package com.vishnurajeev.lifttrack.workout;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findByWorkoutSessionIdOrderByPositionAsc(Long workoutSessionId);

    List<WorkoutExercise> findByExerciseIdAndWorkoutSessionFinishedAtIsNotNullOrderByWorkoutSessionStartedAtAsc(Long exerciseId);
}
