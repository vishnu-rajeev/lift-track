package com.vishnurajeev.lifttrack.workout;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {

    List<WorkoutSet> findByWorkoutExerciseIdOrderBySetNumberAsc(Long workoutExerciseId);
}
