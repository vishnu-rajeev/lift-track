package com.vishnurajeev.lifttrack.workout;

import com.vishnurajeev.lifttrack.exercise.Exercise;
import com.vishnurajeev.lifttrack.exercise.ExerciseNotFoundException;
import com.vishnurajeev.lifttrack.exercise.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutSessionRepository workoutSessionRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository,
                                  WorkoutSessionRepository workoutSessionRepository,
                                  ExerciseRepository exerciseRepository) {

        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutSessionRepository = workoutSessionRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public WorkoutExercise addExerciseToWorkout(Long workoutId, AddExerciseToWorkoutRequest request) {
        WorkoutSession workoutSession = workoutSessionRepository.findById(workoutId)
                                        .orElseThrow(() -> new WorkoutSessionNotFoundException(workoutId));
        Exercise exercise = exerciseRepository.findById(request.exerciseId())
                            .orElseThrow(() -> new ExerciseNotFoundException(request.exerciseId()));

        WorkoutExercise workoutExercise= new WorkoutExercise(workoutSession, exercise, request.position());

        return workoutExerciseRepository.save(workoutExercise);
    }
}
